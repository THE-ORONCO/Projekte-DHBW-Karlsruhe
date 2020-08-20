/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
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
     * @param personenM
     * @throws ParseException
     */
    public HR(String mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, MuseumsElementManager personenM, Bild bild) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, bild);
        this.personenM = personenM;
    }

    public MuseumsElementManager getPersonenM() {
        return personenM;
    }

    public void setPersonenM(MuseumsElementManager personenM) {
        this.personenM = personenM;
    }
}
