package Museum.Exponat;

import Museum.Bild.Bild;
import Museum.MuseumsElement;
import Museum.ObjectManagement.CSVSeparationLevel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Exponat extends MuseumsElement {

    private String name;
    // private final Date erstellungsDatum; // DIFF kein erstellungsdatum
    private Date entstehungsDatum;
    private ArrayList<String> urheber; //koennte auch eine Liste aus Personen sein
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
     * @param benoetigteAusstellungsfaeche benoetigte Fläche um das Exponat auszustellen
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
        super(inventarNummer, beschreibung);
        this.name = name;
        // this.erstellungsDatum = new Date(); // aktuelles Datum
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        String exponatString = "";
        exponatString += String.format("Inventarnummer: %s%n", getPrimaryKey());
        exponatString += String.format("Name: %s%n", getName());
        exponatString += String.format("Entstehungsdatum: %s%n", new SimpleDateFormat("yyyy.MM.dd").format(getEntstehungsDatum()));
        for (String einzelnerUrheber : getUrheber()) {
            exponatString += String.format("Urheber: %s%n", einzelnerUrheber);
        }
        exponatString += String.format("Benoetigte Ausstellungsflaeche: %f%n", getBenoetigteAusstellungsflaeche());
        for (String kategorie : getKategorien()) {
            exponatString += String.format("Kategorie: %s%n", kategorie);
        }
        exponatString += String.format("Epoche: %s%n", getEpoche().toString());
        exponatString += String.format("Herkunftsort: %s%n", getHerkunftsort());
        exponatString += String.format("Exponatwert: %s%n", getExponatwert().toString());
        exponatString += String.format("geschichtliche Historie:%n%s%n", getGeschichtilcheH().toString());
        exponatString += String.format("Bearbeitungshistorie:%n%s%n", getBearbeitungsH().toString());
        exponatString += String.format("Besitzhistorie:%n%s%n", getBesitzH().toString());
        exponatString += String.format("Bild: %s%n", getBild().toString());
        exponatString += String.format("Beschreibung: %s", getBeschreibung());
        return exponatString;
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String[] parsToCSV() {

        String[] csvData = new String[]{
                this.getPrimaryKey(),
                this.getName(),
                new SimpleDateFormat("yyyy.MM.dd").format(getEntstehungsDatum()),
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getUrheber()),// Urheber sind eine Liste
                String.valueOf(this.getBenoetigteAusstellungsflaeche()),
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getKategorien()), //Kategorien ist eine Liste
                this.getEpoche().getPrimaryKey(), //die Epoche wird hier indirekt refferneziert
                this.getHerkunftsort(),
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getExponatwert().parsToCSV()), // Exponatwerte haben mehere Attribute
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getGeschichtilcheH().parseToCSV()), // Historien sind listen
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getBearbeitungsH().parseToCSV()),
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getBesitzH().parseToCSV()),
                String.join(CSVSeparationLevel.LEVEL2.wSeparator(), this.getBild().getPrimaryKey()), //das Bild wird hier indirekt refferneziert
                this.getBeschreibung()
        };
        return csvData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exponat)) return false;
        if (!super.equals(o)) return false;
        Exponat exponat = (Exponat) o;
        return Double.compare(exponat.getBenoetigteAusstellungsflaeche(), getBenoetigteAusstellungsflaeche()) == 0 &&
                getName().equals(exponat.getName()) &&
                getEntstehungsDatum().equals(exponat.getEntstehungsDatum()) &&
                getUrheber().equals(exponat.getUrheber()) &&
                getKategorien().equals(exponat.getKategorien()) &&
                getEpoche().equals(exponat.getEpoche()) &&
                getHerkunftsort().equals(exponat.getHerkunftsort()) &&
                getExponatwert().equals(exponat.getExponatwert()) &&
                getGeschichtilcheH().equals(exponat.getGeschichtilcheH()) &&
                getBearbeitungsH().equals(exponat.getBearbeitungsH()) &&
                getBesitzH().equals(exponat.getBesitzH()) &&
                getBild().equals(exponat.getBild());
    }

    /**
     * Gibt die Namen der Objektattribute zurück
     *
     * @return die Namen der Objektattribute
     */
    public static String[] getCSVHeader() {
        return new String[]{
                "inventarNr",
                "name",
                "entstehungsDatum",
                "urheber",
                "benoetigteAusstellungsflaeche",
                "kategorien",
                "epoche",
                "herkunftsort",
                "exponatwert",
                "geschichtlicheH",
                "bearbeitungsH",
                "besitzH",
                "bild",
                "beschreibung"
        };
    }
}
