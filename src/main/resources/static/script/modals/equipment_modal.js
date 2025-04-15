document.addEventListener("DOMContentLoaded", () => {
    // Получаем элементы модального окна
    const modalOverlay = document.getElementById("modal-overlay");
    const equipmentModal = document.getElementById("equipment-modal");
    const addEquipmentButton = document.getElementById("add-equipment");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");

    // Открытие модального окна при нажатии на кнопку "Добавить сотрудника"
    if (addEquipmentButton) {
        addEquipmentButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            equipmentModal.classList.add("active");
        });
    }

    // Закрытие модального окна при нажатии на кнопку закрытия
    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            equipmentModal.classList.remove("active");
        });
    });

    // Закрытие модального окна при клике вне его
    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            equipmentModal.classList.remove("active");
        }
    });
});