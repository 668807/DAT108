"use strict";

/**
 * Klasse for å håndtere HTML-element.
 * Data i input element blir vist på websiden etter klikk på knapp.
 * på websiden.
 */
class DemoGUI {
    #root;
    #inputElement;
    #outputElement;
    
    constructor(root) {
        this.#root = root;

        this.#inputElement = this.#root.querySelector("input[data-value]"); // Relativ
        this.#outputElement = this.#root.querySelector("span[data-show]"); // Relativ
        const bt = this.#root.querySelector("button[data-button]"); // Relativ

        bt.addEventListener("click",this.#showData.bind(this));
    }

    #showData () {
        this.#outputElement.textContent = this.#inputElement.value;
    }
}

function initC1() {
    const rootElement = document.getElementById("c1"); // Absolutt
    new DemoGUI(rootElement);
}
document.addEventListener("DOMContentLoaded",initC1);

function initC2() {
    const rootElement = document.getElementById("c2"); // Absolutt
    new DemoGUI(rootElement);
}
document.addEventListener("DOMContentLoaded",initC2);

