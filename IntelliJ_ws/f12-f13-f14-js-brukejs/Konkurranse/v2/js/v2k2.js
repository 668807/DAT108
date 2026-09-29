"use strict";

/* Momenter:
 * Litt mer om tabeller
 * Demo data
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
        formelement.addEventListener('submit', event =>  this.#registrer(event));

        this.#tabellelement = tabellelement;

        this.#fyllListe();
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

    /**
     * Hjelpemetode som kan fjernes i endelig løsning.
     * Fyller inn data å arbeide under utvikling av applikasjonen
     */
    #fyllListe() {
        this.#tabellelement.classList.remove('hidden');

        const liste = [
            {'startnummer': 567, 'navn': 'Per Persen'},
            {'startnummer': 127, 'navn': 'Anne Annesen'},
            {'startnummer': 838, 'navn': 'Jo Josen'},
            {'startnummer': 57, 'navn': 'Gro Grosen'},
            {'startnummer': 9, 'navn': 'Hanne Hannesen'},
            {'startnummer': 65, 'navn': 'Jo Josen'},
            {'startnummer': 7476, 'navn': 'Mette Metteson'}
        ];

        for (const deltager of liste) {
            this.#visDeltager(deltager);
        }
    }
}

const formelement = document.getElementById("nydeltager");
const tabellelement = document.getElementById("deltagere")
new KonkurranseController(formelement, tabellelement);
