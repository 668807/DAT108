function leggtilVelkomst(element,kurs) {
    element.insertAdjacentHTML("beforeend",`<h1>Velkommen til ${kurs}</h1>`);
}

const rootElement = document.getElementById("root");
leggtilVelkomst(rootElement, "DAT108");
//leggtilVelkomst(rootElement,"<img src=\"tull\" onerror='window.location=\"https://eple.hvl.no\"'>")