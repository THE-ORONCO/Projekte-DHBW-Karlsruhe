/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.PersonenManager;

import java.text.ParseException;
import java.util.ArrayList;

public class HR extends Mitarbeiter{

    private PersonenManager personenM;

    /**
     *
     * @param mitarbeiterNr
     * @param name
     * @param gebDatum
     * @param beschreibung
     * @param kontakt
     * @param suche
     * @param personenM
     * @throws ParseException
     */
    public HR(int mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche, PersonenManager personenM) throws ParseException {
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
