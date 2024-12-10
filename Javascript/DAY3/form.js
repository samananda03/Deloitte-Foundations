function validateForm() {
    var name = document.getElementById("name").value.trim();
    var email = document.getElementById("email").value.trim();
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var outputElement = document.getElementById("output");

    if (name === "") {
        outputElement.textContent = "Name is required.";
        return;
    }

    if (email === "") {
        outputElement.textContent = "Email is required.";
        return;
    }

    if (password === "") {
        outputElement.textContent = "Password is required.";
        return;
    }

    if (password !== confirmPassword) {
        outputElement.textContent = "Passwords do not match.";
        return;
    }

    outputElement.textContent = "Form submitted successfully!";
    outputElement.style.color = "green";
}
