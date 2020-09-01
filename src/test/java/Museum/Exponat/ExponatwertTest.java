/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Exponat;

import Museum.Exponat.Exponatwert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExponatwertTest {

    private Exponatwert exponatwert;
    private static final float einkaufswert = (float) 3.42;
    private static final float aktuellerSchaetzwert = (float) 2.4;
    private static final float leihwert = (float) 5.5;
    @Before
    public void setUp() throws Exception {
        exponatwert = new Exponatwert(einkaufswert, aktuellerSchaetzwert, leihwert);
    }

    @Test
    public void getEinkaufsWert() {
        assert exponatwert.getEinkaufsWert() == einkaufswert;
    }

    @Test
    public void getAktuellerSchaetzwert() {
        assert exponatwert.getAktuellerSchaetzwert() == aktuellerSchaetzwert;
    }

    @Test
    public void setAktuellerSchaetzwert() {
        float neuerAktuellerSchaetzwert = (float) 34.2;
        exponatwert.setAktuellerSchaetzwert(neuerAktuellerSchaetzwert);
        assert exponatwert.getAktuellerSchaetzwert() == neuerAktuellerSchaetzwert;
    }

    @Test
    public void getLeihwert() {
        assert exponatwert.getLeihwert() == leihwert;
    }

    @Test
    public void setLeihwert() {

    }

    @Test
    public void testEquals() {
        assertEquals(exponatwert, new Exponatwert(einkaufswert, aktuellerSchaetzwert, leihwert));
    }
}