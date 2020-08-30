/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.ObjectManagement.MuseumsElementFactory;
import Museum.ObjectManagement.MuseumsManager;
import Museum.Person.Anschrift;
import Museum.Person.Hausanschrift;
import Museum.Person.Kontaktdaten;
import Museum.Person.Mitarbeiter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MitarbeiterTest extends PersonTest {

    private static String resourcePfad;
    private Mitarbeiter mitarbeiter;
    private static final String name = "Theo Roncoletta";
    private static final String strasse = "Tenesseeallee";
    private static final int hausnummer = 28;
    private static final int plz = 76149;
    private static final String stadt = "Karlsruhe";
    private static final String emailadresse = "theo.roncoletta@posteo.net";
    private static final String teleNr = "+(49)1578 2770476";
    private static final String mitarbeiterNr = "m420";
    private static  Date gebDatum;

    static {
        try {
            gebDatum = new SimpleDateFormat("yyyy.MM.dd").parse("1999.12.23");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static final String beschreibung = "so ein Typ";


    @Before
    public void setUp() throws Exception {
        String dataRoot = new File("./src/test/resources").getCanonicalPath() + "/"; //TODO
        resourcePfad = dataRoot + "data/";
        MuseumsManager.ladeDefaultElemente(dataRoot+"/default");

        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>();
        anschriften.add(new Hausanschrift(name, strasse, hausnummer, plz, stadt));
        Kontaktdaten kontaktdaten = new Kontaktdaten(emailadresse, teleNr, anschriften);
        mitarbeiter = new Mitarbeiter(mitarbeiterNr, name, gebDatum, beschreibung, kontaktdaten, null);

    }

    @Test
    public void getMitarbeiterNr() {
        assertEquals(mitarbeiter.getPrimaryKey(), mitarbeiterNr);
    }


    @Test
    public void testToString() {
        System.out.println(mitarbeiter.toString());
        String mitarbeiterAlsString = "MitarbeiterNr: m420" + System.lineSeparator() +
                "Rolle: Museum.Person.Mitarbeiter" +System.lineSeparator() +
                "Name: Theo Roncoletta" +System.lineSeparator() +
                "Geburtsdatum: Thu Dec 23 00:00:00 CET 1999" +System.lineSeparator() +
                "Beschreibung: so ein Typ" +System.lineSeparator() +
                "Kontakt:" +System.lineSeparator() +
                "Anschrift:" +System.lineSeparator() +
                "Hausanschrift:" +System.lineSeparator() +
                "Theo Roncoletta" +System.lineSeparator() +
                "Tenesseeallee 28" +System.lineSeparator() +
                "76149 Karlsruhe" +System.lineSeparator() +
                "Telefon: +(49)1578 2770476" +System.lineSeparator() +
                "E-Mail: theo.roncoletta@posteo.net";
        assertEquals(mitarbeiter.toString(), mitarbeiterAlsString);
    }

    @Test
    public void testEquals() throws ParseException {
        ArrayList<Anschrift> neueAnschriften = new ArrayList<>();
        neueAnschriften.add(new Hausanschrift(name, strasse, hausnummer, plz, stadt));
        Kontaktdaten neueKontaktdaten = new Kontaktdaten(emailadresse, teleNr, neueAnschriften);
        Bild neuesBild = null;
        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(mitarbeiterNr, name, gebDatum, beschreibung, neueKontaktdaten, neuesBild);

        assertEquals(mitarbeiter, neuerMitarbeiter);
    }

    @Test
    public void testParseToCSV() throws Exception {
        Mitarbeiter m1 = (Mitarbeiter) MuseumsElementFactory.createElement(Mitarbeiter.class, resourcePfad + "mitarbeiter.csv", 2);
        MuseumsManager.remove(m1.getClass(), m1.getPrimaryKey());
        Mitarbeiter m2 = MuseumsElementFactory.createMitarbeiter(m1.parsToCSV());
        assertEquals(m1, m2);
    }
}