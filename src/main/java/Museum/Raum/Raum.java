package Museum.Raum;

public class Raum {

    private int raumNr;
    private String beschreibung;
    private double ausstellungsflaeche;
    private String ausstellungsthema;

    public Raum(int raumNr, String beschreibung, double ausstellungsflaeche, String aussstellungsthema) {
        this.raumNr = raumNr;
        this.beschreibung = beschreibung;
        this.ausstellungsflaeche = ausstellungsflaeche;
        this.ausstellungsthema = aussstellungsthema;
    }

    public Raum(int raumNr, double ausstellungsflaeche) {
        this(raumNr, "Raum", ausstellungsflaeche, "divers");
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
        raum += String.format(
                "Raum%nRaumNr: %d%n" +
                        "Ausstellungsfläche : %f%n" +
                        "Ausstellungsthema: %s%n" +
                        "Beschreibung: %s%n", this.raumNr, this.ausstellungsflaeche, this.ausstellungsthema, this.beschreibung);
        return raum;
    }
}
