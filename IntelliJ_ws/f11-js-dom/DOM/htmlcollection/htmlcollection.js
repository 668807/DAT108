"use strict";

/**
 * Klasse for å vise nytt tilfeldig tall i websiden.
 */
class NumberGUI {
    #HTMLTable;
    #HTMLRowsLiving;
    #HTMLRowsStatic;

    constructor(rootElement) {
        console.clear();
        this.#HTMLTable = rootElement.getElementsByTagName("table")[0];

        this.#HTMLRowsLiving = this.#HTMLTable.getElementsByTagName("tr");
        this.#HTMLRowsStatic = this.#HTMLTable.querySelectorAll("tr");

        rootElement.getElementsByTagName("button")[0].addEventListener("click", this.#addNumber.bind(this));

        this.#showNumberCount();
    }

    /**
     * Metode som viser liste-lengdene i konsollet.
     */
    #showNumberCount() {
        console.log(`Listen this.#HTMLRowsLiving har ${this.#HTMLRowsLiving.length} elementer`);
        console.log(`Listen this.#HTMLRowsStatic har ${this.#HTMLRowsStatic.length} elementer`);
    }

    /**
     * Metode legger til ny tabellrekke, og legger inn et tilfeldig tall i tabellrekken.
     */
    #addNumber() {
        const newRow = this.#HTMLTable.insertRow(-1);// Ny siste rekke
        newRow.insertCell(-1).innerText = `Tall ${this.#HTMLTable.rows.length}:`;
        newRow.insertCell(-1).innerText = Math.trunc(Math.random() * 100);

        this.#showNumberCount();
    }
}


function init() {
    const rootElement = document.getElementById("root");
    new NumberGUI(rootElement);

}
document.addEventListener("DOMContentLoaded", init);
