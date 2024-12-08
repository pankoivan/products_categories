const categoryAddingForm = document.getElementById("category-adding-form");

const deleteButtons = document.querySelectorAll('button[data-element="delete-button"]');

deleteButtons.forEach((deleteButton) => {
    console.log("bynb");
    deleteButton.addEventListener("click", () => {
        fetchDelete(deleteButton.dataset.id);
    });
});

function fetchDelete(id) {
    fetch(`http://localhost:8080/api/categories/${id}`, {
        method: "DELETE",
        headers: {
            "X-CSRF-TOKEN": document.getElementById("csrf-form").querySelector('input[name="_csrf"]').value,
        },
    })
        .then((response) => {
            if (response.ok) {
                window.location.href = "http://localhost:8080/categories";
            }
        })
        .catch((error) => {
            console.error("Ошибка запроса", error);
        });
}
