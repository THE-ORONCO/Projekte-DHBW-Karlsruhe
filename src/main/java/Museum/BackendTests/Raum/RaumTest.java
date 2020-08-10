package Museum.BackendTests.Raum;

import Museum.Raum.Raum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RaumTest {

    private Raum raum;
    private static final int raumNr = 42;
    private static final String beschreibung = "Abstellraum";
    private static final double ausstellungsflaeche = 420.69;
    private static final String ausstellungsthema = "alles was sonst nirgendwo unterkommen kann";

    @Before
    public void setUp() throws Exception {
        this.raum = new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema);
    }

    @Test
    public void getRaumNr() {
        assertEquals(raum.getRaumNr(), raumNr);
    }

    @Test
    public void setRaumNr() {
        int neueRaumNr = 2;
        raum.setRaumNr(neueRaumNr);
        assertEquals(raum.getRaumNr(), neueRaumNr);
    }

    @Test
    public void getBeschreibung() {
        assertEquals(raum.getBeschreibung(), beschreibung);
    }

    @Test
    public void setBeschreibung() {
        String neueBeschreibung = "Lagerhalle";
        raum.setBeschreibung(neueBeschreibung);
        assertEquals(raum.getBeschreibung(), neueBeschreibung);
    }

    @Test
    public void getAusstellungsflaeche() {
        assert raum.getAusstellungsflaeche() == ausstellungsflaeche;
    }

    @Test
    public void setAusstellungsflaeche() {
        double neueAusstellungsflaeche = 500.3;
        raum.setAusstellungsflaeche(neueAusstellungsflaeche);
        assert raum.getAusstellungsflaeche() == neueAusstellungsflaeche;
    }

    @Test
    public void getAusstellungsthema() {
        assertEquals(raum.getAusstellungsthema(), ausstellungsthema);
    }

    @Test
    public void setAusstellungsthema() {
        String neuesAusstellungsthema = "keines";
        raum.setAusstellungsthema(neuesAusstellungsthema);
        assertEquals(raum.getAusstellungsthema(), neuesAusstellungsthema);
    }

    @Test
    public void testToString() {
        String raumAlsString = "RaumNr: 42\n" +
                "Ausstellungsfläche : 420.690000\n" +
                "Ausstellungsthema: alles was sonst nirgendwo unterkommen kann\n" +
                "Beschreibung: Abstellraum";
        assertEquals(raum.toString(), raumAlsString);
    }

    @Test
    public void testEquals() {
        Raum raum2 = new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema);
        assert raum.equals(raum2);
    }
}