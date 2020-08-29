/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Person.Firmenanschrift;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FirmenanschriftTest extends AnschriftTest {

    private Firmenanschrift firmenanschrift;
    private static final String firma = "KIT-Campus-Nord";
    private static final String name = "Theo Roncoletta";
    private static final String strasse = "Tennesseeallee";
    private static final int hausnummer = 28;
    private static final int plz = 76149;
    private static final String stadt = "Karlsruhe";
    private static final String land = "Deutschland";


    @Before
    public void setUp() throws Exception {
        this.firmenanschrift = new Firmenanschrift(firma, name, strasse, hausnummer, plz, stadt, land);
    }

    @Test
    public void getFirma() {
        assertEquals(firmenanschrift.getFirma(), firma);
    }

    @Test
    public void setFirma() {
        String neueFrima = "Evil Inc.";
        firmenanschrift.setFirma(neueFrima);
        assertEquals(firmenanschrift.getFirma(), neueFrima);
    }

    @Test
    public void getName() {
        assertEquals(firmenanschrift.getName(), name);
    }

    @Test
    public void setName() {
        String neuerName = "Uffi Mc Buffi";
        firmenanschrift.setName(neuerName);
        assertEquals(firmenanschrift.getName(), neuerName);
    }

    @Test
    public void getStrasse() {
        assertEquals(firmenanschrift.getStrasse(), strasse);
    }

    @Test
    public void setStrasse() {
        String neueStrasse = "Gosse hinterm Megges";
        firmenanschrift.setStrasse(neueStrasse);
        assertEquals(firmenanschrift.getStrasse(), neueStrasse);
    }

    @Test
    public void getHausnummer() {
        assertEquals(firmenanschrift.getHausnummer(), hausnummer);
    }

    @Test
    public void setHausnummer() {
        int neueHausnummer = 69;
        firmenanschrift.setHausnummer(neueHausnummer);
        assertEquals(firmenanschrift.getHausnummer(), neueHausnummer);
    }

    @Test
    public void testToString() {
        String firmenanschriftAlsString = "KIT-Campus-Nord\n" +
                "Theo Roncoletta\n" +
                "Tennesseeallee 28\n" +
                "76149 Karlsruhe\n" +
                "Deutschland";
        assertEquals(firmenanschrift.toString(), firmenanschriftAlsString);
    }

    @Test
    public void testEquals() {
        Firmenanschrift firmenanschrift2 = new Firmenanschrift(firma, name, strasse, hausnummer, plz, stadt, land);
        assert firmenanschrift.equals(firmenanschrift2);
    }
}