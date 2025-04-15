document.addEventListener("DOMContentLoaded", () => {
    // Получаем элементы модального окна
    const modalOverlay = document.getElementById("modal-overlay");
    const productModal = document.getElementById("product-modal");
    const addProductButton = document.getElementById("add-product");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");

    // Открытие модального окна при нажатии на кнопку "Добавить сотрудника"
    if (addProductButton) {
        addProductButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            productModal.classList.add("active");
        });
    }

    // Закрытие модального окна при нажатии на кнопку закрытия
    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            productModal.classList.remove("active");
        });
    });

    // Закрытие модального окна при клике вне его
    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            productModal.classList.remove("active");
        }
    });
});