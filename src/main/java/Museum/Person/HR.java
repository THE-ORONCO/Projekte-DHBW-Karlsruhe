/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.ObjectManagement.ElementSuche;
import Museum.ObjectManagement.MuseumsElementManager;
import Museum.ObjectManagement.MuseumsManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class HR extends Mitarbeiter{

    private MuseumsElementManager mitarbeiterM;
    private MuseumsElementManager foerdererM;

    /**
     * HR im Museum
     *
     * @param mitarbeiterNr Mitarbeiternummer die den Mitarbeiter eindeutig identifiziert
     * @param name          Name der Person
     * @param gebDatum      Geburtsdatum der Person
     * @param beschreibung  eine kurze Beschreibung der Person wenn gewuenscht
     * @param kontakt       Kontaktdaten unter der die Person zu erreichen is
     * @param bild          Bild des Mitarbeiters
     * @throws ParseException wenn Daten bei Kontakt nicht stimmen
     */
    public HR(String mitarbeiterNr, String name, Date gebDatum, String beschreibung, Kontaktdaten kontakt, Bild bild) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, bild);
        this.mitarbeiterM = MuseumsManager.getFoerdererM();
        this.foerdererM = MuseumsManager.getMitarbeiterM();
    }

    public MuseumsElementManager getMitarbeiterM() {
        return mitarbeiterM;
    }

    public MuseumsElementManager getFoerdererM() {
        return foerdererM;
    }
}
