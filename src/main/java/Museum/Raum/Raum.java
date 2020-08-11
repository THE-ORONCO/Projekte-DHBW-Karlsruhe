/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Raum;

import Museum.Bild.Bild;
import Museum.MuseumsElement;

import java.util.ArrayList;

public class Raum extends MuseumsElement { //DIFF neue Überklasse

    private double ausstellungsflaeche;
    private String ausstellungsthema;
    private ArrayList<Bild> bilder;

    /**
     * repräsentiert einen Raum im Museum
     *
     * @param raumNr              Nummer mit der der Raum im System eindeutig identifiziert werden kann
     * @param beschreibung        eine Kurze Beschreibung des raumes (z.B.: zwei Fenster, eine Säule, 3 Türen)
     * @param ausstellungsflaeche benutzbare Ausstellungsfläche die in diesem Raum zur Verfügung steht in Quadratmetern
     * @param ausstellungsthema   Thema der Ausstellung im aktuellen Raum
     * @param bilder              bilder des Raumes
     */
    public Raum(String raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema, ArrayList<Bild> bilder) {
        super(raumNr, beschreibung);
        this.ausstellungsflaeche = ausstellungsflaeche;
        this.ausstellungsthema = ausstellungsthema;
        this.bilder = bilder;
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

    @Override
    public String toString() {
        String raum = "";
        raum += String.format("RaumNr: %s%n" +
                "Ausstellungsfläche : %f%n" +
                "Ausstellungsthema: %s%n" +
                "Beschreibung: %s", this.getPrimaryKey(), this.ausstellungsflaeche, this.ausstellungsthema, this.getBeschreibung());
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
