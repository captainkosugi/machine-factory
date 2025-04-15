document.addEventListener("DOMContentLoaded", () => {
    const modalOverlay = document.getElementById("modal-overlay");
    const partnerModal = document.getElementById("partner-modal");
    const addPartnerButton = document.getElementById("add-partner");
    const closeModalButtons = document.querySelectorAll(".close-modal, .cancel-modal");

    if (addPartnerButton) {
        addPartnerButton.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            partnerModal.classList.add("active");
        });
    }

    closeModalButtons.forEach((button) => {
        button.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            partnerModal.classList.remove("active");
        });
    });

    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            partnerModal.classList.remove("active");
        }
    });
});