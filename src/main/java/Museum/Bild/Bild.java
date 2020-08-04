package Museum.Bild;

public class Bild {

    private String altText;
    private String dateiName;
    private String beschreibung;

    public Bild(String altText, String dateiName, String beschreibung){
        this.altText = altText;
        this.dateiName = dateiName;
        this.beschreibung = beschreibung;
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
        return "Bild{" +
                "altText='" + altText + '\'' +
                ", dateiName='" + dateiName + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                '}';
    }
}
