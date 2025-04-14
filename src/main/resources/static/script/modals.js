// modals.js
document.addEventListener("DOMContentLoaded", () => {
    // Загружаем модальные окна при необходимости
    loadModals();
});

// Функция для загрузки модальных окон
function loadModals() {
    fetch("/templates/modals.html") // Убедитесь, что путь правильный
        .then((response) => response.text())
        .then((html) => {
        document.getElementById("modal-container").innerHTML = html;
        initModals(); // Инициализация модальных окон
    })
        .catch((error) => {
        console.error("Ошибка загрузки модальных окон:", error);
    });
}

// Инициализация модальных окон
function initModals() {
    // Получаем элементы модальных окон
    const modalOverlay = document.getElementById("modal-overlay");
    const employeeModal = document.getElementById("employee-modal");
    const equipmentModal = document.getElementById("equipment-modal");
    const closeModalBtns = document.querySelectorAll(".close-modal, .cancel-modal");

    // Открытие модального окна для добавления сотрудника
    const addEmployeeBtn = document.getElementById("add-employee");
    if (addEmployeeBtn) {
        addEmployeeBtn.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            employeeModal.classList.add("active");
        });
    }

    // Открытие модального окна для добавления оборудования
    const addEquipmentBtn = document.getElementById("add-equipment");
    if (addEquipmentBtn) {
        addEquipmentBtn.addEventListener("click", () => {
            modalOverlay.style.display = "flex";
            equipmentModal.classList.add("active");
        });
    }

    // Закрытие модальных окон
    closeModalBtns.forEach((btn) => {
        btn.addEventListener("click", () => {
            modalOverlay.style.display = "none";
            document.querySelectorAll(".modal").forEach((modal) => {
                modal.classList.remove("active");
            });
        });
    });

    // Закрытие модального окна при клике на оверлей
    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) {
            modalOverlay.style.display = "none";
            document.querySelectorAll(".modal").forEach((modal) => {
                modal.classList.remove("active");
            });
        }
    });

    // Обработка отправки формы сотрудника
    const employeeForm = document.getElementById("employee-form");
    if (employeeForm) {
        employeeForm.addEventListener("submit", (e) => {
            e.preventDefault();

            // Здесь будет логика сохранения данных сотрудника
            // В реальном приложении здесь был бы запрос к API

            // Закрываем модальное окно после отправки
            modalOverlay.style.display = "none";
            employeeModal.classList.remove("active");

            // Сбрасываем форму
            employeeForm.reset();
        });
    }

    // Обработка отправки формы оборудования
    const equipmentForm = document.getElementById("equipment-form");
    if (equipmentForm) {
        equipmentForm.addEventListener("submit", (e) => {
            e.preventDefault();

            // Здесь будет логика сохранения данных оборудования
            // В реальном приложении здесь был бы запрос к API

            // Закрываем модальное окно после отправки
            modalOverlay.style.display = "none";
            equipmentModal.classList.remove("active");

            // Сбрасываем форму
            equipmentForm.reset();
        });
    }
}