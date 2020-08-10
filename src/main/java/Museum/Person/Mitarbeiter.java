package Museum.Person;

import Museum.ObjectManagement.ElementSuche;

import java.util.ArrayList;
import java.util.Date;

public class Mitarbeiter extends Person{

    private final int mitarbeiterNr;
    private ElementSuche suche;

    public Mitarbeiter(int mitarbeiterNr, String name, Date gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ElementSuche suche) {
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
    public String getName() {
        String mitarbeiter = "";
        mitarbeiter += String.format("MitarbeiterNr: %d%n", this.mitarbeiterNr);
        mitarbeiter += String.format("Rolle: %s%n", this.getClass().getName());
        mitarbeiter += super.toString();
        return mitarbeiter;
    }
}
