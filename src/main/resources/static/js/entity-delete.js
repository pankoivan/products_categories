function fetchDelete(urlPart, id) {
    fetch(`http://localhost:8080/api/${urlPart}/${id}`, {
        method: "DELETE",
        headers: {
            "X-CSRF-TOKEN": document.getElementById("csrf-form").querySelector('input[name="_csrf"]').value,
        },
    })
        .then((response) => {
            if (response.ok) {
                window.location.href = `http://localhost:8080/${urlPart}`;
            }
        })
        .catch((error) => {
            console.error("Ошибка запроса", error);
        });
}
