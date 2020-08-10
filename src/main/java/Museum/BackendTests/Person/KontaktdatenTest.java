/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.BackendTests.Person;

import Museum.Person.Anschrift;
import Museum.Person.Hausanschrift;
import Museum.Person.Kontaktdaten;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class KontaktdatenTest {

    private Kontaktdaten initialize() {
        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>(
                Arrays.asList(new Hausanschrift("Théo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe")));
        ArrayList<String> emails = new ArrayList<String>(Arrays.asList("theo.roncoletta@posteo.net"));
        ArrayList<String> teleNr = new ArrayList<String>(Arrays.asList("+(49)1578 2770476"));
        Kontaktdaten kontakt = new Kontaktdaten(emails, teleNr, anschriften);
        return kontakt;
    }

    @Test
    public void getEmailAdressen() {
        Kontaktdaten kontakt = initialize();
        ArrayList<String> emails = new ArrayList<String>(Arrays.asList("theo.roncoletta@posteo.net"));
        assert kontakt.getEmailAdressen().equals(emails);
    }

    @Test
    public void setEmailAdressen() {
        Kontaktdaten kontakt = initialize();
        ArrayList<String> neueEmails = new ArrayList<String>(Arrays.asList("theo.roncoletta@gmail.com"));
        kontakt.setEmailAdressen(neueEmails);
        assert kontakt.getEmailAdressen().equals(neueEmails);
    }

    @Test
    public void getTeleNr() {
        Kontaktdaten kontakt = initialize();
        ArrayList<String> teleNr = new ArrayList<String>(Arrays.asList("+(49)1578 2770476"));
        assert kontakt.getTeleNr().equals(teleNr);
    }

    @Test
    public void setTeleNr() {
        Kontaktdaten kontakt = initialize();
        ArrayList<String> neueTeleNr = new ArrayList<String>(Arrays.asList("+07394 916186"));
        kontakt.setTeleNr(neueTeleNr);
        assert kontakt.getTeleNr().equals(neueTeleNr);
    }

    @Test
    public void getAnschriften() {
        Kontaktdaten kontakt = initialize();
        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>(
                Arrays.asList(new Hausanschrift("Théo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe")));
        assert kontakt.getAnschriften().equals(anschriften);
    }

    @Test
    public void setAnschriften() {
        Kontaktdaten kontakt = initialize();
        ArrayList<Anschrift> neueAnschrift = new ArrayList<Anschrift>(
                Arrays.asList(new Hausanschrift("Théo Roncoletta", "Blaubeurerstraße", 55, 889601, "Schelklingen")));
        kontakt.setAnschriften(neueAnschrift);
        assert kontakt.getAnschriften().equals(neueAnschrift);
    }

    @Test
    public void addAnschrift() {
        Kontaktdaten kontakt = initialize();
        ArrayList<Anschrift> neueAnschrift = new ArrayList<Anschrift>(
                Arrays.asList(new Hausanschrift("Théo Roncoletta", "Blaubeurerstraße", 55, 889601, "Schelklingen")));
        Anschrift extraAnschrift = new Hausanschrift("Théo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe");
        kontakt.setAnschriften(neueAnschrift);
        kontakt.addAnschrift(extraAnschrift);
        neueAnschrift.add(extraAnschrift);
        assert kontakt.getAnschriften().equals(neueAnschrift);
    }

    @Test
    public void remove() {
        Kontaktdaten kontakt = initialize();
        ArrayList<Anschrift> neueAnschrift = new ArrayList<Anschrift>(
                Arrays.asList(new Hausanschrift("Théo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe")));
        Anschrift extraAnschrift = new Hausanschrift("Théo Roncoletta", "Blaubeurerstraße", 55, 889601, "Schelklingen");

        kontakt.addAnschrift(extraAnschrift);
        neueAnschrift.remove(extraAnschrift);


        assert kontakt.remove(extraAnschrift);
        assert kontakt.getAnschriften().equals(neueAnschrift);
    }

    @Test
    public void testToString() {
        Kontaktdaten kontakt = initialize();
        String kontaktAlsString = "Anschrift:\n" +
                "Théo Roncoletta\n" +
                "Tennesseeallee 28\n" +
                "76149 Karlsruhe\n" +
                "Telefon: +(49)1578 2770476\n" +
                "E-Mail: theo.roncoletta@posteo.net";
        System.out.println(kontakt.toString());
        assert kontakt.toString().equals(kontaktAlsString);
    }

    @Test
    public void testEquals() {
        Kontaktdaten kontakt1 = initialize();
        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>(
                Arrays.asList(new Hausanschrift("Théo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe")));
        ArrayList<String> emails = new ArrayList<String>(Arrays.asList("theo.roncoletta@posteo.net"));
        ArrayList<String> teleNr = new ArrayList<String>(Arrays.asList("+(49)1578 2770476"));
        Kontaktdaten kontakt2 = new Kontaktdaten(emails, teleNr, anschriften);

        assert kontakt1.equals(kontakt2);
    }
}