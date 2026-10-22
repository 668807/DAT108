package no.hvl.dat108;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputValidator {

    /*
     * Aldri opprett denne med new DatabaseHelper().
     */
    private final DatabaseHelper databaseHelper;

    /*
     * SjekkBrukernavnController blir opprettet med denne konstruktøren,
     * og da vil Spring opprette et objekt av typen MyDatabaseHelper og
     * putte (injisere) dette inn i validatoren.
     *
     * NB! Vi kan kun gjøre dette hvis InputValidator selv er
     * en Spring komponent, f.eks. en @Controller eller en @Service.
     */
	public InputValidator(DatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	/*
	 * Valideringsmetoden.
	 * "Et gyldig brukernavn er 3 eller flere tegn, og ikke allerede i bruk."
	 */
	public boolean erGyldigBrukernavn(String brukernavn) {
		
		//Steg1: Ikke godta null
		if (brukernavn == null) {
			return false;
		}
		
		//Steg2: Sjekke om 3 eller flere tegn
		if (brukernavn.trim().length() < 3) {
			return false;
		}
		
		//Steg3: Sjekke om brukernavn allerede er i bruk
		return databaseHelper.erBrukernavnLedig(brukernavn);
	}
}




