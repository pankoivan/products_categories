const savingForm = document.querySelector("form");

savingForm.addEventListener("submit", (event) => {
    event.preventDefault();
});

function save(isAdding, id) {
    file = savingForm.querySelector('input[type="file"]').files[0];
    if (file) {
        encodeFileToBase64(file)
            .then((base64) => {
                const data = {
                    name: savingForm.querySelector('input[name="name"]').value,
                    description: savingForm.querySelector('input[name="description"]').value,
                    price: savingForm.querySelector('input[name="price"]').value,
                    image: {
                        image: base64,
                        extension: file.name.split(".").pop(),
                    },
                    status: savingForm.querySelector('select[name="status"]').value,
                    categoryId: savingForm.querySelector('select[name="categoryId"]').value,
                };
                fetchSave(data, isAdding, id);
            })
            .catch((error) => {
                console.error("Ошибка кодирования файла в формат Base64", error);
            });
    } else {
        const data = {
            name: savingForm.querySelector('input[name="name"]').value,
            description: savingForm.querySelector('input[name="description"]').value,
            price: savingForm.querySelector('input[name="price"]').value,
            status: savingForm.querySelector('select[name="status"]').value,
            categoryId: savingForm.querySelector('select[name="categoryId"]').value,
        };
        fetchSave(data, isAdding, id);
    }
}

function fetchSave(data, isAdding, id) {
    fetch(isAdding ? "http://localhost:8080/api/products" : `http://localhost:8080/api/products/${id}`, {
        method: isAdding ? "POST" : "PUT",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": savingForm.querySelector('input[name="_csrf"]').value,
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            if (response.ok) {
                window.location.href = "http://localhost:8080/products";
            } else {
                response.json().then((responseJson) => {
                    savingForm.querySelectorAll('[data-element="input-field"]').forEach((inputField) => {
                        inputField.classList.remove("is-invalid");
                        inputField.classList.add("is-valid");
                    });
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

function encodeFileToBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = () => {
            const base64String = reader.result.split(",")[1];
            resolve(base64String);
        };
        reader.onerror = (error) => {
            reject(error);
        };
        reader.readAsDataURL(file);
    });
}
