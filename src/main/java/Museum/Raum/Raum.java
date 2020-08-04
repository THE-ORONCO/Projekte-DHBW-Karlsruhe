package Museum.Raum;

public class Raum {

    private String raumNr;
    private String beschreibung;
    private double ausstellungsfläche;
    private String ausstellungsthema;

    public Raum(String raumNr, String beschreibung, double ausstellungsfläche, String aussstellungsthema) {
        this.raumNr = raumNr;
        this.beschreibung = beschreibung;
        this.ausstellungsfläche = ausstellungsfläche;
        this.ausstellungsthema = aussstellungsthema;
    }

    public Raum(String raumNr, double ausstellungsfläche){
        this(raumNr, "Raum", ausstellungsfläche, "divers");
    }

    public String getRaumNr() {
        return raumNr;
    }

    public void setRaumNr(String raumNr) {
        this.raumNr = raumNr;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public double getAusstellungsfläche() {
        return ausstellungsfläche;
    }

    public void setAusstellungsfläche(double ausstellungsfläche) {
        this.ausstellungsfläche = ausstellungsfläche;
    }

    public String getAusstellungsthema() {
        return ausstellungsthema;
    }

    public void setAusstellungsthema(String ausstellungsthema) {
        this.ausstellungsthema = ausstellungsthema;
    }
}
