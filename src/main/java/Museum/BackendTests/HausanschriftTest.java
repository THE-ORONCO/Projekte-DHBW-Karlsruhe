package Museum.BackendTests;

import Museum.Person.Hausanschrift;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HausanschriftTest extends AnschriftTest {

    private Hausanschrift hausanschrift;
    private static final String name= "Théo Roncoletta";
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
        String hausanschriftAlsString = "Théo Roncoletta\n" +
                "Tennesseeallee 28\n" +
                "76149 Karlsruhe\n" +
                "Deutschland";
        assertEquals(hausanschrift.toString(), hausanschriftAlsString);
    }

    @Test
    public void testEquals() {
        Hausanschrift hausanschrift2 = new Hausanschrift(name, strasse, hausnummer, plz, stadt, land);
        assert hausanschrift.equals(hausanschrift2);
    }
}