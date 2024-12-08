const categoryAddingForm = document.getElementById("product-adding-form");

const categoryInputFields = categoryAddingForm.querySelectorAll('[data-element="product-input-field"]');

categoryAddingForm.addEventListener("submit", (event) => {
    event.preventDefault();

    console.log("BYYYYYYYYYYYYB");

    file = document.querySelector('input[type="file"]').files[0];

    if (file) {
        convertFileToBase64(file)
            .then((base64) => {
                console.log("Base64:", base64);
                const data = {
                    name: categoryAddingForm.querySelector('input[name="name"]').value,
                    description: categoryAddingForm.querySelector('input[name="description"]').value,
                    price: categoryAddingForm.querySelector('input[name="price"]').value,
                    image: {
                        image: base64,
                        extension: "png",
                    },
                    categoryId: categoryAddingForm.querySelector('select[name="categoryId"]').value,
                };
                fetchAdd(data);
            })
            .catch((error) => {
                console.error("Ошибка при преобразовании файла:", error);
            });
    } else {
        const data = {
            name: categoryAddingForm.querySelector('input[name="name"]').value,
            description: categoryAddingForm.querySelector('input[name="description"]').value,
            price: categoryAddingForm.querySelector('input[name="price"]').value,
            categoryId: categoryAddingForm.querySelector('select[name="categoryId"]').value,
        };
        fetchAdd(data);
    }

    /*const data = {
        name: categoryAddingForm.querySelector('input[name="name"]').value,
        description: categoryAddingForm.querySelector('input[name="description"]').value,
    };*/

    //fetchAdd(data);
});

function convertFileToBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();

        reader.onload = () => {
            // Получаем результат и преобразуем его в строку Base64
            const base64String = reader.result.split(",")[1]; // Убираем часть перед запятой
            resolve(base64String);
        };

        reader.onerror = (error) => {
            reject(error);
        };

        // Читаем файл как Data URL
        reader.readAsDataURL(file);
    });
}

function fetchAdd(data) {
    const parts = window.location.pathname.split("/");
    const byb = parts[parts.length - 1].trim();
    console.log(byb);
    fetch(`http://localhost:8080/api/products/${parts[parts.length - 1]}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": categoryAddingForm.querySelector('input[name="_csrf"]').value,
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            if (response.ok) {
                window.location.href = "http://localhost:8080/products";
            } else {
                response.json().then((responseJson) => {
                    console.log(responseJson);
                    entityFormFieldsValid(categoryInputFields);
                    responseJson.fieldErrorPairs.forEach((pair) => {
                        const invalidInputField = document.getElementById(`${pair.fieldName}`);
                        const invalidFeedback = document.getElementById(`${pair.fieldName}-invalid`);
                        invalidInputField.classList.replace("is-valid", "is-invalid");
                        invalidFeedback.innerText = pair.fieldErrorMessage;
                    });
                });
            }
        })
        .catch((error) => {
            console.error("Ошибка запроса", error);
        });
}

function entityFormFieldsValid(entityInputFields) {
    entityInputFields.forEach((inputField) => {
        inputField.classList.remove("is-invalid");
        inputField.classList.add("is-valid");
    });
}
