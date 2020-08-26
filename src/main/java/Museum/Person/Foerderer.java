/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.Exponat.Exponat;
import Museum.ObjectManagement.CSVSeparationLevel;

import java.text.ParseException;
import java.util.ArrayList;

public class Foerderer extends Person {

    private ArrayList<Exponat> gefoerderteExponate;

    //TODO beschreibung ans ende der Signatur

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
    public Foerderer(String foerderNr, String name, String gebDatum, String beschreibung, Kontaktdaten kontakt, ArrayList<Exponat> gefoerderteExponate, Bild bild) throws ParseException {
        super(foerderNr, name, gebDatum, beschreibung, kontakt, bild);
        this.gefoerderteExponate = gefoerderteExponate;
    }

    public Foerderer(String foerderNr, String name, String gebDatum, String beschreibung, Kontaktdaten kontakt, Bild bild) throws ParseException {
        super(foerderNr, name, gebDatum, beschreibung, kontakt, bild);
        this.gefoerderteExponate = new ArrayList<>();
    }

    public ArrayList<Exponat> getGefoerderteExponate() {
        return gefoerderteExponate;
    }

    public Exponat getGefoerdertesExponat(String inventarNR) {
        for (Exponat exponat : this.gefoerderteExponate) {
            if (exponat.getPrimaryKey().equals(inventarNR)) {
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
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
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
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getKontakt().parseToCSV()),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.parsExponateToCSV()),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getBild().parsToCSV())
        };
        return csvData;
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    private String[] parsExponateToCSV(){
        ArrayList<String > exponatCSVData = new ArrayList<>();
        for(Exponat exponat: this.gefoerderteExponate){
            exponatCSVData.add(exponat.getPrimaryKey());
        }
        return exponatCSVData.toArray(new String[exponatCSVData.size()]);
    }

}
