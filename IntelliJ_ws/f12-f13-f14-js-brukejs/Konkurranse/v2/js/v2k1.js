"use strict";

/* Momenter:
 * Bruk av classlist
 * insertRow
 * insertCell
 */

/**
 * Håndterer webside for innlesing av nye deltagere og visning av deltagere.
 */
class KonkurranseController {
    #tabellelement;

    /**
     * @param {HTMLFormElement} formelement
     * @param {HTMLTableElement} tabellelement
     */
    constructor(formelement, tabellelement) {
        formelement.addEventListener('submit', event => this.#registrer(event));

        this.#tabellelement = tabellelement;
    }

    /**
     * Legger til ny deltager med data last fra form-skjema
     *
     * @param {SubmitEvent} event
     */
    #registrer(event) {
        event.preventDefault();

        const formData = new FormData(event.target);
        const deltager = {
            'startnummer': Number(formData.get('startnummer')),
            'navn': formData.get('navn')
        };

        this.#visDeltager(deltager);

        event.target.reset();
    }

    /**
     * Viser deltager i deltagerlisten usortert
     *
     * @param {Object} deltager
     */
    #visDeltager(deltager) {
        const newRow = this.#tabellelement.tBodies[0].insertRow(-1);
        newRow.insertCell(-1).textContent = deltager.startnummer;
        newRow.insertCell(-1).textContent = deltager.navn;
        newRow.insertCell(-1).innerHTML = "<input type='time' step='1'>";
        newRow.insertCell(-1).innerHTML = "<input type='time' step='1'>";
        newRow.insertCell(-1);
        this.#tabellelement.classList.remove('hidden');
    }
}

const formelement = document.getElementById("nydeltager");
const tabellelement = document.getElementById("deltagere")
new KonkurranseController(formelement, tabellelement);
