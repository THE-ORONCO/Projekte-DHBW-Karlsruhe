/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.*;
import Museum.MuseumsElement;
import Museum.Person.Admin;
import Museum.Person.Foerderer;
import Museum.Person.HR;
import Museum.Person.User;
import Museum.Raum.Raum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MuseumsElementFactoryTest {
    private static String resourcePfad;
    private static String defaultResourcePfad;
    private static Epoche epoche;
    private static Raum raum;
    private static Bild bild;
    private static Foerderer foerderer;
    private static Exponat exponat;


    @Before
    public void setUp() throws Exception {
        MuseumsManager.clearAlles(); // scheinbar braucht es das in Eclipse
        String dataRoot = new File("./src/test/resources").getCanonicalPath() + "/";
        resourcePfad = dataRoot + "data/";
        defaultResourcePfad = dataRoot + "default/";

        MuseumsManager.ladeDefaultElemente(defaultResourcePfad);

        epoche = new Epoche("e15", "Begin der Moderne / 19. Jahrhundert", "Romantik", "ca. 1790 - 1840", "");
        bild = new Bild("b23", "tolles Bild", "bild.png", "tolles Bild");
        raum = new Raum("r5", Double.parseDouble("209.3"), "alles", null, "Raum in dem alles abgestellt wir was aktuell nicht ausgestellt ist");
        foerderer = new Foerderer("f1", "Theo Roncoletta", new SimpleDateFormat("yyyy.MM.dd").parse("1999.12.23"), "So ein Typ", MuseumsElementFactory.createKontaktdaten("theo.roncoletta@gmail.com, 015782770476, SWE-Museumsverwaltungssoftwareprogrammierer co. KG._ Theo Roncoletta_ Tennesseeallee_ 28_ 76149_ Karlsruhe_ Deutschland | 42069_ 76149_ Karlsruhe_ Deutschland".split(CSVSeparationLevel.LEVEL2.rSeparator())), new ArrayList<Exponat>(), (Bild) MuseumsManager.getDefault(Bild.class));
        ArrayList<String> urheber = new ArrayList<>(Collections.singletonList("Lenoard Darfintschi"));
        ArrayList<String> kategorien = new ArrayList<>(Collections.singletonList("Bild"));
        Historie gH = new Historie(new HashMap<Date, Ereignis>() {{
            Ereignis e1 = new Ereignis(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.01"), "Erschaffung der Mona Lisa");
            put(e1.getDatum(), e1);
            Ereignis e2 = new Ereignis(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.02"), "Mona Lisa wurde von unserem Museum aufgekauft");
            put(e2.getDatum(), e2);
        }});
        Historie beaH = new Historie(new HashMap<Date, Ereignis>() {{
            Ereignis e1 = new Ereignis(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.02"), "Exponat wurde angelegt");
            put(e1.getDatum(), e1);
        }});
        Historie besH = new Historie(new HashMap<Date, Ereignis>() {{
            Ereignis e1 = new Ereignis(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.01"), "Leonad Dafintschi");
            put(e1.getDatum(), e1);
            Ereignis e2 = new Ereignis(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.02"), "Das Museum");
            put(e2.getDatum(), e2);
        }});
        exponat = new Exponat("x1", "mona lisa", new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.01"), urheber, 2, kategorien, (Epoche) MuseumsManager.getDefault(Epoche.class), "Italien", new Exponatwert(420.69, 3.50, 1000), gH, beaH, besH, (Bild) MuseumsManager.getDefault(Bild.class), "Die noch nicht weltberuemte Mona Lisa von Leonard Dafintschi");
    }

    @After
    public void tearDown() throws Exception {
        MuseumsManager.clearAlles();
    }


    @Test
    public void createElement() throws Exception {
        Epoche epocheAusCSV = null;

        try {
            epocheAusCSV = (Epoche) MuseumsElementFactory.createElement(Epoche.class, resourcePfad + "epochen.csv", 15);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println(epoche.toString());
    }

    @Test
    public void testCreateElement() {
        ArrayList<MuseumsElement> bilder = new ArrayList<>();
        ArrayList<Bild> zuErstellendeBilder = new ArrayList<>();
        zuErstellendeBilder.add(new Bild("b1", "Bild einer Banane", "bilder/banana.png", "ein Buendel Bananen in der Natur"));
        zuErstellendeBilder.add(new Bild("b2", "Mona Lisa", "bilder/mona.png", "Ein Bild von Mona Lisa"));
        zuErstellendeBilder.add(new Bild("b3", "Der Schrai", "bilder/schrai.png", "Ein Bild von dem Schrai auf der Bruecke"));
        zuErstellendeBilder.add(new Bild("b10", "RaumBild", "bilder/abstellRaum.png", "Bild des Abstellraums"));
        zuErstellendeBilder.add(new Bild("b11", "RaumBild", "bilder/bananenRaum.png", "Bild des Bananenraum"));
        zuErstellendeBilder.add(new Bild("b12", "RaumBild", "bilder/bildRaum.png", "Bild des Bilderraums"));
        zuErstellendeBilder.add(new Bild("b23", "Bild von Theo Roncoletta", "bilder/roncolettaTheo.png", "Bild von Theo Roncoletta"));


        try {
            bilder = MuseumsElementFactory.createElement(Bild.class, resourcePfad + "bilder.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < bilder.size(); i++) {
            assertEquals(bilder.get(i), zuErstellendeBilder.get(i)); // Bild wurde entsprechend der template erstellt
            assert MuseumsManager.contains(Bild.class, bilder.get(i)); // Bild wurde im MuseumsManager abgelegt
        }

        ArrayList<? extends MuseumsElement> epochen = new ArrayList<>();

        try {
            epochen = MuseumsElementFactory.createElement(Epoche.class, resourcePfad + "epochen.csv");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        ArrayList<? extends MuseumsElement> exponate = new ArrayList<>();

        try {
            exponate = MuseumsElementFactory.createElement(Exponat.class, resourcePfad + "exponate.csv");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        // Test ob die Exponate als CSV geparst die gleichen Exponate erstellen wie die Daten der  gelesenen CSV-Datei
        for (MuseumsElement e : exponate) {
            MuseumsManager.remove(Exponat.class, e.getPrimaryKey());
        }
        ArrayList<Exponat> neueExponate = new ArrayList<>();
        for (MuseumsElement e : exponate) {
            try {
                neueExponate.add(MuseumsElementFactory.createExponat(e.parsToCSV()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        for (int i = 0; i < exponate.size(); i++) {
            assertEquals(exponate.get(i), neueExponate.get(i));
        }

    }

    @Test
    public void testCreateElementFromLine() {
        Epoche epocheAusLinie15;
        try {
            epocheAusLinie15 = (Epoche) MuseumsElementFactory.createElement(Epoche.class, resourcePfad + "epochen.csv", 15);
            assertEquals(epocheAusLinie15, epoche);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void createExponat() {
        String[] csvData = "x1;mona lisa;2020.08.01;Lenoard Darfintschi;2;Bild;e11;Italien;420.69, 3.50, 1000;2020.08.01 | Erschaffung der Mona Lisa , 2020.08.02|Mona Lisa wurde von unserem Museum aufgekauft;2020.08.02|Exponat wurde angelegt;2020.08.01|Leonad Dafintschi, 2020.08.02|Das Museum;b2;Die noch nicht weltberuemte Mona Lisa von Leonard Dafintschi".split(CSVSeparationLevel.LEVEL1.rSeparator());
        try {
            Exponat generiertesExponat = MuseumsElementFactory.createExponat(csvData);
            assertEquals(exponat, generiertesExponat);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void createBild() throws Exception {
        String[] csvData = new String[]{"b24", "tolles Bild", "bild.png", "tolles Bild"};
        Bild erstelltesBild = MuseumsElementFactory.createBild(csvData);
        assertEquals(bild, erstelltesBild);
        try {
            MuseumsElementFactory.createBild(csvData);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Bild mit gleichem PrimaryKey exisitert bereits");
        }
    }

    @Test
    public void createRaum() throws Exception {
        String[] csvData = new String[]{"r5", "209.3", "alles", "b10", "x2, x4, x5",
                "Raum in dem alles abgestellt wir was aktuell nicht ausgestellt ist"};
        Raum erstellterRaum = MuseumsElementFactory.createRaum(csvData);
        assertEquals(erstellterRaum, raum);
    }


    @Test
    public void createFoerderer() {
        try {
            Foerderer generierterFoerderer = MuseumsElementFactory.createFoerderer("f1;Theo Roncoletta;1999.12.23;So ein Typ;theo.roncoletta@gmail.com, 015782770476, SWE-Museumsverwaltungssoftwareprogrammierer co. KG._ Theo Roncoletta_ Tennesseeallee_ 28_ 76149_ Karlsruhe_ Deutschland | 42069_ 76149_ Karlsruhe_ Deutschland;e2;b23".split(CSVSeparationLevel.LEVEL1.rSeparator()));
            assertEquals(foerderer, generierterFoerderer);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
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
            assertEquals(e.getMessage(), "Epoche mit gleichem PrimaryKey exisitert bereits");
        }
    }
}