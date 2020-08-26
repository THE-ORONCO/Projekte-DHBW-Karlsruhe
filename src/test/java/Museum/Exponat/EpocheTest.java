package Museum.BackendTests.Exponat;

import Museum.Exponat.Epoche;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EpocheTest {

    private Epoche e1;
    private Epoche e2;
    private static final String epochenID = "e15";
    private static final String epoche = "Beginn der Moderne / 19. Jahrhundert";
    private static final String stil = "Romantik";
    private static final String zeitalter = "ca. 1790 - 1840";
    private static final String beschreibung = "voll romantisch";

    @Before
    public void setUp() throws Exception {
        e1 = new Epoche(epochenID, epoche, stil, zeitalter, beschreibung);
    }

    @Test
    public void getEpoche() {
        assertEquals(e1.getEpochenName(), epoche);
    }

    @Test
    public void getStilrichtung() {
        assertEquals(e1.getStilrichtung(), stil);
    }

    @Test
    public void getZeitalter() {
        assertEquals(e1.getZeitalter(), zeitalter);
    }

    @Test
    public void testToString() {
        assertEquals(e1.toString(), epoche+" "+ stil+" "+ zeitalter);
    }
}