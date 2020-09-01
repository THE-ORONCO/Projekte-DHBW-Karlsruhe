/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Exponat;

import Museum.Exponat.Ereignis;
import Museum.Exponat.Historie;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HistorieTest {

    private static Historie historie;
    private static Ereignis ereignis1;
    private static final Date datum1 = new Date();
    private static final Date datum2 = new Date();
    private static final String beschreibung1 = "wow, much happen, many ereignisse, wow";
    private static final String beschreibung2 = "wow, much more happen, many more ereignisse, big wow";

    @Before
    public void setUp() throws Exception {
        ereignis1 = new Ereignis(datum1, beschreibung1);
        HashMap<Date, Ereignis> ereignisse = new HashMap<>();
        ereignisse.put(datum1, ereignis1);
        historie = new Historie(ereignisse);
    }

    @Test
    public void addEreignis() {
        Ereignis ereignis2 = new Ereignis(datum2, beschreibung2);
        historie.addEreignis(ereignis2);
        assert historie.getEreignisse().containsKey(datum2);
        assert historie.getEreignisse().containsValue(ereignis2);
    }

    @Test
    public void findEreignis() {
        assertEquals(historie.findEreignis(datum1), ereignis1);
    }

    @Test
    public void modifyEreignis() {
        String modifizierteBeschreibung = "modifizierte Beschreibung";
        Ereignis modifiziertesEreignis = new Ereignis(ereignis1.getDatum(), modifizierteBeschreibung);
        historie.modifyEreignis(modifiziertesEreignis);
        assertEquals(historie.findEreignis(ereignis1.getDatum()), modifiziertesEreignis);
    }

    @Test
    public void testModifyEreignis() {
        String modifizierteBeschreibung = "modifizierte Beschreibung";
        Ereignis modifiziertesEreignis = new Ereignis(ereignis1.getDatum(), modifizierteBeschreibung);
        historie.modifyEreignis(ereignis1.getDatum(), modifizierteBeschreibung);
        assertEquals(historie.findEreignis(ereignis1.getDatum()), modifiziertesEreignis);
    }

    @Test
    public void removeEreignis() {
        historie.removeEreignis(ereignis1);
        assertEquals(historie.findEreignis(datum1), null);
    }

    @Test
    public void testRemoveEreignis() {
        historie.removeEreignis(datum1);
        assertEquals(historie.findEreignis(datum1), null);
    }

    @Test
    public void testToString() {
        String historieAlsString = datum1 + " : wow, much happen, many ereignisse, wow" + System.lineSeparator();
        assertEquals(historie.toString(), historieAlsString);
    }

    @Test
    public void testEquals() {
        HashMap<Date, Ereignis> ereignisse = new HashMap<>();
        ereignisse.put(datum1, ereignis1);
        Historie neueHistorie = new Historie(ereignisse);
        assertEquals(historie, neueHistorie);
    }
}