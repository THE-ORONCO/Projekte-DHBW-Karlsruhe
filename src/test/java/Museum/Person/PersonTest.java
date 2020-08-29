/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Person.Anschrift;
import Museum.Person.Hausanschrift;
import Museum.Person.Kontaktdaten;
import Museum.Person.Person;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PersonTest {
/*

    private Person initialize(){
        Person person = null;
        ArrayList<Kontaktdaten> kontakt = new ArrayList<Kontaktdaten>();


        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>();
        anschriften.add(new Hausanschrift("Theo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe"));
        Kontaktdaten heim = new Kontaktdaten("theo.roncoletta@posteo.net", "+(49)1578 2770476", anschriften);
        kontakt.add(heim);
        try {
            person = new Person("p2312","Theo Roncoletta", "1999.12.23", "so ein Typ", kontakt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Test
    public void getName() throws Exception {
        Person person = initialize();
        assert person.getName().equals("Theo Roncoletta");
    }

    @Test
    public void setName() {
        Person person = initialize();
        String neuerName = "Uffi Mc Bufffi";
        person.setName(neuerName);
        assert person.getName().equals(neuerName);
    }

    @Test
    public void getGebDatum() throws ParseException {
        Person person = initialize();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date gebDatum = sdf.parse("1999.12.23");
        assert person.getGebDatum().equals(gebDatum);
    }

    @Test
    public void setGebDatum() throws ParseException {
        Person person = initialize();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date neuesGebDatum = sdf.parse("1999.23.12");
        person.setGebDatum(neuesGebDatum);
        assert person.getGebDatum().equals(neuesGebDatum);
    }

    @Test
    public void getBeschreibung() {
        Person person = initialize();
        assert person.getBeschreibung().equals("so ein Typ");
    }

    @Test
    public void setBeschreibung() {
        Person person = initialize();
        String neueBeschreibung = "so ein studierender Typ";
        person.setBeschreibung(neueBeschreibung);
        assert person.getBeschreibung().equals(neueBeschreibung);
    }

    @Test
    public void getKontakt() {
        Person person = initialize();
        ArrayList<Kontaktdaten> kontakt = new ArrayList<Kontaktdaten>();
        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>();
        anschriften.add(new Hausanschrift("Theo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe"));
        Kontaktdaten heim = new Kontaktdaten("theo.roncoletta@posteo.net", "+(49)1578 2770476", anschriften);
        kontakt.add(heim);

        assert person.getKontakt().equals(kontakt);
    }

    @Test
    public void setKontakt() {
        Person person = initialize();
        ArrayList<Kontaktdaten> kontakt = new ArrayList<Kontaktdaten>();
        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>();
        anschriften.add(new Hausanschrift("Theo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe"));
        Kontaktdaten heim = new Kontaktdaten("theo.roncoletta@posteo.net", "+(49)1578 2770476", anschriften);
        kontakt.add(heim);

        ArrayList<Anschrift> alteAnschriften = new ArrayList<Anschrift>();
        alteAnschriften.add(new Hausanschrift("Theo Roncoletta", "Blaubeuererstrasse", 55, 89601, "Schelklingen"));
        Kontaktdaten altesHeim = new Kontaktdaten("theo.roncoletta@posteo.net", "07394919186", alteAnschriften);
        ArrayList<Kontaktdaten> alteKontaktdaten = new ArrayList<Kontaktdaten>();
        alteKontaktdaten.add(altesHeim);
        person.setKontakt(kontakt);
        assert person.getKontakt().equals(kontakt);
    }

    @Test
    public void testToString() {
        Person person = initialize();
        String personAlsString = "Name: Theo Roncoletta\n" +
                "Geburtsdatum: Thu Dec 23 00:00:00 CET 1999\n" +
                "Beschreibung: so ein Typ\n" +
                "Kontakt:\n" +
                "Anschrift:\n" +
                "Theo Roncoletta\n" +
                "Tennesseeallee 28\n" +
                "76149 Karlsruhe\n" +
                "Telefon: +(49)1578 2770476\n" +
                "E-Mail: theo.roncoletta@posteo.net";
        assert person.toString().equals(personAlsString);
    }

    @Test
    public void equals() {
        Person person1 = initialize();

        Person person2 = null;
        ArrayList<Kontaktdaten> kontakt = new ArrayList<Kontaktdaten>();


        ArrayList<Anschrift> anschriften = new ArrayList<Anschrift>();
        anschriften.add(new Hausanschrift("Theo Roncoletta", "Tennesseeallee", 28, 76149, "Karlsruhe"));
        Kontaktdaten heim = new Kontaktdaten("theo.roncoletta@posteo.net", "+(49)1578 2770476", anschriften);
        kontakt.add(heim);
        try {
            person2 = new Person("p2312","Theo Roncoletta", "1999.12.23", "so ein Typ", kontakt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert person1.equals(person2);

    }*/
}