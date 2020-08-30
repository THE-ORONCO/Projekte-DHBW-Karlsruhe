/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Raum;

import Museum.Bild.Bild;
import Museum.ObjectManagement.MuseumsElementFactory;
import Museum.ObjectManagement.MuseumsManager;
import Museum.Raum.Raum;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RaumTest {

    private static String resourcePfad;
    private static String defaultResourcePfad;

    private Raum raum;
    private static final String raumNr = "r42";
    private static final String beschreibung = "Abstellraum";
    private static final double ausstellungsflaeche = 420.69;
    private static final String ausstellungsthema = "alles was sonst nirgendwo unterkommen kann";
    private static final ArrayList<Bild> bilder =
            new ArrayList<Bild>(Arrays.asList(
                    new Bild("b23", "tolles Exponat", "hier.png", "ein Tolles Exponat das voll toll ist")));


    @Before
    public void setUp() throws Exception {
        String dataRoot = new File("./src/test/resources").getCanonicalPath() + "/";
        resourcePfad = dataRoot + "data/";
        defaultResourcePfad = dataRoot + "default/";
        MuseumsManager.ladeDefaultElemente(defaultResourcePfad);

        this.raum = new Raum( raumNr, ausstellungsflaeche, ausstellungsthema, bilder, beschreibung);
    }

    @Test
    public void getRaumNr() {
        assertEquals(raum.getPrimaryKey(), raumNr);
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
        String raumAlsString = "RaumNr: r42" + System.lineSeparator() +
                "Ausstellungsfläche : 420.690000" + System.lineSeparator() +
                "Ausstellungsthema: alles was sonst nirgendwo unterkommen kann" + System.lineSeparator() +
                "Beschreibung: Abstellraum"+ System.lineSeparator();
        assertEquals(raum.toString(), raumAlsString);
    }

    @Test
    public void testEquals() throws Exception {
        Raum raum2 = new Raum( raumNr, ausstellungsflaeche, ausstellungsthema, bilder, beschreibung);
        assert raum.equals(raum2);
    }

    @Test
    public void testParseToCSV() throws Exception {
        Raum r1 = (Raum) MuseumsElementFactory.createElement(Raum.class, resourcePfad+"/raeume.csv", 2);
        MuseumsManager.remove(Raum.class, r1.getPrimaryKey());
        Raum r2 = MuseumsElementFactory.createRaum(r1.parsToCSV());
        assertEquals(r1, r2);
    }
}