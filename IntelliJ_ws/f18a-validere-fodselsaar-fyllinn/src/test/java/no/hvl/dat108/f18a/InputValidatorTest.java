package no.hvl.dat108.f18a;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/*
 * "Et gyldig fødselsår er 4 siffer og er mellom 1900 og året vi er i nå"
 */
public class InputValidatorTest {
	
    int iAar = LocalDate.now().getYear();
	
	@Test
	void nullErUgyldigAar() {
		assertFalse(InputValidator.erGyldigAarstall(null).isBlank());
	}
	
	@Test
	void tomStringErUgyldigAar() {
		assertFalse(InputValidator.erGyldigAarstall("").isBlank());
	}

	@Test
	void ikkeBareSifreErUgyldigAar() {
		assertFalse(InputValidator.erGyldigAarstall("123x").isBlank());
		assertFalse(InputValidator.erGyldigAarstall("-200").isBlank());
		assertFalse(InputValidator.erGyldigAarstall("-1980").isBlank());
		assertFalse(InputValidator.erGyldigAarstall(" 1980").isBlank());
		assertFalse(InputValidator.erGyldigAarstall("1980 ").isBlank());
	}

	@Test
	void ikkeInnenforOmraadeErUgyldigAar() {	
		assertFalse(InputValidator.erGyldigAarstall("123").isBlank());
		assertFalse(InputValidator.erGyldigAarstall("12345").isBlank());
		assertFalse(InputValidator.erGyldigAarstall("1899").isBlank());
		assertFalse(InputValidator.erGyldigAarstall("" + (iAar+1)).isBlank());
	}

	@Test
	void fireSifreErGyldigAar() {	
		assertTrue(InputValidator.erGyldigAarstall("1900").isBlank());
		assertTrue(InputValidator.erGyldigAarstall("1950").isBlank());
		assertTrue(InputValidator.erGyldigAarstall("2000").isBlank());
		assertTrue(InputValidator.erGyldigAarstall("" + iAar).isBlank());
	}
}
