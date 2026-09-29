"use strict";

/* Momenter - denne feiler:
 * Forskjell på funksjon med function kontra pilnotasjon
 */

/**
 * Håndterer webside for innlesing av nye deltagere og visning av deltagere.
 */
class KonkurranseController {

    /**
     * @param {HTMLFormElement} formelement
     */
    constructor(formelement) {
        formelement.addEventListener('submit', function (event) {
            event.preventDefault();
            console.log(this);
            // this.#registrer(event); // Denne vil feile
        });
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
