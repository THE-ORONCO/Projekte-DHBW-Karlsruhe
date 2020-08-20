/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.Exponat.Exponat;

import java.text.ParseException;
import java.util.ArrayList;

public class Foerderer extends Person {

    private ArrayList<Exponat> gefoerderteExponate;

    /**
     * Ein Förderer unterstützt das Museum mit verschiedenen Exponaten die er sponsort
     *
     * @param foerderNr           eindeutige Identifikationsnummer eines Förderers
     * @param name                Name des Förderers
     * @param gebDatum            Geburtsdatum des Förderers
     * @param beschreibung        zusätzliche Informationen die interessant sein könnten
     * @param kontakt             Liste von verschiedensten Kontaktdaten die über den Förderer bekannt sind
     * @param gefoerderteExponate Exponate die von diesem Förderer gesponsert sind
     * @param bild                Bild des förderers
     * @throws ParseException wenn Telefonnummer oder E-Mail-Adresse falsch formatiert ist
     */
    public Foerderer(String foerderNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ArrayList<Exponat> gefoerderteExponate, Bild bild) throws ParseException {
        super(foerderNr, name, gebDatum, beschreibung, kontakt, bild);
        this.gefoerderteExponate = gefoerderteExponate;
    }

    public Foerderer(String foerderNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ArrayList<Exponat> gefoerderteExponate) throws ParseException {
        super(foerderNr, name, gebDatum, beschreibung, kontakt);
        this.gefoerderteExponate = gefoerderteExponate;
    }

    public Foerderer(String foerderNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, Bild bild) throws ParseException {
        super(foerderNr, name, gebDatum, beschreibung, kontakt, bild);
        this.gefoerderteExponate = new ArrayList<>();
    }

    public Foerderer(String foerderNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt) throws ParseException {
        super(foerderNr, name, gebDatum, beschreibung, kontakt);
        this.gefoerderteExponate = new ArrayList<>();
    }

    public ArrayList<Exponat> getGefoerderteExponate() {
        return gefoerderteExponate;
    }

    public Exponat getGefoerdertesExponat(String inventarNR) {
        for (Exponat exponat : this.gefoerderteExponate) {
            if (exponat.getPrimaryKey() == inventarNR) {
                return exponat;
            }
        }
        return null;
    }

    public void setGefoerderteExponate(ArrayList<Exponat> gefoerderteExponate) {
        this.gefoerderteExponate = gefoerderteExponate;
    }

    public boolean foerdereWeiteresExponat(Exponat exponat) {
        if (!this.gefoerderteExponate.contains(exponat)) {
            this.gefoerderteExponate.add(exponat);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String foerderer = "";
        foerderer += super.toString();
        for (Exponat exponat : this.gefoerderteExponate) {
            foerderer += String.format("Exponat: %s%n", exponat);
        }
        return foerderer;
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbare CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String parsToCSV() {
        return null;
    }

}
