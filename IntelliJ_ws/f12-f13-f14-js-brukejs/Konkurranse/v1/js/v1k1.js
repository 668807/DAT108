"use strict";

/* Momenter:
 * strict mode
 * class, eller object eller funksjon
 * private og public felt
 * `` type strenger
 */

/**
 * Håndterer webside for innlesing av nye deltagere og visning av deltagere.
 */
class KonkurranseController {

    /**
     * @param {HTMLFormElement} formelement
     */
    constructor(formelement) {
        formelement.addEventListener('submit', this.#registrer);
    }

    /**
     * Legger til ny deltager med data last fra form-skjema
     *
     * @param {SubmitEvent} event
     */
    #registrer(event) {
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
