/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Exponat.Epoche;
import Museum.Raum.Raum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertThrows;

public class MuseumsManagerTest {

    //TODO MuseumsManagerTests schreiben
    private static String resourcePfad;
    private static String defaultResourcePfad;
    private static File tempResource;

    @Before
    public void setUp() throws Exception {
        MuseumsManager.clearAlles(); // scheinbar braucht es das in Eclipse
        String dataRoot = new File("./src/test/resources").getCanonicalPath() + "/";
        resourcePfad = dataRoot + "data/";
        tempResource = new File(dataRoot + "temp/");
        tempResource.mkdirs();
        defaultResourcePfad = dataRoot + "default/";

        MuseumsManager.ladeDefaultElemente(defaultResourcePfad);

    }

    @After
    public void tearDown() {
        MuseumsManager.clearAlles();
        loescheOrdner(tempResource);
    }

    @Test
    public void contains() {
    }

    @Test
    public void testContains() {
    }

    @Test
    public void persist() {
    }

    @Test
    public void find() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void importieren() {
    }

    @Test
    public void exportieren() throws Exception {
        MuseumsManager.exportieren(Raum.class, tempResource.getAbsolutePath(), true);
        MuseumsManager.exportieren(Raum.class, tempResource.getAbsolutePath(), true);
        MuseumsManager.exportieren(Epoche.class, tempResource.getAbsolutePath(), false);
        assertThrows(IOException.class, () -> {
            MuseumsManager.exportieren(Raum.class, tempResource.getAbsolutePath(), false);
        });
    }

    @Test
    public void generiereUnbenutzenSchluessel() {
    }

    @Test
    public void clearAlles() {
    }

    @Test
    public void ladeDefaultElemente() {
    }

    @Test
    public void getDefault() {
    }

    private static boolean loescheOrdner(File zuLoeschendeDatei) {
        File[] ordnerInhalte = zuLoeschendeDatei.listFiles();
        if (ordnerInhalte != null) {
            for (File datei : ordnerInhalte) {
                loescheOrdner(datei);
            }
        }
        return zuLoeschendeDatei.delete();
    }
}