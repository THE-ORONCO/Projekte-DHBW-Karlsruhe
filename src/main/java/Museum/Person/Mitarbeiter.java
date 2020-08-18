/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import java.text.ParseException;
import java.util.ArrayList;

public class Mitarbeiter extends Person {

    /**
     * Mitarbeiter an dem Museum
     *
     * @param name         Name der Person
     * @param gebDatum     Geburtsdatum der Person
     * @param beschreibung eine kurze Beschreibung der Person wenn gewünscht
     * @param kontakt      Kontaktdaten unter der die Person zu erreichen is
     * @throws ParseException wenn Daten bei Kontakt nicht stimmen
     */
    public Mitarbeiter(String mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt) throws ParseException {
        super(mitarbeiterNr, name, gebDatum, beschreibung, kontakt);
    }

    @Override
    public String toString() {
        String mitarbeiter = "";
        mitarbeiter += String.format("MitarbeiterNr: %s%n", this.getPrimaryKey());
        mitarbeiter += String.format("Rolle: %s%n", this.getClass().getName());
        mitarbeiter += super.toString();
        return mitarbeiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mitarbeiter that = (Mitarbeiter) o;
        return getPrimaryKey() == that.getPrimaryKey();
    }
}
