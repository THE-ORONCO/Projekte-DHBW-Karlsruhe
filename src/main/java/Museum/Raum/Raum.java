/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Raum;

import Museum.Bild.Bild;

import java.util.ArrayList;

public class Raum {

    private int raumNr;
    private String beschreibung;
    private double ausstellungsflaeche;
    private String ausstellungsthema;
    private ArrayList<Bild> bilder;

    /**
     * repräsentiert einen Raum im Museum
     *
     * @param raumNr              Nummer mit der der Raum im System eindeutig identifiziert werden kann
     * @param beschreibung        eine Kurze Beschreibung des raumes (z.B.: zwei Fenster, eine Säule, 3 Türen)
     * @param ausstellungsflaeche benutzbare Ausstellungsfläche die in diesem Raum zur Verfügung steht in Quadratmetern
     * @param aussstellungsthema  Thema der Ausstellung im aktuellen Raum
     * @param bilder              bilder des Raumes
     */
    public Raum(int raumNr, String beschreibung, double ausstellungsflaeche, String aussstellungsthema, ArrayList<Bild> bilder) {
        this.raumNr = raumNr;
        this.beschreibung = beschreibung;
        this.ausstellungsflaeche = ausstellungsflaeche;
        this.ausstellungsthema = aussstellungsthema;
        this.bilder = bilder;
    }

    public Raum(int raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema){
        this(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, new ArrayList<Bild>());
    }

    public Raum(int raumNr, double ausstellungsflaeche) {
        this(raumNr, "Raum", ausstellungsflaeche, "divers", new ArrayList<Bild>());
    }

    public int getRaumNr() {
        return raumNr;
    }

    public void setRaumNr(int raumNr) {
        this.raumNr = raumNr;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
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
        raum += String.format("RaumNr: %d%n" +
                "Ausstellungsfläche : %f%n" +
                "Ausstellungsthema: %s%n" +
                "Beschreibung: %s", this.raumNr, this.ausstellungsflaeche, this.ausstellungsthema, this.beschreibung);
        return raum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Raum)) return false;
        Raum raum = (Raum) o;
        return getRaumNr() == raum.getRaumNr() &&
                Double.compare(raum.getAusstellungsflaeche(), getAusstellungsflaeche()) == 0 &&
                getBeschreibung().equals(raum.getBeschreibung()) &&
                getAusstellungsthema().equals(raum.getAusstellungsthema());
    }
}
