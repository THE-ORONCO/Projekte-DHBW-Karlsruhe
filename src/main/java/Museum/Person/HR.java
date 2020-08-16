/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.MuseumsElementManager;

import java.text.ParseException;
import java.util.ArrayList;

public class HR extends Mitarbeiter{

    private MuseumsElementManager personenM;

    /**
     * @param name
     * @param gebDatum
     * @param beschreibung
     * @param kontakt
     * @param suche
     * @param personenM
     * @throws ParseException
     */
    public HR(String mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche, MuseumsElementManager personenM) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, suche);
        this.personenM = personenM;
    }

    public MuseumsElementManager getPersonenM() {
        return personenM;
    }

    public void setPersonenM(MuseumsElementManager personenM) {
        this.personenM = personenM;
    }
}
