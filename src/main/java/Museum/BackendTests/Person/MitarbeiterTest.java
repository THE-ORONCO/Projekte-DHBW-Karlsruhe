package Museum.BackendTests.Person;

import Museum.Person.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MitarbeiterTest extends PersonTest {

    private Mitarbeiter mitarbeiter;
    private static final String name = "Théo Roncoletta";
    private static final String strasse = "Tenesseeallee";
    private static final int hausnummer = 28;
    private static final int plz = 76149;
    private static final String stadt = "Karlsruhe";
    private static final String emailadresse = "theo.roncoletta@posteo.net";
    private static final String teleNr = "+(49)1578 2770476";
    private static final int mitarbeiterNr = 420;
    private static final String gebDatum = "1999.12.23";
    private static final String beschreibung = "so ein Typ";


    @Before
    private void setUp() throws Exception {
        Person mitarbeiter = null;
        ArrayList<Kontaktdaten> kontakt = new ArrayList<Kontaktdaten>();


        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>();
        anschriften.add(new Hausanschrift(name, strasse, hausnummer, plz, stadt));
        Kontaktdaten heim = new Kontaktdaten(emailadresse, teleNr, anschriften);
        kontakt.add(heim);
        try {
            mitarbeiter = new Mitarbeiter(mitarbeiterNr, name, gebDatum, beschreibung, kontakt,  null);
            // TODO suche realisieren, damit sie hier getestet werden kann
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMitarbeiterNr() {
        assertEquals(mitarbeiter.getMitarbeiterNr(), mitarbeiterNr);
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
        String mitarbeiterAlsString = "";
        mitarbeiterAlsString += String.format("MitarbeiterNr: %d%n", mitarbeiterNr);
        mitarbeiterAlsString += String.format("Rolle: %s%n", this.getClass().getName());
        mitarbeiterAlsString += mitarbeiter.toString();
        assertEquals(mitarbeiter.toString(), mitarbeiterAlsString);
    }

    @Test
    public void testEquals() {
    }
}