"use strict";

/* Momenter:
 * Feil verdi av 'this' og løsning med funksjon med pilnotasjon
 * anonyme funksjoner med pilnotasjon
 */

/**
 * Håndterer webside for innlesing av nye deltagere og visning av deltagere.
 */
class KonkurranseController {

    /**
     * @param {HTMLFormElement} formelement
     */
    constructor(formelement) {
        formelement.addEventListener('submit', event => this.#registrer(event));
    }

    /**
     * Legger til ny deltager med data last fra form-skjema
     *
     * @param {SubmitEvent} event
     */
    #registrer(event) {
        event.preventDefault();

        console.log(this.toString());
        console.log(`Hendelse ${event.toString()}`);
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
