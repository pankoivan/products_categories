package org.test_task.products_categories.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.test_task.products_categories.dto.in.product.ProductAddingDto;
import org.test_task.products_categories.dto.in.product.ProductEditingDto;
import org.test_task.products_categories.dto.in.product.ProductSavingDto;
import org.test_task.products_categories.entities.Product;
import org.test_task.products_categories.exceptions.EntityNotFoundException;
import org.test_task.products_categories.exceptions.FileUploadingException;
import org.test_task.products_categories.repositories.ProductRepository;
import org.test_task.products_categories.services.interfaces.CategoryService;
import org.test_task.products_categories.services.interfaces.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Value("${my.file.upload-path}")
    private final String uploadPath;

    private CategoryService categoryService;

    @Override
    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Продукт с id \"%s\" не найден"));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        // todo: обработать некорректные параметры GET-запроса
        return findAll()
                .stream()
                .filter(product -> product.getName().contains(params.get("name")))
                .filter(product -> product.getPrice() >= Integer.parseInt(params.get("priceFrom")))
                .filter(product -> product.getPrice() <= Integer.parseInt(params.get("priceTo")))
                .filter(product -> product.getCategory().getName().contains("categoryName"))
                .toList();
    }

    @Override
    public Product add(ProductAddingDto addingDto, BindingResult bindingResult) {
        //validateBindingResult(bindingResult);
        String filename;
        try {
            filename = uploadImage(addingDto.getEncodedImage());
        } catch (IOException e) {
            throw new FileUploadingException("Произошла ошибка при загрузке файла на сервер", e);
        }
        System.out.println(filename);
        return repository.save(
                Product
                        .builder()
                        .name(addingDto.getName())
                        .description(addingDto.getDescription())
                        .price(addingDto.getPrice())
                        .imageName(filename)
                        .status(true)
                        .category(categoryService.findById(addingDto.getCategoryId()))
                        //.category(categoryService.findById(1))
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public Product edit(Integer id, ProductEditingDto editingDto, BindingResult bindingResult) {
        validateBindingResult(bindingResult);
        //Product product = findById(editingDto.getId()); todo
        Product product = findById(id);
        product.setName(editingDto.getName());
        product.setDescription(editingDto.getDescription());
        product.setPrice(editingDto.getPrice());
        if (editingDto.getStatus() != null) {
            product.setStatus(editingDto.getStatus());
        }
        if (editingDto.getCategoryId() != null) {
            product.setCategory(categoryService.findById(editingDto.getCategoryId()));
        }
        if (editingDto.getEncodedImage() != null) {
            try {
                product.setImageName(uploadImage(editingDto.getEncodedImage()));
            } catch (IOException e) {
                throw new FileUploadingException("Произошла ошибка при загрузке файла на сервер", e);
            }
        }
        return repository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        Product product = findById(id);
        repository.deleteById(product.getId());
    }

    @Override
    @Transactional
    public void makeInactive(Collection<Product> products) {
        products.forEach(product -> product.setStatus(false));
        repository.saveAll(products);
    }

    private String uploadImage(ProductSavingDto.EncodedBase64Image encodedImage) throws IOException {
        String filename = String.format("%s.%s", UUID.randomUUID().toString(), encodedImage.extension());
        Files.write(Paths.get(uploadPath, filename), Base64.getDecoder().decode(encodedImage.image()));
        return filename;
    }

}
