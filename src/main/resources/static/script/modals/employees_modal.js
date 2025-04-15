document.addEventListener("DOMContentLoaded", () => {
    const modalOverlay = document.getElementById("modal-overlay");
    const employeeModal = document.getElementById("employee-modal");
    const addEmployeeButton = document.getElementById("add-employee");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");

    if (addEmployeeButton) {
        addEmployeeButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            employeeModal.classList.add("active");
        });
    }

    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            employeeModal.classList.remove("active");
        });
    });


    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            employeeModal.classList.remove("active");
        }
    });
});