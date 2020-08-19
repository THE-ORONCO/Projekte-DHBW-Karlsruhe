/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Raum;

import Museum.Bild.Bild;
import Museum.Exponat.Exponat;
import Museum.MuseumsElement;
import Museum.ObjectManagement.MuseumsManager;

import java.util.ArrayList;

public class Raum extends MuseumsElement { //DIFF neue Überklasse

    private double ausstellungsflaeche;
    private String ausstellungsthema;
    private ArrayList<Bild> bilder;
    private ArrayList<Exponat> ausgestellteExponate; // DIFF wurde im Entwurfsklassendiagramm vergessen

    /**
     * repräsentiert einen Raum im Museum
     *
     * @param raumNr               Nummer mit der der Raum im System eindeutig identifiziert werden kann
     * @param beschreibung         eine Kurze Beschreibung des raumes (z.B.: zwei Fenster, eine Säule, 3 Türen)
     * @param ausstellungsflaeche  benutzbare Ausstellungsfläche die in diesem Raum zur Verfügung steht in Quadratmetern
     * @param ausstellungsthema    Thema der Ausstellung im aktuellen Raum
     * @param bilder               bilder des Raumes
     * @param ausgestellteExponate die in dem Raum ausgestellten Exponate
     */
    public Raum(String raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema, ArrayList<Bild> bilder, ArrayList<Exponat> ausgestellteExponate) throws Exception {
        super(raumNr, beschreibung);
        this.ausstellungsflaeche = ausstellungsflaeche;
        this.ausstellungsthema = ausstellungsthema;
        this.bilder = bilder;
        this.ausgestellteExponate = new ArrayList<Exponat>();
        for (Exponat e : ausgestellteExponate) {
            this.exponatAusstellen(e);
        }
    }

    public Raum(String raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema, ArrayList<Bild> bilder) throws Exception {
        this(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder, new ArrayList<Exponat>());
    }

    public double getAusstellungsflaeche() {
        return ausstellungsflaeche;
    }

    public void setAusstellungsflaeche(double ausstellungsflaeche) {
        this.ausstellungsflaeche = ausstellungsflaeche;
    }

    public String getAusstellungsthema() {
        return ausstellungsthema;
    }

    public void setAusstellungsthema(String ausstellungsthema) {
        this.ausstellungsthema = ausstellungsthema;
    }

    public ArrayList<Bild> getBilder() {
        return bilder;
    }

    public void setBilder(ArrayList<Bild> bilder) {
        this.bilder = bilder;
    }

    public ArrayList<Exponat> getAusgestellteExponate() {
        return ausgestellteExponate;
    }

    public void setAusgestellteExponate(ArrayList<Exponat> ausgestellteExponate) {
        this.ausgestellteExponate = ausgestellteExponate;
    }

    /**
     * Fügt ein bekanntes Exponat einem Raum hinzu
     *
     * @param exponat
     * @throws Exception
     */
    public void exponatAusstellen(Exponat exponat) throws Exception {
        double verbrauchteAusstellungsflaeche = 0;
        for (Exponat e : this.ausgestellteExponate) {
            verbrauchteAusstellungsflaeche += e.getBenoetigteAusstellungsflaeche();
        }
        if (verbrauchteAusstellungsflaeche + exponat.getBenoetigteAusstellungsflaeche() <= this.ausstellungsflaeche
                && !this.ausgestellteExponate.contains(exponat)) {

            // wenn das Exponat noch nicht existiert wird es dem MuseumsManager hinzugefügt
            if(!MuseumsManager.contains(Exponat.class, exponat)){
                MuseumsManager.persist(Exponat.class, exponat);
            }

            this.ausgestellteExponate.add(exponat);
        } else throw new Exception("Ausstellungsflaeche zu klein oder Exponat bereits im Raum");
    }

    @Override
    public String toString() {
        String raum = "";
        raum += String.format("RaumNr: %s%n" +
                "Ausstellungsfläche : %f%n" +
                "Ausstellungsthema: %s%n" +
                "Beschreibung: %s%n" +
                "Ausgestellte Exponate:%n", this.getPrimaryKey(), this.ausstellungsflaeche, this.ausstellungsthema, this.getBeschreibung());
        for (Exponat exponat : this.ausgestellteExponate) {
            raum += String.format("%s%n", exponat.toString());
        }
        return raum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Raum)) return false;
        Raum raum = (Raum) o;
        return getPrimaryKey() == raum.getPrimaryKey() &&
                Double.compare(raum.getAusstellungsflaeche(), getAusstellungsflaeche()) == 0 &&
                getBeschreibung().equals(raum.getBeschreibung()) &&
                getAusstellungsthema().equals(raum.getAusstellungsthema());
    }
}
