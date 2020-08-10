package Museum.Bild;

public class Bild { // TODO vielleicht ermöglichen eine fertige Bild-File oder ein Bild-Element für die GUI auszugeben

    private String altText;
    private String dateiName;
    private String beschreibung;

    /**
     * Repräsentiert ein Bild eines Exponats, einer Person oder eines Raumes
     * @param altText Alternativ-Text der um die Barrierefreiheit zu erhöhen angegeben werden kann
     * @param dateiName dateiname der Bild-Datei
     * @param beschreibung eine kurze Beschreibung des Bildes
     */
    public Bild(String altText, String dateiName, String beschreibung){
        this.altText = altText;
        this.dateiName = dateiName;
        this.beschreibung = beschreibung;
    }

    public Bild(String dateiName){
        this(dateiName, "", "");
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getDateiName() {
        return dateiName;
    }

    public void setDateiName(String dateiName) {
        this.dateiName = dateiName;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        String bild = "";
        bild += String.format("Bild%nAlt-Text: %s%nDateiname: %s%nBeschreibung: %s%n", this.altText, this.dateiName, this.beschreibung);
        return bild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bild)) return false;
        Bild bild = (Bild) o;
        return getAltText().equals(bild.getAltText()) &&
                getDateiName().equals(bild.getDateiName()) &&
                getBeschreibung().equals(bild.getBeschreibung());
    }

}
