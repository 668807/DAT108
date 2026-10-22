"use strict";

/* Momenter:
 * setCustomValidity på felt for startnummer og navn
 * Kontrollelement sin egenskap validitystate
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

        const startnummerInput = formelement.elements["startnummer"];
        startnummerInput.addEventListener(
            'input',
            event => this.#validerStartnummer(event.target)
        );

        const navnInput = formelement.elements["navn"];
        navnInput.addEventListener(
            'input',
            event => this.#validerNavn(event.target)
        );

        this.#fyllListe();
        this.#validerStartnummer(startnummerInput);
        this.#validerNavn(navnInput);
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
        this.#validerStartnummer(event.target.elements["startnummer"]);
        this.#validerNavn(event.target.elements["navn"]);
    }

    /**
     * Validerer verdi i INPUT felt for startnummer for ny deltager
     *
     * @param {HTMLInputElement} target
     */
    #validerStartnummer(target) {
        console.log(target.validity);
        let errormessage;
        if (this.#startnummerFinnes(target.valueAsNumber)) {
            errormessage = "Startnummer er i bruk";
        } else if (target.validity.badInput || target.validity.rangeUnderflow || target.validity.stepMismatch ) {
            errormessage = "Startnummer må være et heltall større eller lik 1";
        } else if (target.validity.valueMissing) {
            errormessage = "Startnummer er påkrevd";
        } else {
            errormessage = "";
        }
        target.setCustomValidity(errormessage);
        target.title = errormessage; // For Chromium baserte nettlesere
    }

    /**
     * Validerer verdi i INPUT felt for navn for ny deltager
     *
     * @param {HTMLInputElement} target
     */
    #validerNavn(target) {
        console.log(target.validity);
        let errormessage;
        if (target.validity.patternMismatch) {
            errormessage = "Navn må bestå av ett eller flere delnavn skilt av mellomrom eller bindestrek og hvert delnavn må starte med stor forbokstav etterfulgt av kun små bokstaver";
        } else if (target.validity.valueMissing) {
            errormessage = "Navn mangler";
        } else {
            errormessage = "";
        }
        target.setCustomValidity(errormessage);
        target.title = errormessage; // For Chromium baserte nettleser
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

        const startCelle = newRow.insertCell(-1);
        startCelle.innerHTML = "<input type='time' step='1'>";
        const startElement = startCelle.querySelector("input");

        const sluttCelle = newRow.insertCell(-1);
        sluttCelle.innerHTML = "<input type='time' step='1'>";
        const sluttElement = sluttCelle.querySelector("input");

        const lopstidElement = newRow.insertCell(-1);

        startElement.addEventListener(
            'input',
            event => this.#validerTider(startElement, sluttElement, lopstidElement)
        );

        sluttElement.addEventListener(
            'input',
            event => this.#validerTider(startElement, sluttElement, lopstidElement)
        );

        this.#tabellelement.classList.remove('hidden');
    }

    /**
     * Validerer sluttid mot starttid og viser løpstid for en deltager.
     *
     * @param {HTMLInputElement} startElement
     * @param {HTMLInputElement} sluttElement
     * @param {HTMLTableCellElement} lopstidElement
     */
    #validerTider(startElement, sluttElement, lopstidElement) {
        const start = startElement.valueAsNumber;
        const slutt = sluttElement.valueAsNumber;

        const beggeUtfylt = sluttElement.value !== "" && startElement.value !== "";

        let errormessage;

        if (beggeUtfylt && slutt <= start) {
            errormessage = "Sluttid må være etter starttid";
            lopstidElement.textContent = "";
        } else if (beggeUtfylt) {
            errormessage = "";
            lopstidElement.textContent = this.#formaterTid(slutt - start);
        } else {
            errormessage = "";
            lopstidElement.textContent = "";
        }
        sluttElement.setCustomValidity(errormessage);
        sluttElement.title = errormessage;
    }

    /**
     * Hjelpemetode for omgjøring av ett-sifret tall til tekst. (3 -> 03)
     *
     * @param {number} tall
     * @returns {string}
     */
    #toSifre(tall) {
        if (tall < 10) {
            return `0${tall}`;
        }
        return `${tall}`;
    }

    /**
     * Hjelpemetode for å formatere løpstid i millisekunder som tt:mm:ss
     *
     * @param {number} millisekunder
     * @returns {string}
     */
    #formaterTid(millisekunder) {
        const totalSekunder = millisekunder / 1000;
        const timer = Math.trunc(totalSekunder / 3600);
        const minutter = Math.trunc((totalSekunder % 3600) / 60);
        const sekunder = totalSekunder % 60;
        return `${this.#toSifre(timer)}:${this.#toSifre(minutter)}:${this.#toSifre(sekunder)}`;
    }

    /**
     * Sjekker om input-parameter startnummer allerede finnes i deltagerlisten
     *
     * @param {number} startnummer
     * @returns {boolean}
     */
    #startnummerFinnes(startnummer) {
        const tbody = this.#tabellelement.tBodies[0];
        const element = tbody.querySelector(`tr[data-startnummer="${startnummer}"]`);
        return element !== null;
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
            if (!this.#startnummerFinnes(deltager.startnummer)) {
                this.#visDeltager(deltager);
            }
        }
    }
}

const formelement = document.forms["nydeltager"];
const tabellelement = document.getElementById("deltagere");
new KonkurranseController(formelement, tabellelement);
