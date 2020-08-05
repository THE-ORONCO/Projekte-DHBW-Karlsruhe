package Museum.Person;

import java.util.ArrayList;
import java.util.Date;

public class Person {
    private String name;
    private Date gebDatum;
    private String beschreibung;
    private ArrayList<Kontaktdaten> kontakt;

    public Person(String name, Date gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt) {
        this.name = name;
        this.gebDatum = gebDatum;
        this.beschreibung = beschreibung;
        this.kontakt = kontakt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(Date gebDatum) {
        this.gebDatum = gebDatum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public ArrayList<Kontaktdaten> getKontakt() {
        return kontakt;
    }

    public void setKontakt(ArrayList<Kontaktdaten> kontakt) {
        this.kontakt = kontakt;
    }

    @Override
    public String toString() {
        String person = "";
        person += String.format("Name: %s%n", this.name);
        person += String.format("Geburtsdatum: %s%n", this.gebDatum);
        person += String.format("Beschreibung: %s%n", this.beschreibung);
        for(Kontaktdaten kontakt : this.kontakt){
            person += String.format("Kontakt: %s%n", kontakt);
        }
        return person;
    }
}
