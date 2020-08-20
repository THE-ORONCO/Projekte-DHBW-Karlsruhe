/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.ObjectManagement.CSVSeparationLevel;

import java.text.ParseException;
import java.util.ArrayList;

public class Mitarbeiter extends Person {

    /**
     * Mitarbeiter an dem Museum
     *
     * @param name         Name der Person
     * @param gebDatum     Geburtsdatum der Person
     * @param beschreibung eine kurze Beschreibung der Person wenn gewünscht
     * @param kontakt      Kontaktdaten unter der die Person zu erreichen is
     * @param bild         Bild des Mitarbeiters
     * @throws ParseException wenn Daten bei Kontakt nicht stimmen
     */
    public Mitarbeiter(String mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, Bild bild) throws ParseException {
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
        return getPrimaryKey() == that.getPrimaryKey();
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbare CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String[] parsToCSV() {
        String[] csvData = new String[]{
                this.getPrimaryKey(),
                this.getName(),
                this.getGebDatum().toString(),
                this.getBeschreibung(),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.parsKontakteToCSV()),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getBild().parsToCSV()),
        };
        return csvData;
    }
}
