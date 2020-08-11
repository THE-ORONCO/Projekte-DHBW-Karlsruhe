package Museum.BackendTests.Person;

import Museum.Person.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MitarbeiterTest extends PersonTest {

    private Mitarbeiter mitarbeiter;
    private static final String name = "Théo Roncoletta";
    private static final String strasse = "Tenesseeallee";
    private static final int hausnummer = 28;
    private static final int plz = 76149;
    private static final String stadt = "Karlsruhe";
    private static final String emailadresse = "theo.roncoletta@posteo.net";
    private static final String teleNr = "+(49)1578 2770476";
    private static final String mitarbeiterNr = "m420";
    private static final String gebDatum = "1999.12.23";
    private static final String beschreibung = "so ein Typ";


    @Before
    public void setUp() throws Exception {
        ArrayList<Kontaktdaten> kontakt = new ArrayList<Kontaktdaten>();


        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>();
        anschriften.add(new Hausanschrift(name, strasse, hausnummer, plz, stadt));
        Kontaktdaten heim = new Kontaktdaten(emailadresse, teleNr, anschriften);
        kontakt.add(heim);
        mitarbeiter = new Mitarbeiter(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, null);
        // TODO suche realisieren, damit sie hier getestet werden kann

    }

    @Test
    public void getMitarbeiterNr() {
        assertEquals(mitarbeiter.getPrimaryKey(), mitarbeiterNr);
    }

    @Test
    public void getSuche() {
        //TODO
    }

    @Test
    public void setSuche() {
        //TODO
    }

    @Test
    public void testToString() {
        String mitarbeiterAlsString = "MitarbeiterNr: m420\n" +
                "Rolle: Museum.Person.Mitarbeiter\n" +
                "Name: Théo Roncoletta\n" +
                "Geburtsdatum: Thu Dec 23 00:00:00 CET 1999\n" +
                "Beschreibung: so ein Typ\n" +
                "Kontakt:\n" +
                "Anschrift:\n" +
                "Théo Roncoletta\n" +
                "Tenesseeallee 28\n" +
                "76149 Karlsruhe\n" +
                "Telefon: +(49)1578 2770476\n" +
                "E-Mail: theo.roncoletta@posteo.net";
        assertEquals(mitarbeiter.toString(), mitarbeiterAlsString);
    }

    @Test
    public void testEquals() {
    }
}