function leggtilVelkomst(element,kurs) {
	element.insertAdjacentHTML("beforeend","<h1>Velkommen til <span></span></h1>");
	element.querySelector("span").textContent = kurs;
}

const rootElement = document.getElementById("root");
//leggtilVelkomst(rootElement, "DAT108");
leggtilVelkomst(rootElement,"<img src=\"tull\" onerror='window.location=\"https://eple.hvl.no\"'>")