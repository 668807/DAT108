"use strict";
/**
 * Håndterer webside for innlesing av nye deltagere, visning av deltagere med registrering av tider,
 * og filtrering av deltagerlisten.
 */
class KonkurranseController {
    #tabellelement;
    #aktivFilterelement;

    /**
     * @param {HTMLFormElement} formelement
     * @param {HTMLTableElement} tabellelement
     * @param {HTMLFormElement} filterelement
     */
    constructor(formelement, tabellelement, filterelement) {
        formelement.addEventListener('submit', event => this.#registrer(event));
        filterelement.addEventListener('submit', event => this.#aktiverFilter(event));

        const nullstillElement = filterelement.querySelector('button[type="reset"]');
        nullstillElement.addEventListener('click', event => this.#nullstillFilter());

        this.#tabellelement = tabellelement;
        this.#aktivFilterelement = filterelement.querySelector("span");

        const startnummerInput = formelement.elements["startnummer"];
        startnummerInput.addEventListener('input', event => this.#validerStartnummer(event.target));

        const navnInput = formelement.elements["navn"];
        navnInput.addEventListener('input', event => this.#validerNavn(event.target));

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
     * Viser deltagere som matcher filteret
     *
     * @param {SubmitEvent} event
     */
    #aktiverFilter(event) {
        event.preventDefault();

        const formData = new FormData(event.target);
        const filtertekst = formData.get('filtertekst');
        const kolonne = Number(formData.get('felt'));

        let regexp;
        try {
            regexp = new RegExp(filtertekst);
        } catch (error) {
            this.#nullstillFilter();
            this.#aktivFilterelement.textContent = `Ugyldig mønster "${filtertekst}"`;
            return;
        }

        const tbody = this.#tabellelement.tBodies[0];
        for (const rad of tbody.rows) {
            const tekst = rad.cells[kolonne].textContent;
            if (regexp.test(tekst)) {
                rad.classList.remove('hidden');
            } else {
                rad.classList.add('hidden');
            }
        }

        let feltnavn;
        if (kolonne === 0) {
            feltnavn = "startnummer";
        } else {
            feltnavn = "navn";
        }
        this.#aktivFilterelement.textContent = `Mønster "${filtertekst}" på felt "${feltnavn}"`;
    }

    /**
     * Fjerner filter og viser alle deltagere
     */
    #nullstillFilter() {
        const tbody = this.#tabellelement.tBodies[0];
        for (const rad of tbody.rows) {
            rad.classList.remove('hidden');
        }
        this.#aktivFilterelement.textContent = "Ingen";
    }

    /**
     * Validerer verdi i INPUT felt for startnummer for ny deltager
     *
     * @param {HTMLInputElement} target
     */
    #validerStartnummer(target) {
        let errormessage;
        if (this.#startnummerFinnes(target.valueAsNumber)) {
            errormessage = "Startnummer er i bruk";
        } else if (target.validity.badInput || target.validity.rangeUnderflow || target.validity.stepMismatch) {
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
        let errormessage;
        if (target.validity.patternMismatch) {
            errormessage = "Navn må bestå av ett eller flere delnavn skilt av mellomrom eller bindestrek og hvert delnavn må starte med stor forbokstav etterfulgt av kun små bokstaver";
        } else if (target.validity.valueMissing) {
            errormessage = "Navn mangler";
        } else {
            errormessage = "";
        }
        target.setCustomValidity(errormessage);
        target.title = errormessage; // For Chromium baserte nettlesere
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
        const indeks = HTMLTbodyRowsAsArray.findIndex(rekke => {
            const trnummer = Number(rekke.cells[0].textContent);
            return dennenummer < trnummer;
        });

        const newRow = tbody.insertRow(indeks);
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

        startElement.addEventListener('input', event => this.#validerTider(startElement, sluttElement, lopstidElement));

        sluttElement.addEventListener('input', event => this.#validerTider(startElement, sluttElement, lopstidElement));

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
        sluttElement.title = errormessage; // For Chromium baserte nettlesere
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
const filterelement = document.forms["filter"];
new KonkurranseController(formelement, tabellelement, filterelement);
