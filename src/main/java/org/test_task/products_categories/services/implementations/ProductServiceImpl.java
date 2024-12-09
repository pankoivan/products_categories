package org.test_task.products_categories.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.test_task.products_categories.dto.ProductSavingDto;
import org.test_task.products_categories.entities.Product;
import org.test_task.products_categories.exceptions.EntityNotFoundException;
import org.test_task.products_categories.exceptions.FileOperationsException;
import org.test_task.products_categories.repositories.ProductRepository;
import org.test_task.products_categories.services.interfaces.CategoryService;
import org.test_task.products_categories.services.interfaces.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

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
        Stream<Product> productStream = findAll().stream();
        productStream = safeNameSearch(productStream, params.get("name"));
        productStream = safePriceFromSearch(productStream, params.get("priceFrom"));
        productStream = safePriceToSearch(productStream, params.get("priceTo"));
        productStream = safeCategoryNameSearch(productStream, params.get("categoryName"));
        return productStream.toList();
    }

    @Override
    public Product add(ProductSavingDto savingDto, BindingResult bindingResult) {
        if (savingDto.getEncodedImage() == null) {
            bindingResult.addError(new FieldError("emptyImage", "image", "Обязательное поле"));
        }
        validateBindingResult(bindingResult);
        String filename = uploadImage(savingDto.getEncodedImage());
        return repository.save(
                Product
                        .builder()
                        .name(savingDto.getName())
                        .description(savingDto.getDescription())
                        .price(savingDto.getPrice())
                        .imageName(filename)
                        .status(true)
                        .category(categoryService.findById(savingDto.getCategoryId()))
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    @Transactional
    public Product edit(Integer id, ProductSavingDto savingDto, BindingResult bindingResult) {
        validateBindingResult(bindingResult);
        Product product = findById(id);
        product.setName(savingDto.getName());
        product.setDescription(savingDto.getDescription());
        product.setPrice(savingDto.getPrice());
        product.setStatus(savingDto.getStatus());
        product.setCategory(categoryService.findById(savingDto.getCategoryId()));
        if (savingDto.getEncodedImage() != null) {
            product.setImageName(uploadImage(savingDto.getEncodedImage()));
        }
        return repository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Product product = findById(id);
        repository.deleteById(product.getId());
        deleteFromFileSystem(product.getImageName());
    }

    @Override
    @Transactional
    public void makeInactive(Collection<Product> products) {
        products.forEach(product -> product.setStatus(false));
        repository.saveAll(products);
    }

    private String uploadImage(ProductSavingDto.EncodedBase64Image encodedImage) {
        try {
            String filename = String.format("%s.%s", UUID.randomUUID(), encodedImage.extension());
            Files.write(Paths.get(uploadPath, filename), Base64.getDecoder().decode(encodedImage.image()));
            return filename;
        } catch (IOException e) {
            throw new FileOperationsException("Произошла ошибка при загрузке файла на сервер", e);
        }
    }

    private void deleteFromFileSystem(String filename) {
        try {
            Files.delete(Paths.get(uploadPath, filename));
        } catch (IOException e) {
            throw new FileOperationsException("Произошла ошибка при удалении файла с сервера", e);
        }
    }

    private Stream<Product> safeNameSearch(Stream<Product> stream, String name) {
        return name != null ? stream.filter(product -> product.getName().contains(name)) : stream;
    }

    private Stream<Product> safePriceFromSearch(Stream<Product> stream, String priceFrom) {
        int parsedPriceFrom;
        try {
            parsedPriceFrom = Integer.parseInt(priceFrom);
            return stream.filter(product -> product.getPrice() >= parsedPriceFrom);
        } catch (NumberFormatException e) {
            return stream;
        }
    }

    private Stream<Product> safePriceToSearch(Stream<Product> stream, String priceTo) {
        int parsedPriceTo;
        try {
            parsedPriceTo = Integer.parseInt(priceTo);
            return stream.filter(product -> product.getPrice() <= parsedPriceTo);
        } catch (NumberFormatException e) {
            return stream;
        }
    }

    private Stream<Product> safeCategoryNameSearch(Stream<Product> stream, String categoryName) {
        return categoryName != null ? stream.filter(product -> product.getCategory().getName().contains(categoryName)) : stream;
    }

}
