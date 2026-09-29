"use strict";

/* Momenter:
 * event.target
 * FormData
 * Litt om iteratorer
 */

/**
 * Håndterer webside for innlesing av nye deltagere og visning av deltagere.
 */
class KonkurranseController {

    /**
     * @param {HTMLFormElement} formelement
     */
    constructor(formelement) {
        formelement.addEventListener('submit', event => {
            this.#registrer(event)
        });
    }

    /**
     * Legger til ny deltager med data last fra form-skjema
     *
     * @param {SubmitEvent} event
     */
    #registrer(event) {
        event.preventDefault();

        const formData = new FormData(event.target);
        this.#sjekk(...formData);
    }

    #sjekk(tabell) {
        console.log(tabell);
    }

    /**
     * Overkjører standard toString() for forekomst
     *
     * @returns {string}
     */
    toString() {
        return `Forekomst av ${this.constructor.name}`;
    }
}

const formelement = document.getElementById("nydeltager");
new KonkurranseController(formelement);
