/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Bild;

import Museum.ObjectManagement.MuseumsElementFactory;
import Museum.ObjectManagement.MuseumsManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class BildTest {

    private Bild bild;
    private static String resourcePfad;

    private static final String bildNr = "b2344";
    private static final String altText  = "tolles Bild";
    private static final String dateiName = "bild.png";
    private static final String beschreibung = "voll tolles Bild";

    @Before
    public void setUp() throws Exception {
        String dataRoot = new File("./src/test/resources").getCanonicalPath() + "/";
        resourcePfad = dataRoot + "data/";

        bild = new Bild(bildNr, altText, dateiName, beschreibung);
    }

    @Test
    public void getAltText() {
        assertEquals(bild.getAltText(), altText);
    }

    @Test
    public void setAltText() {
        String neuerAltText = "uff";
        bild.setAltText(neuerAltText);
        assertEquals(bild.getAltText(), neuerAltText);
    }

    @Test
    public void getDateiName() {
        assertEquals(bild.getDateiName(), dateiName);
    }

    @Test
    public void setDateiName() {
        String neuerDateiname = "neuesBild.png";
        bild.setDateiName(neuerDateiname);
        assertEquals(bild.getDateiName(), neuerDateiname);
    }

    @Test
    public void getBeschreibung() {
        assertEquals(bild.getBeschreibung(), beschreibung);
    }

    @Test
    public void setBeschreibung() {
        String neueBeschreibung = " echt tolles Bild";
        bild.setBeschreibung(neueBeschreibung);
        assertEquals(bild.getBeschreibung(), neueBeschreibung);
    }

    @Test
    public void testToString() {
        String bildAlsString = "BildNr: b2344" + System.lineSeparator() +
                "Alt-Text: tolles Bild" +System.lineSeparator() +
                "Dateiname: bild.png" +System.lineSeparator() +
                "Beschreibung: voll tolles Bild";
        assertEquals(bild.toString(), bildAlsString);
    }

    @Test
    public void testEquals() {
        Bild neuesBild = new Bild(bildNr, altText, dateiName, beschreibung);
        assertEquals(bild, neuesBild);
    }

    @Test
    public void parsToCSV() throws Exception {
        Bild generiertesBild1 = (Bild) MuseumsElementFactory.createElement(Bild.class, resourcePfad + "bilder.csv", 2);
        MuseumsManager.remove(Bild.class, generiertesBild1.getPrimaryKey());
        Bild generiertesBild2 = (Bild) MuseumsElementFactory.createBild(generiertesBild1.parsToCSV());
        assertEquals(generiertesBild1, generiertesBild2);
    }
}