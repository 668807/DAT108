"use strict";

/* Momenter:
 * Pakke ut fra tabell til variabler
 * Object og metoder for å initsialisere og opprette egenskaper til objekter
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

        const deltager = {};
        const formData = new FormData(event.target);
        for (const [key, value] of formData) {
            deltager[key.trim()] = value.trim();
        }
        console.log(deltager);
        console.log(typeof deltager.startnummer);

        const deltagerAlt = {
            'startnummer': Number(formData.get('startnummer')),
            'navn': formData.get('navn')
        };
        console.log(deltagerAlt);
        console.log(typeof deltagerAlt.startnummer);

        event.target.reset();
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
