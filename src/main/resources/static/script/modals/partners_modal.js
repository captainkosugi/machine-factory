document.addEventListener("DOMContentLoaded", () => {
    // Получаем элементы модального окна
    const modalOverlay = document.getElementById("modal-overlay");
    const partnerModal = document.getElementById("partner-modal");
    const addPartnerButton = document.getElementById("add-partner");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");

    // Открытие модального окна при нажатии на кнопку "Добавить сотрудника"
    if (addPartnerButton) {
        addPartnerButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            partnerModal.classList.add("active");
        });
    }

    // Закрытие модального окна при нажатии на кнопку закрытия
    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            partnerModal.classList.remove("active");
        });
    });

    // Закрытие модального окна при клике вне его
    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            partnerModal.classList.remove("active");
        }
    });
});