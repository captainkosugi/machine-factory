document.addEventListener("DOMContentLoaded", () => {
    // Получаем элементы модального окна
    const modalOverlay = document.getElementById("modal-overlay");
    const inventoryModal = document.getElementById("inventory-modal");
    const addInventoryButton = document.getElementById("add-inventory");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");


    if (addInventoryButton) {
        addInventoryButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            inventoryModal.classList.add("active");
        });
    }


    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            inventoryModal.classList.remove("active");
        });
    });


    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            inventoryModal.classList.remove("active");
        }
    });
});