"use strict";

/* Momenter:
 * Lagrer deltagere også i Array
 * - find() for å sjekke Array for om deltager finnes fra før
 * - Array er ikke sortert, kun HTML tabellen
 * Ut - flyttes til senere
 * Array metoder og egenskaper:
 * - length, []
 * - Muterende :
 *    . splice, sort, [], push, pop, enter, unshift ol.
 * - Ikke muterende, returnerer kopi:
 *   . toSpliced, toSorted, filter, map
 */

/**
 * Håndterer webside for innlesing av nye deltagere og visning av deltagere.
 */
class KonkurranseController {
    #tabellelement;
    #deltagere = [];

    /**
     * @param {HTMLFormElement} formelement
     * @param {HTMLTableElement} tabellelement
     */
    constructor(formelement, tabellelement) {
        formelement.addEventListener('submit', event => this.#registrer(event));

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

        if (this.#deltagere.find(
            d => d.startnummer === deltager.startnummer) === undefined
        ) {
            this.#deltagere.push(deltager);
            this.#visDeltager(deltager);
            event.target.reset();
        }
    }

    /**
     * Viser deltager i deltagerlisten plassert mhp. startnummer stigende
     *
     * @param {Object} deltager
     */
    #visDeltager(deltager) {
        const tbody = this.#tabellelement.tBodies[0];
        const HTMLTbodyRowsAsArray = Array.from(tbody.rows);
        const dennenummer = deltager.startnummer;
        const indeks = HTMLTbodyRowsAsArray.findIndex(
            rekke => {
                const trnummer = Number(rekke.cells[0].textContent);
                return dennenummer < trnummer;
            }
        );

        const newRow = this.#tabellelement.tBodies[0].insertRow(indeks);
        newRow.dataset.startnummer = deltager.startnummer;
        newRow.insertCell(-1).textContent = deltager.startnummer;
        newRow.insertCell(-1).textContent = deltager.navn;
        newRow.insertCell(-1).innerHTML = "<td><input type='time' step='1'></td>";
        newRow.insertCell(-1).innerHTML = "<td><input type='time' step='1'></td>";
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
        //console.log(liste);

        for (const deltager of liste) {
            if (this.#deltagere.find(
                d => d.startnummer === deltager.startnummer) === undefined
            ) {
                this.#deltagere.push(deltager);
                this.#visDeltager(deltager);
            }
        }
    }
}

const formelement = document.getElementById("nydeltager");
const tabellelement = document.getElementById("deltagere")
new KonkurranseController(formelement, tabellelement);
