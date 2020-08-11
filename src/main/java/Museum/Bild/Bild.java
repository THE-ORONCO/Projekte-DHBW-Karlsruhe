package Museum.Bild;

import Museum.MuseumsElement;

public class Bild extends MuseumsElement { //DIFF Bild hat neues Überobjekt
    // TODO vielleicht ermöglichen eine fertige Bild-File oder ein Bild-Element für die GUI auszugeben
    // TODO möglicherweise eine BildId hinzufügen

    private String altText;
    private String dateiName;

    /**
     * Repräsentiert ein Bild eines Exponats, einer Person oder eines Raumes
     * @param altText Alternativ-Text der um die Barrierefreiheit zu erhöhen angegeben werden kann
     * @param dateiName dateiname der Bild-Datei
     * @param beschreibung eine kurze Beschreibung des Bildes
     */
    public Bild(String altText, String dateiName, String beschreibung){ //TODO potenziell Methodensignatur ändern
        super(beschreibung);
        this.altText = altText;
        this.dateiName = dateiName;
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

    @Override
    public String toString() {
        String bild = "";
        bild += String.format("Alt-Text: %s%nDateiname: %s%nBeschreibung: %s", this.altText, this.dateiName, this.getBeschreibung());
        return bild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bild)) return false;
        if (!super.equals(o)) return false;
        Bild bild = (Bild) o;
        return getAltText().equals(bild.getAltText()) &&
                getDateiName().equals(bild.getDateiName());
    }
}
