package Museum.Raum;

public class Raum {

    private int raumNr;
    private String beschreibung;
    private double ausstellungsfläche;
    private String ausstellungsthema;

    public Raum(int raumNr, String beschreibung, double ausstellungsfläche, String aussstellungsthema) {
        this.raumNr = raumNr;
        this.beschreibung = beschreibung;
        this.ausstellungsfläche = ausstellungsfläche;
        this.ausstellungsthema = aussstellungsthema;
    }

    public Raum(int raumNr, double ausstellungsfläche) {
        this(raumNr, "Raum", ausstellungsfläche, "divers");
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

    @Override
    public String toString() {
        String raum = "";
        raum += String.format(
                "Raum%nRaumNr: %d%n" +
                        "Ausstellungsfläche : %d%n" +
                        "Ausstellungsthema: %s%n" +
                        "Beschreibung: %s%n", this.raumNr, this.ausstellungsfläche, this.ausstellungsthema, this.beschreibung);
        return raum;
    }
}
