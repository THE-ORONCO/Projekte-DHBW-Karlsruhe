/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;

import java.text.ParseException;
import java.util.ArrayList;

public class Admin extends Mitarbeiter { //TODO


    public Admin(String mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, Bild bild) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, bild);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbare CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String[] parsToCSV() {
        return super.parsToCSV();
    }
}
