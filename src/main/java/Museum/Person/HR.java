package Museum.Person;

import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.PersonenManager;

import java.util.ArrayList;
import java.util.Date;

public class HR extends Mitarbeiter{

    private PersonenManager personenM;

    public HR(int mitarbeiterNr, String name, Date gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche, PersonenManager personenM) {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, suche);
        this.personenM = personenM;
    }

    public PersonenManager getPersonenM() {
        return personenM;
    }

    public void setPersonenM(PersonenManager personenM) {
        this.personenM = personenM;
    }
}
