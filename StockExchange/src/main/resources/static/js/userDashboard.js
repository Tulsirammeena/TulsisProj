document.addEventListener('DOMContentLoaded', function() {
    // Dropdown Toggle
    var dropdownToggle = document.getElementById('admin-username');
    var dropdownMenu = document.getElementById('adminUserMenu');

    dropdownToggle.addEventListener('click', function() {
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', function(event) {
        if (!dropdownToggle.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });

    // Content Sections Navigation
    const sections = document.querySelectorAll('.content-section');
    const navLinks = document.querySelectorAll('nav a');

    const showSection = (sectionId) => {
        sections.forEach(section => {
            section.style.display = (section.id === sectionId) ? 'block' : 'none';
        });

        navLinks.forEach(link => {
            link.classList.toggle('active', link.dataset.section === sectionId);
        });
    };

    // Set default section to Portfolio
    showSection('portfolio');

    // Add click event listeners to nav links
    navLinks.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const sectionId = link.dataset.section;
            showSection(sectionId);
        });
    });

    // Modal Handling
    var buyButtons = document.querySelectorAll('.buy-btn');
    var sellButtons = document.querySelectorAll('.sell-btn');
    var watchlistButtons = document.querySelectorAll('.watchlist-btn');
    var buyModal = document.getElementById('buy-modal');
    var sellModal = document.getElementById('sell-modal');
    var closeBtns = document.querySelectorAll('.close-btn');

    buyButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            var stockName = button.getAttribute('data-stock');
            document.getElementById('buy-stock-name').textContent = 'Buy ' + stockName;
            buyModal.style.display = 'flex';
        });
    });

    sellButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            var stockName = button.getAttribute('data-stock');
            document.getElementById('sell-stock-name').textContent = 'Sell ' + stockName;
            sellModal.style.display = 'flex';
        });
    });

    watchlistButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            var stockName = button.getAttribute('data-stock');
            alert(stockName + ' has been added to your watchlist.');
        });
    });

    closeBtns.forEach(function(button) {
        button.addEventListener('click', function() {
            buyModal.style.display = 'none';
            sellModal.style.display = 'none';
        });
    });

    document.getElementById('cancel-buy-btn').addEventListener('click', function() {
        buyModal.style.display = 'none';
    });

    document.getElementById('cancel-sell-btn').addEventListener('click', function() {
        sellModal.style.display = 'none';
    });

    document.getElementById('confirm-buy-btn').addEventListener('click', function() {
        const stockName = document.getElementById('buy-stock-name').textContent.replace('Buy ', '');
        const orderRequest = {
            userId: getUserId(), // Replace with a function to get the logged-in user's ID
            stockId: stockName,
            orderType: 'BUY',
            quantity: 1, // Replace with actual quantity if needed
            status: 'PENDING',
            timestamp: new Date().toISOString()
        };

        fetch('/api/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderRequest)
        })
        .then(response => response.json())
        .then(data => {
            alert('Purchase confirmed!');
            buyModal.style.display = 'none';
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    document.getElementById('confirm-sell-btn').addEventListener('click', function() {
        const stockName = document.getElementById('sell-stock-name').textContent.replace('Sell ', '');
        const orderRequest = {
            userId: getUserId(), // Replace with a function to get the logged-in user's ID
            stockId: stockName,
            orderType: 'SELL',
            quantity: 1, // Replace with actual quantity if needed
            status: 'PENDING',
            timestamp: new Date().toISOString()
        };

        fetch('/api/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderRequest)
        })
        .then(response => response.json())
        .then(data => {
            alert('Sale confirmed!');
            sellModal.style.display = 'none';
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    function calculateTotals() {
        fetch('/api/portfolio')
            .then(response => response.json())
            .then(data => {
                let totalInvestedValue = 0;
                let totalCurrentValue = 0;

                data.holdings.forEach(item => {
                    let investedValue = item.investedValue;
                    let currentValue = item.currentValue;
                    
                    totalInvestedValue += investedValue;
                    totalCurrentValue += currentValue;
                });

                let totalProfit = totalCurrentValue - totalInvestedValue;
                let totalProfitPercent = (totalInvestedValue === 0) ? 0 : ((totalProfit / totalInvestedValue) * 100).toFixed(2);

                document.getElementById('total-invested-value').textContent = `$${totalInvestedValue.toFixed(2)}`;
                document.getElementById('total-current-value').textContent = `$${totalCurrentValue.toFixed(2)}`;
                document.getElementById('total-profit').textContent = `$${totalProfit.toFixed(2)}`;
                document.getElementById('total-profit-percent').textContent = `${totalProfitPercent}%`;
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function updateWatchlist() {
        fetch('/api/stocks')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('#watchlistTable tbody');
                tableBody.innerHTML = '';

                data.stocks.forEach(stock => {
                    const change = stock.currentPrice - stock.previousPrice;
                    const arrowClass = change > 0 ? 'up-arrow' : 'down-arrow';
                    const arrowSymbol = change > 0 ? '▲' : '▼';

                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${stock.name}</td>
                        <td>${stock.currentPrice.toFixed(2)}</td>
                        <td>
                            ${change.toFixed(2)} <span class="arrow ${arrowClass}">${arrowSymbol}</span>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function updateHistory() {
        fetch('/api/orders/history')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('#historyTable tbody');
                tableBody.innerHTML = '';

                data.orders.forEach(order => {
                    const profitPercentage = order.sellPrice ? ((order.sellPrice - order.buyPrice) / order.buyPrice * 100).toFixed(2) : 'N/A';
                    const profitClass = profitPercentage !== 'N/A' && profitPercentage > 0 ? 'profit' : 'loss';
                    const sellPrice = order.sellPrice ? order.sellPrice.toFixed(2) : '-';

                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${order.date}</td>
                        <td>${order.action}</td>
                        <td>${order.name}</td>
                        <td>${order.buyPrice.toFixed(2)}</td>
                        <td>${sellPrice}</td>
                        <td class="${profitClass}">${profitPercentage !== 'N/A' ? profitPercentage + '%' : 'N/A'}</td>
                    `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    // Call the functions to update the sections on page load
    calculateTotals();
    updateWatchlist();
    updateHistory();

    function getUserId() {
        // Replace this with actual logic to get the logged-in user's ID
        return 12345; // Example user ID
    }
});
