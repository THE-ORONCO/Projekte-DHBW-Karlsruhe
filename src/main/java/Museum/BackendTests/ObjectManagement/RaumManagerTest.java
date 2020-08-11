package Museum.BackendTests.ObjectManagement;

import Museum.Bild.Bild;
import Museum.ObjectManagement.RaumFactory;
import Museum.ObjectManagement.RaumManager;
import Museum.Raum.Raum;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RaumManagerTest {

    private RaumManager raumManager;
    private static final int raumNr = 123;
    private static final String beschreibung = "Toller Raum mit 5 Fenstern";
    private static final double ausstellungsflaeche = 642.9;
    private static final String ausstellungsthema = "Tolle Sachen";
    private static final ArrayList<Bild> bilder =
            new ArrayList<Bild>(Arrays.asList(
                    new Bild("tolles Exponat", "hier.png", "ein Tolles Exponat das voll toll ist")));
    private static final Raum raum = new Raum( raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder);

    @Before
    public void setUp() throws Exception {
        HashMap<Integer, Raum> raume = new HashMap<Integer, Raum>();
        raume.put(raum.getRaumNr(), raum);
        raumManager = new RaumManager(raume);
    }

    @Test
    public void containsObject() {
        assert raumManager.contains(raum);
    }

    @Test
    public void containsRaumNr() {
        assert raumManager.contains(raum.getRaumNr());
    }

    @Test
    public void persistException() {
        Throwable exception = assertThrows(ValueException.class, () -> raumManager.persist(raum));
        assertEquals("Raum mit der raumNr bereits vorhanden!", exception.getMessage());
    }

    @Test
    public void persist(){
    }

    @Test
    public void remove() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void find() {
    }

    @Test
    public void edit() {
    }
}