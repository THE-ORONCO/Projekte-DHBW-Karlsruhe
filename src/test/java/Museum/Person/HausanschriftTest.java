/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HausanschriftTest extends AnschriftTest {

    private Hausanschrift hausanschrift;
    private static final String name= "Theo Roncoletta";
    private static final String strasse= "Tennesseeallee";
    private static final int hausnummer= 28;
    private static final int plz= 76149;
    private static final String stadt = "Karlsruhe";
    private static final String land = "Deutschland";

    @Before
    public void setUp(){
        this.hausanschrift = new Hausanschrift(name, strasse, hausnummer, plz, stadt, land);
    }

    @Test
    public void getName() {
        assertEquals(hausanschrift.getName(), name);
    }

    @Test
    public void setName() {
        String neuerName = "Uffi Mc Buffi";
        hausanschrift.setName(neuerName);
        assertEquals(hausanschrift.getName(), neuerName);
    }

    @Test
    public void getStrasse() {
        assertEquals(hausanschrift.getStrasse(), strasse);
    }

    @Test
    public void setStrasse() {
        String neueStrasse = "Gosse hinterm Megges";
        hausanschrift.setStrasse(neueStrasse);
        assertEquals(hausanschrift.getStrasse(), neueStrasse);
    }

    @Test
    public void getHausnummer() {
        assertEquals(hausanschrift.getHausnummer(), hausnummer);
    }

    @Test
    public void setHausnummer() {
        int neueHausnummer = 69;
        hausanschrift.setHausnummer(neueHausnummer);
        assertEquals(hausanschrift.getHausnummer(), neueHausnummer);
    }

    @Test
    public void testToString() {
        String hausanschriftAlsString = "Theo Roncoletta" +System.lineSeparator() +
                "Tennesseeallee 28" +System.lineSeparator() +
                "76149 Karlsruhe" +System.lineSeparator() +
                "Deutschland";
        assertEquals(hausanschrift.toString(), hausanschriftAlsString);
    }

    @Test
    public void testEquals() {
        Hausanschrift hausanschrift2 = new Hausanschrift(name, strasse, hausnummer, plz, stadt, land);
        assert hausanschrift.equals(hausanschrift2);
    }
}