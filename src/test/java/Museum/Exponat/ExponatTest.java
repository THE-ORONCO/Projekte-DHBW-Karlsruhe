/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Exponat;

import Museum.ObjectManagement.MuseumsElementFactory;
import Museum.ObjectManagement.MuseumsManager;
import de.dhbwka.swe.utils.util.CSVReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExponatTest {

    private static final String[] csvData = "x1;mona lisa;2020.08.01;Lenoard Darfintschi;2;Bild;e11;Italien;420.69, 3.50, 1000;2020.08.01 | Erschaffung der Mona Lisa , 2020.08.02|Mona Lisa wurde von unserem Museum aufgekauft;2020.08.02|Exponat wurde angelegt;2020.08.01|Leonad Dafintschi, 2020.08.02|Das Museum;b2;Die noch nicht weltberümte Mona Lisa von Leonard Dafintschi".split(String.valueOf(CSVReader.DEFAULT_DELIMITER));
    private static Exponat exponat;

    @Before
    public void setUp() throws Exception {
        MuseumsManager.ladeDefaultElemente("/mnt/data/the_oronco/Desktop/Projekte-DHBW-Karlsruhe/resources/default");
        exponat = MuseumsElementFactory.createExponat(csvData);
    }

    @After
    public void tearDown() {
        MuseumsManager.clearAlles();
    }

    //TODO default Elemente implementieren bevor das hier getestet werden kann
    @Test
    public void testEquals() throws Exception {
        MuseumsManager.remove(exponat);
        Exponat neuesExponat = MuseumsElementFactory.createExponat(csvData);
        //assertEquals(exponat, neuesExponat);
    }

    @Test
    public void parsToCSV() throws Exception {
        String[] neueCSVDaten = exponat.parsToCSV();
        MuseumsManager.remove(exponat);
        Exponat neuesExponat = MuseumsElementFactory.createExponat(neueCSVDaten);
        assertEquals(exponat, neuesExponat);
    }
}