document.addEventListener("DOMContentLoaded", () => {

    const modalOverlay = document.getElementById("modal-overlay");
    const equipmentModal = document.getElementById("equipment-modal");
    const addEquipmentButton = document.getElementById("add-equipment");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");


    if (addEquipmentButton) {
        addEquipmentButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            equipmentModal.classList.add("active");
        });
    }


    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            equipmentModal.classList.remove("active");
        });
    });


    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            equipmentModal.classList.remove("active");
        }
    });
});