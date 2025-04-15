document.addEventListener("DOMContentLoaded", () => {
    const modalOverlay = document.getElementById("modal-overlay");
    const productModal = document.getElementById("product-modal");
    const addProductButton = document.getElementById("add-product");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");


    if (addProductButton) {
        addProductButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            productModal.classList.add("active");
        });
    }


    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            productModal.classList.remove("active");
        });
    });


    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            productModal.classList.remove("active");
        }
    });
});