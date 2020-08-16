package Museum.BackendTests.ObjectManagement;

import Museum.Bild.Bild;
import Museum.MuseumsElement;
import Museum.ObjectManagement.MuseumsElementManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MuseumsElementManagerTest {

    private MuseumsElementManager meManager;
    private Bild bild = new Bild("b420", "tolles Bild mit Farbe", "bild.png", "Farbe auf Hintergrund");

    @Before
    public void setUp() throws Exception {
        HashMap<String, MuseumsElement> inhalt = new HashMap<>();

        inhalt.put(bild.getPrimaryKey(), bild);
        meManager = new MuseumsElementManager(inhalt);
    }

    @Test
    public void contains() {
        assert meManager.contains(bild);
        assert meManager.contains(bild.getPrimaryKey());
    }

    @Test
    public void persist() {
        Bild anderesBild = new Bild("b34", "anderes tolles Bild ohne Farbe", "anderesbild.png", "Schwarz auf Weiß");

        try {
            meManager.persist(anderesBild);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert meManager.contains("b34");
        assert meManager.contains(anderesBild);

        try {
            meManager.persist(anderesBild);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Museumselement existiert bereits in diesem MuseumsElementManager!");
        }


        assert meManager.contains("b34");
        assert meManager.contains(anderesBild);
    }

    @Test
    public void find() {
        Bild gefundenesBild = (Bild) meManager.find(Bild.class, "b420");
        assertEquals(bild, gefundenesBild);
    }

    @Test
    public void remove() {
        assert meManager.remove(bild);
        assertFalse(meManager.remove(bild));

        assertFalse(meManager.contains(bild));
        assertFalse(meManager.contains(bild.getPrimaryKey()));
        try {
            meManager.persist(bild);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert meManager.remove(bild.getPrimaryKey());
        assertFalse(meManager.remove(bild.getPrimaryKey()));

        assertFalse(meManager.contains(bild));
        assertFalse(meManager.contains(bild.getPrimaryKey()));
    }
}