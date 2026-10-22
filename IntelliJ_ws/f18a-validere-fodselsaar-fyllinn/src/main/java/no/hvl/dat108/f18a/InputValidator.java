package no.hvl.dat108.f18a;

import java.time.LocalDate;

public class InputValidator {

    /**
     * "Et gyldig fødselsår er 4 siffer og er mellom 1900 og året vi er i nå".
     *
     * @param aarString Stringen som inneholder året
     * @return En valideringsmelding, tom streng hvis gyldig år.
     */
	static String erGyldigAarstall(String aarString) {
		
		//Steg1: Ikke godta null
		if (aarString == null) {
			return "Du må oppgi et år";
		}

        //Steg2: Ikke godta tomt år
        if (aarString.isBlank()) {
            return "Året kan ikke være blankt";
        }

		//Steg3: Ikke godta om det ikke kan parses
		int aar;
		try {
			aar = Integer.parseInt(aarString);
		} catch (NumberFormatException e) {
            return "Året må være 4 siffer";
		}
	
	    //Steg4: Sjekke om gyldig år
		int iAar = LocalDate.now().getYear();
        if (aar < 1900 || aar > iAar) {
            return "Året må være mellom 1900 og " + iAar;
        }
		return "";
	}
}




