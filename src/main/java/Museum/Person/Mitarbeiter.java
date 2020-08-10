/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.ObjectManagement.ElementSuche;

import java.text.ParseException;
import java.util.ArrayList;

public class Mitarbeiter extends Person{

    private final int mitarbeiterNr;
    private ElementSuche suche;

    /**
     * Mitarbeiter an dem Museum
     * @param mitarbeiterNr Mittarbeiternummer die im System des Museums einen Mitarbeiter identifiziert
     * @param name Name der Person
     * @param gebDatum Geburtsdatum der Person
     * @param beschreibung eine kurze Beschreibung der Person wenn gewünscht
     * @param kontakt Kontaktdaten unter der die Person zu erreichen is
     * @param suche SuchObjekt mit welchem eine Suche in allen Elementen durchgeführt werden kann
     * @throws ParseException wenn Daten bei Kontakt nicht stimmen
     */
    public Mitarbeiter(int mitarbeiterNr, String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche) throws ParseException {
        super(name, gebDatum, beschreibung, kontakt);
        this.mitarbeiterNr = mitarbeiterNr;
        this.suche = suche;
    }

    public int getMitarbeiterNr() {
        return mitarbeiterNr;
    }

    public ElementSuche getSuche() {
        return suche;
    }

    public void setSuche(ElementSuche suche) {
        this.suche = suche;
    }

    @Override
    public String toString () {
        String mitarbeiter = "";
        mitarbeiter += String.format("MitarbeiterNr: %d%n", this.mitarbeiterNr);
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
        return mitarbeiterNr == that.mitarbeiterNr &&
                suche.equals(that.suche);
    }
}
