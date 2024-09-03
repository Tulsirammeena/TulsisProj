document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('signupForm');
    
    const panInput = document.getElementById('pan');
    const phoneInput = document.getElementById('phone');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    
    const panError = document.getElementById('panError');
    const phoneError = document.getElementById('phoneError');
    const emailError = document.getElementById('emailError');
    const passwordError = document.getElementById('passwordError');
    
    // Validation functions
    const validatePAN = () => {
        const panPattern = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/; // Example PAN format
        if (!panPattern.test(panInput.value)) {
            panError.textContent = 'Invalid PAN format.';
            return false;
        } else {
            panError.textContent = '';
            return true;
        }
    };

    const validatePhone = () => {
        const phonePattern = /^[0-9]{10}$/; // Example phone format (10 digits)
        if (!phonePattern.test(phoneInput.value)) {
            phoneError.textContent = 'Invalid phone number.';
            return false;
        } else {
            phoneError.textContent = '';
            return true;
        }
    };

    const validateEmail = () => {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(emailInput.value)) {
            emailError.textContent = 'Invalid email address.';
            return false;
        } else {
            emailError.textContent = '';
            return true;
        }
    };

    const validatePassword = () => {
        if (passwordInput.value.length < 6) { // Example minimum length
            passwordError.textContent = 'Password must be at least 6 characters long.';
            return false;
        } else {
            passwordError.textContent = '';
            return true;
        }
    };
    
    // Event listeners for onblur (losing focus)
    panInput.addEventListener('blur', validatePAN);
    phoneInput.addEventListener('blur', validatePhone);
    emailInput.addEventListener('blur', validateEmail);
    passwordInput.addEventListener('blur', validatePassword);
    
    // Handle form submission
    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        
        const isPANValid = validatePAN();
        const isPhoneValid = validatePhone();
        const isEmailValid = validateEmail();
        const isPasswordValid = validatePassword();
        
        if (isPANValid && isPhoneValid && isEmailValid && isPasswordValid) {
            const formData = new FormData(form);
            const response = await fetch('/register', {
                method: 'POST',
                body: JSON.stringify({
                    firstName: formData.get('firstName'),
                    lastName: formData.get('lastName'),
                    pan: formData.get('pan'),
                    phone: formData.get('phone'),
                    email: formData.get('email'),
                    password: formData.get('password')
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            
            if (response.ok) {
                alert('Registration successful');
                form.reset();
            } else {
                alert('Registration failed');
            }
        } else {
            alert('Please correct the errors in the form.');
        }
    });
});
