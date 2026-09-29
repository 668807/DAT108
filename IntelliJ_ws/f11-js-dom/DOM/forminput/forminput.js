function registrerkurs() {
    const inputElement = document.querySelector("input[type=text]");
    const kurs = inputElement.value.trim();
    if (kurs === "") {
        inputElement.value = "";
        return;
    }

    const spanElement = document.querySelector("h1").querySelector("span");
    spanElement.textContent = kurs;
}

const button = document.querySelector("button");
button.addEventListener("click", registrerkurs);
