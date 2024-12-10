function authUer() {
    const enteredUsername = document.getElementById("un").value;
    const enteredPassword = document.getElementById("pw").value;

    const outputElement = document.getElementById("output");

    if (enteredUsername === "admin" && enteredPassword === "password") {
      outputElement.textContent = "Logged in as admin";
    } else {
      outputElement.textContent = "Invalid login!";
    }
}

