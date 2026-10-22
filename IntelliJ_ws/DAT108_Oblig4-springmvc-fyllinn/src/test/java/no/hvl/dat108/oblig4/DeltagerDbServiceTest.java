package no.hvl.dat108.oblig4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.service.DeltagerDbService;

import java.util.List;

public class DeltagerDbServiceTest {

    @Test
    void finnerDeltagereMedKjentMobil() {
        Deltager deltager = DeltagerDbService.finnDeltagerMedMobil("12345678");
        assertEquals("Knut", deltager.getFornavn());
        assertEquals("Knutsen", deltager.getEtternavn());
    }

    @Test
    void ukjentMobilGirNull() {
        assertNull(DeltagerDbService.finnDeltagerMedMobil("99999999"));
    }

    @Test
    void mobilLikNullGirNull() {
        assertNull(DeltagerDbService.finnDeltagerMedMobil(null));
    }

    @Test
    void kjentMobilErPaameldt() {
        assertTrue(DeltagerDbService.erDeltagerPaameldt("12345678"));
    }

    @Test
    void ukjentMobilErIkkePaameldt() {
        assertFalse(DeltagerDbService.erDeltagerPaameldt("99999999"));
    }

    @Test
    void listeErSortertPaaFulltNavn() {
        List<Deltager> liste = DeltagerDbService.finnAlleSortertPaaFulltNavn();
        for (int i = 1; i < liste.size(); i++) {
            String forrige = liste.get(i - 1).getFulltNavn();
            String denne = liste.get(i).getFulltNavn();
            assertTrue(forrige.compareTo(denne) <= 0);
        }
    }

    @Test
    void lagretDeltagerBlirPaameldt() {
        Deltager deltager = new  Deltager("00000000", "pass", "Test", "Testesen", "mann");
        assertFalse(DeltagerDbService.erDeltagerPaameldt("00000000"));
        DeltagerDbService.lagreDeltager(deltager);
        assertTrue(DeltagerDbService.erDeltagerPaameldt("00000000"));
    }
}
