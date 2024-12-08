function search() {
    const url = new URL(window.location.href);
    url.searchParams.set("name", document.querySelector('input[name="name"]').value);
    url.searchParams.set("priceFrom", document.querySelector('input[name="priceFrom"]').value);
    url.searchParams.set("priceTo", document.querySelector('input[name="priceTo"]').value);
    url.searchParams.set("categoryName", document.querySelector('input[name="categoryName"]').value);
    window.location.href = url;
}
