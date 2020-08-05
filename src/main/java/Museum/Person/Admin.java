package Museum.Person;

import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.MuseumsManager;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends Mitarbeiter {

    private MuseumsManager museumsM;

    public Admin(int mitarbeiterNr, String name, Date gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche, MuseumsManager museumsM) {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, suche);
        this.museumsM = museumsM;
    }

    public MuseumsManager getMuseumsM() {
        return museumsM;
    }

    public void setMuseumsM(MuseumsManager museumsM) {
        this.museumsM = museumsM;
    }
}
