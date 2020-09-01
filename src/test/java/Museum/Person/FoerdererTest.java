package Museum.Person;

import Museum.ObjectManagement.MuseumsElementFactory;
import Museum.ObjectManagement.MuseumsManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FoerdererTest {

    private static String resourcePfad;
    private static String defaultResourcePfad;

    @Before
    public void setup() throws Exception {
        String dataRoot = new File("./src/test/resources").getCanonicalPath() + "/";
        resourcePfad = dataRoot + "data/";
        defaultResourcePfad = dataRoot + "default/";

        MuseumsManager.ladeDefaultElemente(defaultResourcePfad);
    }

    @Test
    public void parsToCSV() throws Exception {
        Foerderer f1 = (Foerderer) MuseumsElementFactory.createElement(Foerderer.class, resourcePfad + "/foerderer.csv", 2);
        MuseumsManager.remove(Foerderer.class, f1.getPrimaryKey());
        Foerderer f2 = MuseumsElementFactory.createFoerderer(f1.parsToCSV());
        assertEquals(f1, f2);
    }
}