/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.ObjectManagement.CSVSeparationLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mitarbeiter extends Person {

    /**
     * Mitarbeiter im Museum
     *
     * @param mitarbeiterNr Mitarbeiternummer die den Mitarbeiter eindeutig identifiziert
     * @param name          Name der Person
     * @param gebDatum      Geburtsdatum der Person
     * @param beschreibung  eine kurze Beschreibung der Person wenn gewuenscht
     * @param kontakt       Kontaktdaten unter der die Person zu erreichen is
     * @param bild          Bild des Mitarbeiters
     * @throws ParseException wenn Daten bei Kontakt nicht stimmen
     */
    public Mitarbeiter(String mitarbeiterNr, String name, Date gebDatum, String beschreibung, Kontaktdaten kontakt, Bild bild) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, bild);
    }

    @Override
    public String toString() {
        String mitarbeiter = "";
        mitarbeiter += String.format("MitarbeiterNr: %s%n", this.getPrimaryKey());
        mitarbeiter += String.format("Rolle: %s%n", this.getClass().getName());
        mitarbeiter += super.toString();
        return mitarbeiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mitarbeiter that = (Mitarbeiter) o;
        return getPrimaryKey().equals(that.getPrimaryKey());
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String[] parsToCSV() {
        String[] csvData = new String[]{
                this.getPrimaryKey(),
                this.getName(),
                new SimpleDateFormat("yyyy.MM.dd").format(this.getGebDatum()),
                this.getBeschreibung(),
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getKontakt().parseToCSV()),
                getBild().getPrimaryKey(),
        };
        return csvData;
    }

    /**
     * Gibt die Namen der Objektattribute zurück
     *
     * @return die Namen der Objektattribute
     */
    public static String[] getCSVHeader() {
        return new String[]{
                "mitarbeiterNr",
                "name",
                "gebDatum",
                "beschreibung",
                "kontakt",
                "bild"
        };
    }
}
