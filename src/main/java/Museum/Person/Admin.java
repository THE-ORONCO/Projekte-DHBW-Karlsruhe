/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.MuseumsManager;

import java.text.ParseException;
import java.util.ArrayList;

public class Admin extends Mitarbeiter {

    private MuseumsManager museumsM;

    public Admin(String mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche, MuseumsManager museumsM) throws ParseException {
        super("a" + mitarbeiterNr, name, gebDatum, beschreibung, kontakt, suche);
        this.museumsM = museumsM;
    }

    public MuseumsManager getMuseumsM() {
        return museumsM;
    }

    public void setMuseumsM(MuseumsManager museumsM) {
        this.museumsM = museumsM;
    }
}
