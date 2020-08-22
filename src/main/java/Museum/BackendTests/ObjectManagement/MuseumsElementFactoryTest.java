/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.BackendTests.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.MuseumsElement;
import Museum.ObjectManagement.MuseumsElementFactory;
import Museum.ObjectManagement.MuseumsManager;
import Museum.Person.Admin;
import Museum.Person.Foerderer;
import Museum.Person.HR;
import Museum.Person.User;
import Museum.Raum.Raum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MuseumsElementFactoryTest {
    private static Epoche epoche;
    private static Raum raum;
    private static Bild bild;
    private static User user;
    private static Admin admin;
    private static HR hr;
    private static Foerderer foerderer;
    private static Exponat exponat;

    @Before
    public void setUp() throws Exception {
        epoche = new Epoche("e15", "Begin der Moderne / 19. Jahrhundert", "Romantik", "ca. 1790 - 1840", "");
        bild = new Bild("b23", "tolles Bild", "bild.png", "tolles Bild");
    }

    @After
    public void tearDown() throws Exception {
        MuseumsManager.remove(Epoche.class, epoche.getPrimaryKey());
        MuseumsManager.remove(Bild.class, bild.getPrimaryKey());

    }

    @Test
    public void createElement() throws Exception {
        Epoche epocheAusCSV = null;

        try {
            epocheAusCSV = (Epoche) MuseumsElementFactory.createElement(Epoche.class, "/mnt/data/the_oronco/Desktop/Projekte-DHBW-Karlsruhe/src/main/java/Museum/BackendTests/ObjectManagement/data/epochen.csv", 15);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateElement() {
        ArrayList<MuseumsElement> bilder = null;
        ArrayList<Bild> zuErstellendeBilder = new ArrayList<>();
        zuErstellendeBilder.add(new Bild("b0", "default Bild", "/default.png", "default Bild"));
        zuErstellendeBilder.add(new Bild("b1", "Bild einer Banane", "/banana.png", "ein Bündel Bananen in der Natur"));
        zuErstellendeBilder.add(new Bild("b2", "Mona Lisa", "/mona.png", "Ein Bild von Mona Lisa"));
        zuErstellendeBilder.add(new Bild("b3", "Der Schrai", "/schrai.png", "Ein Bild von dem Schrai auf der Brücke"));


        try {
            bilder =  MuseumsElementFactory.createElement(Bild.class, "/mnt/data/the_oronco/Desktop/Projekte-DHBW-Karlsruhe/src/main/java/Museum/BackendTests/ObjectManagement/data/bilder.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < bilder.size(); i++){
            assertEquals(bilder.get(i), zuErstellendeBilder.get(i)); // Bild wurde entsprechend der template erstellt
            assert MuseumsManager.contains(Bild.class, bilder.get(i)); // Bild wurde im MuseumsManager abgelegt
        }

    }

    @Test
    public void testCreateElement1() {
    }

    @Test
    public void createExponat() {
    }

    @Test
    public void createBild() throws Exception {
        String[] CSVdata = new String[]{"b24", "tolles Bild", "bild.png", "tolles Bild"};
        Bild erstelltesBild = MuseumsElementFactory.createBild(CSVdata);
        assertEquals(bild, erstelltesBild);
        try {
            MuseumsElementFactory.createBild(CSVdata);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Bild mit gleichem PrimaryKey exisitert bereits");
        }
    }

    @Test
    public void createRaum() {
    }

    @Test
    public void createFoerderer() {
    }

    @Test
    public void createAdmin() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void createHR() {

    }

    @Test
    public void createEpoche() throws Exception {

        String[] CSVdata = new String[]{"e13", "Begin der Moderne / 19. Jahrhundert", "Romantik", "ca. 1790 - 1840", ""};
        Epoche erstellteEpoche = MuseumsElementFactory.createEpoche(CSVdata);
        assertEquals(erstellteEpoche, epoche);
        try {
            MuseumsElementFactory.createEpoche(CSVdata);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Museumselement existiert bereits in diesem MuseumsElementManager!");
        }
    }
}