const categoryAddingForm = document.getElementById("category-adding-form");

const categoryInputFields = categoryAddingForm.querySelectorAll('[data-element="category-input-field"]');

categoryAddingForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const data = {
        name: categoryAddingForm.querySelector('input[name="name"]').value,
        description: categoryAddingForm.querySelector('input[name="description"]').value,
    };

    fetchAdd(data);
});

function fetchAdd(data) {
    const parts = window.location.pathname.split("/");
    const byb = parts[parts.length - 1].trim();
    console.log(byb);
    fetch(`http://localhost:8080/api/categories/${parts[parts.length - 1]}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": categoryAddingForm.querySelector('input[name="_csrf"]').value,
        },
        body: JSON.stringify(data),
    })
        .then((response) => {
            if (response.ok) {
                console.log("SUCCESS");
                window.location.href = "http://localhost:8080/categories";
            } else {
                console.log("ERROR");
                response.json().then((responseJson) => {
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
