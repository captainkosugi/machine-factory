
function initCharts() {
    const ctx = document.getElementById('production-chart')?.getContext('2d');
    if (ctx) {
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Январь', 'Февраль', 'Март'],
                datasets: [{
                    label: 'Производство',
                    data: [100, 150, 120],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
}

document.addEventListener("DOMContentLoaded", () => {
    initCharts();
});