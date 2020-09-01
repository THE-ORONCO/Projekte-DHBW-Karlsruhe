/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Bild.Bild;
import Museum.ObjectManagement.MuseumsElementManager;
import Museum.ObjectManagement.MuseumsManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class User extends Mitarbeiter{

    private MuseumsElementManager exponatM;
    private MuseumsElementManager raumM;

    /**
     * User im Museum
     *
     * @param mitarbeiterNr Mitarbeiternummer die den Mitarbeiter eindeutig identifiziert
     * @param name          Name der Person
     * @param gebDatum      Geburtsdatum der Person
     * @param beschreibung  eine kurze Beschreibung der Person wenn gewuenscht
     * @param kontakt       Kontaktdaten unter der die Person zu erreichen is
     * @param bild          Bild des Mitarbeiters
     * @throws ParseException wenn Daten bei Kontakt nicht stimmen
     */
    public User(String mitarbeiterNr, String name, Date gebDatum, String beschreibung, Kontaktdaten kontakt, Bild bild) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt, bild);
        this.exponatM = MuseumsManager.getExponatM();
        this.raumM = MuseumsManager.getRaumM();
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

    /**
     * Gibt die Namen der Objektattribute zurück
     *
     * @return die Namen der Objektattribute
     */
    public static String[] getCSVHeader() {
        return new String[]{
                "personalNr",
                "name",
                "gebDatum",
                "beschreibung",
                "kontakt",
                "bild"
        };
    }
}
