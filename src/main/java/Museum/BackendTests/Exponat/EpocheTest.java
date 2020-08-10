package Museum.BackendTests.Exponat;

import Museum.Exponat.Epoche;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EpocheTest {

    private Epoche e1;
    private Epoche e2;
    private static final String epoche = "Beginn der Moderne / 19. Jahrhundert";
    private static final String stil = "Romantik";
    private static final String zeitalter = "ca. 1790 - 1840";

    @Before
    public void setUp() throws Exception {
        e1 = new Epoche(epoche, stil, zeitalter);
        e2 = new Epoche(epoche, stil, "");
    }

    @Test
    public void getEpoche() {
        assertEquals(e1.getEpoche(), epoche);
        assertEquals(e2.getEpoche(), epoche);
    }

    @Test
    public void getStilrichtung() {
        assertEquals(e1.getStilrichtung(), stil);
        assertEquals(e2.getStilrichtung(), stil);
    }

    @Test
    public void getZeitalter() {
        assertEquals(e1.getZeitalter(), zeitalter);
        assertEquals(e2.getZeitalter(), "");
    }

    @Test
    public void testToString() {
        assertEquals(e1.toString(), epoche+" "+ stil+" "+ zeitalter);
        assertEquals(e2.toString(), epoche+" "+ stil+ " ");
    }
}