package Museum.Exponat;

import Museum.Bild.Bild;
import Museum.MuseumsElement;
import Museum.ObjectManagement.CSVSeparationLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Exponat extends MuseumsElement {

    private String name;
    private final Date erstellungsDatum;
    private Date entstehungsDatum;
    private ArrayList<String> urheber; //könnte auch eine Liste aus Personen sein
    private double benoetigteAusstellungsflaeche;
    private ArrayList<String> kategorien;
    private Epoche epoche;
    private String herkunftsort;
    private Exponatwert exponatwert;
    private Historie geschichtilcheH;
    private Historie bearbeitungsH; // TODO tracken der Änderungen in der Bearbeitungshistorie
    private Historie besitzH;
    private Bild bild;

    /**
     * Dieses Objekt repräsentiert ein Exponat im Museum
     *
     * @param inventarNummer               Inventarnummer des Exponats
     * @param name                         Name des Exponats
     * @param entstehungsDatum             Entstehungsdatum des Exponats im Fromat von yyyy.MM.dd
     * @param urheber                      Urheber des Exponats
     * @param benoetigteAusstellungsfaeche benötigte Fläche um das Exponat auszustellen
     * @param kategorien                   Kategorien in die das Exponat einzuordenen ist (Bild etc.)
     * @param epoche                       Epoche aus der das Exponat kommt
     * @param herkunftsort                 Herkunftsort des Exponats
     * @param exponatwert                  Kombination aus Einkaufs-, aktuellem Schätz- und dem Leihwert des Exponats
     * @param geschichtilcheH              geschichtliche des Exponats
     * @param bearbeitungsH                Bearbeitungshistorie des Exponats im internen System
     * @param besitzH                      Besitzhistorie des Exponats
     * @param bild                         Bild des Exponats
     * @param beschreibung                 eine kurze Beschreibung des Exponats
     */
    public Exponat(String inventarNummer, String name, Date entstehungsDatum, ArrayList<String> urheber,
                   double benoetigteAusstellungsfaeche, ArrayList<String> kategorien, Epoche epoche,
                   String herkunftsort, Exponatwert exponatwert,
                   Historie geschichtilcheH, Historie bearbeitungsH, Historie besitzH,
                   Bild bild, String beschreibung) {
        super("e" + inventarNummer, beschreibung);
        this.name = name;
        this.erstellungsDatum = new Date(); // aktuelles Datum
        this.entstehungsDatum = entstehungsDatum;
        this.urheber = urheber;
        this.benoetigteAusstellungsflaeche = benoetigteAusstellungsfaeche;
        this.kategorien = kategorien;
        this.epoche = epoche;
        this.herkunftsort = herkunftsort;
        this.exponatwert = exponatwert;
        this.geschichtilcheH = geschichtilcheH;
        this.bearbeitungsH = bearbeitungsH;
        this.besitzH = besitzH;
        this.bild = bild;
    }

    public Exponat(String inventarNummer, String name, Date entstehungsDatum, double benoetigteAusstellungsflaeche, ArrayList<String> kategorien, Epoche epoche, String herkunftsort, Exponatwert exponatwert, Historie geschichtilcheH, Historie bearbeitungsH, Historie besitzH, Bild bild, String beschreibung) {
        this(inventarNummer, name, entstehungsDatum, new ArrayList<String>(Collections.singleton("Unbekannt")), benoetigteAusstellungsflaeche, kategorien, epoche, herkunftsort, exponatwert, geschichtilcheH, bearbeitungsH, besitzH, bild, beschreibung);
    }

    public Exponat(String inventarNummer, String name, Date entstehungsDatum, ArrayList<String> urheber, double benoetigteAusstellungsflaeche, ArrayList<String> kategorien, Epoche epoche, String herkunftsort, Exponatwert exponatwert, Historie geschichtilcheH, Historie bearbeitungsH, Historie besitzH, Bild bild) {
        this(inventarNummer, name, entstehungsDatum, urheber, benoetigteAusstellungsflaeche, kategorien, epoche, herkunftsort, exponatwert, geschichtilcheH, bearbeitungsH, besitzH, bild, "Exponat");
    }

    public Exponat(String inventarNummer, String name, Date entstehungsDatum, ArrayList<String> urheber, double benoetigteAusstellungsflaeche, ArrayList<String> kategorien, Epoche epoche, String herkunftsort, Exponatwert exponatwert, Historie geschichtilcheH, Historie bearbeitungsH, Historie besitzH, String beschreibung) {
        this(inventarNummer, name, entstehungsDatum, urheber, benoetigteAusstellungsflaeche, kategorien, epoche, herkunftsort, exponatwert, geschichtilcheH, bearbeitungsH, besitzH, new Bild("0", "default Exponat", "default.jpg", "default Bild"), "Exponat");
        // TODO default path of default picture
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public Date getEntstehungsDatum() {
        return entstehungsDatum;
    }

    public void setEntstehungsDatum(Date entstehungsDatum) {
        this.entstehungsDatum = entstehungsDatum;
    }

    public ArrayList<String> getUrheber() {
        return urheber;
    }

    public void setUrheber(ArrayList<String> urheber) {
        this.urheber = urheber;
    }

    public double getBenoetigteAusstellungsflaeche() {
        return benoetigteAusstellungsflaeche;
    }

    public void setBenoetigteAusstellungsflaeche(double benoetigteAusstellungsflaeche) {
        this.benoetigteAusstellungsflaeche = benoetigteAusstellungsflaeche;
    }

    public ArrayList<String> getKategorien() {
        return kategorien;
    }

    public void setKategorien(ArrayList<String> kategorien) {
        this.kategorien = kategorien;
    }

    public Epoche getEpoche() {
        return epoche;
    }

    public void setEpoche(Epoche epoche) {
        this.epoche = epoche;
    }

    public String getHerkunftsort() {
        return herkunftsort;
    }

    public void setHerkunftsort(String herkunftsort) {
        this.herkunftsort = herkunftsort;
    }

    public Exponatwert getExponatwert() {
        return exponatwert;
    }

    public void setExponatwert(Exponatwert exponatwert) {
        this.exponatwert = exponatwert;
    }

    public Historie getGeschichtilcheH() {
        return geschichtilcheH;
    }

    public void setGeschichtilcheH(Historie geschichtilcheH) {
        this.geschichtilcheH = geschichtilcheH;
    }

    public Historie getBearbeitungsH() {
        return bearbeitungsH;
    }

    public void setBearbeitungsH(Historie bearbeitungsH) {
        this.bearbeitungsH = bearbeitungsH;
    }

    public Historie getBesitzH() {
        return besitzH;
    }

    public void setBesitzH(Historie besitzH) {
        this.besitzH = besitzH;
    }

    public Bild getBild() {
        return bild;
    }

    public void setBild(Bild bild) {
        this.bild = bild;
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbare CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String[] parsToCSV() {

        String[] csvData = new String[]{
                this.getPrimaryKey(),
                this.getName(),
                this.getEntstehungsDatum().toString(),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getUrheber()),// Urheber sind eine Liste
                String.valueOf(this.getBenoetigteAusstellungsflaeche()),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getKategorien()), //Kategorien ist eine Liste
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getEpoche().parsToCSV()),// Epochen haben mehere Attribute
                this.getHerkunftsort(),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getExponatwert().parsToCSV()), // Exponatwerte haben mehere Attribute
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getGeschichtilcheH().parseToCSV()), // Historien sind listen
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getBearbeitungsH().parseToCSV()),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getBesitzH().parseToCSV()),
                String.join(CSVSeparationLevel.LEVEL2.toString(), this.getBild().parsToCSV()),// Bilder haben mehere Attribute
                this.getBeschreibung()
        };
        return csvData;
    }
}
