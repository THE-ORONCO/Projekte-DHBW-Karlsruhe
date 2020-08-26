/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Exponat;

import Museum.Exponat.Ereignis;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class EreignisTest {

    private static Ereignis ereignis;
    private static final Date datum = new Date();
    private static final String beschreibung = "wow, much happen, many ereignisse, wow";

    @Before
    public void setUp() throws Exception {
        ereignis = new Ereignis(datum, beschreibung);
    }

    @Test
    public void getDatum() {
        assertEquals(ereignis.getDatum(), datum);
    }

    @Test
    public void setDatum() {
        Date neuesDatum = new Date();
        ereignis.setDatum(neuesDatum);
        assertEquals(ereignis.getDatum(), neuesDatum);
    }

    @Test
    public void getBeschreibung() {
        assertEquals(ereignis.getBeschreibung(), beschreibung);
    }

    @Test
    public void setBeschreibung() {
        String neueBeschreibung = "neue Beschreibung";
        ereignis.setBeschreibung(neueBeschreibung);
        assertEquals(ereignis.getBeschreibung(), neueBeschreibung);
    }

    @Test
    public void testToString() {
        assertEquals(ereignis.toString(), datum + " : wow, much happen, many ereignisse, wow");
    }

    @Test
    public void testEquals() {
        Ereignis neuesEreignis = new Ereignis(datum, beschreibung);
        assert ereignis.equals(neuesEreignis);
    }
}