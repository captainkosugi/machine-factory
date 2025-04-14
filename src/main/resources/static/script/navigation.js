// navigation.js
document.addEventListener("DOMContentLoaded", () => {
    // Переключение между разделами
    const navLinks = document.querySelectorAll(".sidebar a");

    navLinks.forEach((link) => {
        link.addEventListener("click", function (e) {
            // Удаляем активный класс у всех ссылок
            navLinks.forEach((link) => link.classList.remove("active"));

            // Добавляем активный класс текущей ссылке
            this.classList.add("active");

            // На мобильных устройствах закрываем сайдбар после выбора раздела
            if (window.innerWidth <= 768) {
                document.getElementById("sidebar").classList.remove("active");
            }
        });
    });

    // Переключение сайдбара
    const sidebarToggle = document.getElementById("sidebar-toggle");
    const sidebar = document.getElementById("sidebar");

    sidebarToggle.addEventListener("click", () => {
        if (window.innerWidth <= 768) {
            sidebar.classList.toggle("active");
        } else {
            sidebar.classList.toggle("collapsed");
        }
    });

    // Закрытие сайдбара при клике вне его на мобильных устройствах
    document.addEventListener("click", (e) => {
        if (
        window.innerWidth <= 768 &&
        !e.target.closest("#sidebar") &&
        !e.target.closest("#sidebar-toggle") &&
        sidebar.classList.contains("active")
        ) {
            sidebar.classList.remove("active");
        }
    });

    // Обработка изменения размера окна
    window.addEventListener("resize", () => {
        if (window.innerWidth > 768) {
            sidebar.classList.remove("active");
        }
    });
});