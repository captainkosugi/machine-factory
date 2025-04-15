
document.addEventListener("DOMContentLoaded", () => {

    const navLinks = document.querySelectorAll(".sidebar a");

    navLinks.forEach((link) => {
        link.addEventListener("click", function (e) {

            navLinks.forEach((link) => link.classList.remove("active"));


            this.classList.add("active");


            if (window.innerWidth <= 768) {
                document.getElementById("sidebar").classList.remove("active");
            }
        });
    });


    const sidebarToggle = document.getElementById("sidebar-toggle");
    const sidebar = document.getElementById("sidebar");

    sidebarToggle.addEventListener("click", () => {
        if (window.innerWidth <= 768) {
            sidebar.classList.toggle("active");
        } else {
            sidebar.classList.toggle("collapsed");
        }
    });


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


    window.addEventListener("resize", () => {
        if (window.innerWidth > 768) {
            sidebar.classList.remove("active");
        }
    });
});