package no.hvl.dat108.oblig4;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import no.hvl.dat108.oblig4.model.DeltagerSkjema;
import no.hvl.dat108.oblig4.model.Feilmeldinger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DeltagerSkjemaValideringTest {

    private Validator validator;

    private DeltagerSkjema testSkjema;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        testSkjema = new DeltagerSkjema();
        testSkjema.setFornavn("Test");
        testSkjema.setEtternavn("Testesen");
        testSkjema.setMobil("00000000");
        testSkjema.setPassord("passord");
        testSkjema.setPassordRepetert("passord");
        testSkjema.setKjonn("mann");
    }

    @Test
    void skjemaHarGyldigeInitVerdier() {
        Set<ConstraintViolation<DeltagerSkjema>> violations = validator.validate(testSkjema);
        assertTrue(violations.isEmpty());
    }

    @Test
    void fornavnErObligatorisk() {
        testSkjema.setFornavn(null);
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_FORNAVN_MLD);
    }

    @Test
    void fornavnMedBindestrekOgNorskeTegnGyldig() {
        testSkjema.setFornavn("Øy-Åse");
        sjekkAtSkjemaetErGyldig();
    }

    @Test
    void fornavnMedSifreUgyldig() {
        testSkjema.setFornavn("123");
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_FORNAVN_MLD);
    }

    @Test
    void etternavnMedSifreUgyldig() {
        testSkjema.setEtternavn("123");
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_ETTERNAVN_MLD);
    }

    @Test
    void etternavnMedMellomromUgyldig() {
        testSkjema.setEtternavn("Et navn");
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_ETTERNAVN_MLD);
    }

    @Test
    void mobilFeilAntallSifreUgyldig() {
        testSkjema.setMobil("0000000");
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_MOBIL_MLD);
        testSkjema.setMobil("000000000");
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_MOBIL_MLD);
    }

    @Test
    void passordObligatorisk() {
        testSkjema.setPassord(null);
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_PASSORD_MLD);
    }

    @Test
    void passord4TegnGyldig() {
        testSkjema.setPassord("pass");
        sjekkAtSkjemaetErGyldig();
    }

    @Test
    void passordMindreEnn4TegnUgyldig() {
        testSkjema.setPassord("pas");
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_PASSORD_MLD);
    }

    @Test
    void kjonnObligatorisk() {
        testSkjema.setKjonn(null);
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_KJONN_MLD);
    }

    @Test
    void kjonnFeilInputUgyldig() {
        testSkjema.setKjonn("annet");
        sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(Feilmeldinger.UGYLDIG_KJONN_MLD);
    }

    //Hjelpemetode for å sjekke akkurat en feil
    private void sjekkAtSkjemaetErUgyldigMedDenneEneFeilen(String feilmelding) {

        Set<ConstraintViolation<DeltagerSkjema>> violations = validator.validate(testSkjema);
        assertFalse(violations.isEmpty());
        assertThat(violations).hasSize(1);

        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .contains(feilmelding);

    }

    //Hjelpemetode for å sjekke om skjema er gyldig
    private void sjekkAtSkjemaetErGyldig() {
        Set<ConstraintViolation<DeltagerSkjema>> violations = validator.validate(testSkjema);
        assertTrue(violations.isEmpty());
    }
}
