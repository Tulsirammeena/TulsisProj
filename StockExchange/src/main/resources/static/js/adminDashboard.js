// admin-dashboard.js

// Handle dropdown menu
document.addEventListener('DOMContentLoaded', function() {
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
});

// Handle section switching
document.addEventListener('DOMContentLoaded', function() {
    var navLinks = document.querySelectorAll('nav ul li a');
    var sections = document.querySelectorAll('.content-section');

    navLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            var targetSection = document.getElementById(link.getAttribute('data-section'));

            navLinks.forEach(function(navLink) {
                navLink.classList.remove('active');
            });
            link.classList.add('active');

            sections.forEach(function(section) {
                section.style.display = 'none';
            });
            targetSection.style.display = 'block';
        });
    });
});
