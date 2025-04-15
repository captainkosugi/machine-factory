document.addEventListener("DOMContentLoaded", () => {
    // Получаем элементы модального окна
    const modalOverlay = document.getElementById("modal-overlay");
    const employeeModal = document.getElementById("employee-modal");
    const addEmployeeButton = document.getElementById("add-employee");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");

    // Открытие модального окна при нажатии на кнопку "Добавить сотрудника"
    if (addEmployeeButton) {
        addEmployeeButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            employeeModal.classList.add("active");
        });
    }

    // Закрытие модального окна при нажатии на кнопку закрытия
    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            employeeModal.classList.remove("active");
        });
    });

    // Закрытие модального окна при клике вне его
    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            employeeModal.classList.remove("active");
        }
    });
});