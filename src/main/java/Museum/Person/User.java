/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.ObjectManagement.MuseumsElementManager;
import Museum.ObjectManagement.MuseumsManager;

import java.text.ParseException;
import java.util.ArrayList;

public class User extends Mitarbeiter{

    private MuseumsElementManager exponatM;
    private MuseumsElementManager raumM;

    public User(String mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, Bild bild) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, bild);
        this.exponatM = MuseumsManager.getExponatManager();
        this.raumM = MuseumsManager.getRaumManager();
    }

    public MuseumsElementManager getExponatM() {
        return exponatM;
    }

    public void setExponatM(MuseumsElementManager exponatM) {
        this.exponatM = exponatM;
    }

    public MuseumsElementManager getRaumM() {
        return raumM;
    }

    public void setRaumM(MuseumsElementManager raumM) {
        this.raumM = raumM;
    }
}
