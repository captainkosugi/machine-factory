import { Chart } from "@/components/ui/chart";

function initCharts() {
    const productionCtx = document.getElementById("production-chart");
    if (productionCtx) {
        new Chart(productionCtx, {
            type: "line",
            data: {
                labels: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь"],
                datasets: [
                    {
                        label: "Объем производства",
                        data: [65, 59, 80, 81, 56, 85],
                        borderColor: "#2563eb",
                        backgroundColor: "rgba(37, 99, 235, 0.1)",
                        tension: 0.3,
                        fill: true,
                    },
                ],
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false,
                    },
                },
                scales: {
                    y: {
                        beginAtZero: true,
                    },
                },
            },
        });
    }


    const staffCtx = document.getElementById("staff-chart");
    if (staffCtx) {
        new Chart(staffCtx, {
            type: "doughnut",
            data: {
                labels: ["Производство", "Инженерия", "Администрация", "Логистика", "Другие"],
                datasets: [
                    {
                        data: [120, 50, 30, 25, 20],
                        backgroundColor: ["#2563eb", "#10b981", "#f59e0b", "#8b5cf6", "#64748b"],
                    },
                ],
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: "bottom",
                    },
                },
            },
        });
    }
}


document.addEventListener("DOMContentLoaded", () => {
    initCharts();
});