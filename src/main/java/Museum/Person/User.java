/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.ExponatManager;
import Museum.ObjectManagement.RaumManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class User extends Mitarbeiter{

    private ExponatManager exponatM;
    private RaumManager raumM;

    public User(int mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche, ExponatManager exponatM, RaumManager raumM) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, suche);
        this.exponatM = exponatM;
        this.raumM = raumM;
    }

    public ExponatManager getExponatM() {
        return exponatM;
    }

    public void setExponatM(ExponatManager exponatM) {
        this.exponatM = exponatM;
    }

    public RaumManager getRaumM() {
        return raumM;
    }

    public void setRaumM(RaumManager raumM) {
        this.raumM = raumM;
    }
}
