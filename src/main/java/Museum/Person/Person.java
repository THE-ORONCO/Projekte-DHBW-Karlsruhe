package Museum.Person;

import Museum.BackendTests.Person.KontaktdatenTest;
import Museum.Bild.Bild;
import Museum.MuseumsElement;
import Museum.ObjectManagement.CSVSeparationLevel;
import Museum.ObjectManagement.MuseumsManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Person extends MuseumsElement {
    private String name;
    private Date gebDatum;
    private Kontaktdaten kontakt; //DIFF ein Kontaktdatenelement reicht
    private Bild bild;

    /**
     * erstelle eine Person mit den gegebenen Daten
     *
     * @param name         Name der Person
     * @param gebDatum     Geburtsdatum der Person
     * @param beschreibung eine kurze Beschreibung der Person wenn gewünscht
     * @param kontakt      Kontaktdaten unter der die Person zu erreichen ist
     * @param bild       Bilder von dieser Person
     */
    public Person(String personenNr, String name, String gebDatum, String beschreibung, Kontaktdaten kontakt, Bild bild) throws ParseException {
        super(personenNr, beschreibung);
        this.name = name;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        this.gebDatum = sdf.parse(gebDatum);
        this.kontakt = kontakt;
        this.bild = bild;
    }

    public Person(String personenNr, String name, String gebDatum, String beschreibung, Kontaktdaten kontakt) throws ParseException {
        this(personenNr, name, gebDatum, beschreibung, kontakt, (Bild) MuseumsManager.find(Bild.class, "b0"));// TODO Default bild in den Museumsmanager ablegen
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

    public Kontaktdaten getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontaktdaten kontakt) {
        this.kontakt = kontakt;
    }

    public Bild getBild() {
        return bild;
    }

    public void setBild(Bild bild) {
        this.bild = bild;
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
        person += String.format("Kontakt:%n%s", this.kontakt.toString());

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

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbare CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public abstract String[] parsToCSV();

}
