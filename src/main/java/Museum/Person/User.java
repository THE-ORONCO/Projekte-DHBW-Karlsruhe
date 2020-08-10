package Museum.Person;

import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.ExponatManager;
import Museum.ObjectManagement.RaumManager;

import java.util.ArrayList;
import java.util.Date;

public class User extends Mitarbeiter{

    private ExponatManager exponatM;
    private RaumManager raumM;

    public User(int mitarbeiterNr, String name, Date gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche, ExponatManager exponatM, RaumManager raumM) {
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
