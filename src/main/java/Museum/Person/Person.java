package Museum.Person;

import Museum.MuseumsElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Person extends MuseumsElement {
    private String name;
    private Date gebDatum;
    private ArrayList<Kontaktdaten> kontakt;

    /**
     * erstelle eine Person mit den gegebenen Daten
     * @param name Name der Person
     * @param gebDatum Geburtsdatum der Person
     * @param beschreibung eine kurze Beschreibung der Person wenn gewünscht
     * @param kontakt Kontaktdaten unter der die Person zu erreichen ist
     */
    public Person(String personenNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt) throws ParseException {
        super(personenNr, beschreibung);
        this.name = name;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        this.gebDatum = sdf.parse(gebDatum);
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

    public ArrayList<Kontaktdaten> getKontakt() {
        return kontakt;
    }

    public void setKontakt(ArrayList<Kontaktdaten> kontakt) {
        this.kontakt = kontakt;
    }

    @Override
    public String toString() {
        /**
         * @return schön formatierter String mit allen Attributen
         */
        String person = "";
        person += String.format("Name: %s%n", this.name);
        person += String.format("Geburtsdatum: %s%n", this.gebDatum);
        person += String.format("Beschreibung: %s%n", this.getBeschreibung());
        for(Kontaktdaten kontakt : this.kontakt){
            person += String.format("Kontakt:%n%s", kontakt);
        }
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                gebDatum.equals(person.gebDatum) &&
                this.getBeschreibung().equals(person.getBeschreibung()) &&
                kontakt.equals(person.kontakt);
    }
}
