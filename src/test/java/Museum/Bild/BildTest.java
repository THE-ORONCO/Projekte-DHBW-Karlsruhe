/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Bild;

import Museum.Bild.Bild;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BildTest {

    private Bild bild;
    private static final String bildNr = "b2344";
    private static final String altText  = "tolles Bild";
    private static final String dateiName = "bild.png";
    private static final String beschreibung = "voll tolles Bild";

    @Before
    public void setUp() throws Exception {
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
        String bildAlsString = "BildNr: b2344\n" +
                "Alt-Text: tolles Bild\n" +
                "Dateiname: bild.png\n" +
                "Beschreibung: voll tolles Bild";
        assertEquals(bild.toString(), bildAlsString);
    }

    @Test
    public void testEquals() {
        Bild neuesBild = new Bild(bildNr, altText, dateiName, beschreibung);
        assertEquals(bild, neuesBild);
    }
}