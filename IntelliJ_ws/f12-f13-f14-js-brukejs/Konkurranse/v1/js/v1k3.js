"use strict";

/* Momenter:
 * Object, kun en instans
 * Alt er public
 * Kan bruke mekanisme closure for å få til parametre og metoder som blir private
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Closures
 * Hvis ingen felt og kun private metoder, bedre å bruke en funksjon eller class
 */

/**
 * Håndterer webside for innlesing av nye deltagere og visning av deltagere.
 */
const konkurranseController = {
    /**
     * @param {HTMLFormElement} formelement
     */
    init(formelement) {
        formelement.addEventListener('submit', this.registrer);
        console.log(this);
    },

    /**
     * Legger til ny deltager med data last fra form-skjema
     *
     * @param {SubmitEvent} event
     */
    registrer(event) {
        event.preventDefault();

        console.log(this.toString());
        console.log(`Hendelse ${event.toString()}`);
    },

    /**
     * Overkjører standard toString()
     *
     * @returns {string}
     */
    toString() {
        return `Verdi av this: ${this}`;
    }
}

const formelement = document.getElementById("nydeltager");
konkurranseController.init(formelement);
