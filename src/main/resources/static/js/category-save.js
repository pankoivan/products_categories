const savingForm = document.querySelector("form");

savingForm.addEventListener("submit", (event) => {
    event.preventDefault();
});

function fetchSave(isAdding, id) {
    fetch(isAdding ? "http://localhost:8080/api/categories" : `http://localhost:8080/api/categories/${id}`, {
        method: isAdding ? "POST" : "PUT",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": savingForm.querySelector('input[name="_csrf"]').value,
        },
        body: JSON.stringify({
            name: savingForm.querySelector('input[name="name"]').value,
            description: savingForm.querySelector('input[name="description"]').value,
        }),
    })
        .then((response) => {
            if (response.ok) {
                window.location.href = "http://localhost:8080/categories";
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
