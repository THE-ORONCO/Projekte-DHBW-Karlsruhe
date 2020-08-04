package Museum.Exponat;

import Museum.Bild.Bild;
import Museum.Person.Förderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Exponat {

    private final int inventarNummer;
    private String name;
    private final Date erstellungsDatum;
    private Date entstehungsDatum;
    private ArrayList<String> urheber; //könnte auch eine Liste aus Personen sein
    private double benötigteAusstellungsfläche;
    private ArrayList<String> kategorien;
    private ArrayList<Epoche> epoche;
    private String herkunftsort;
    private ArrayList<Förderer> förderer;
    private Exponatwert exponatwert;
    private GeschichtilcheHistorie geschichtilcheH;
    private BearbeitungsHistorie bearbeitungsH;
    private BesitzHistorie besitzH;
    private Bild bild;
    private String beschreibung;

    public Exponat(int inventarNummer, String name, Date entstehungsDatum, ArrayList<String> urheber, double benötigteAusstellungsfläche, ArrayList<String> kategorien, ArrayList<Epoche> epoche, String herkunftsort, ArrayList<Förderer> förderer, Exponatwert exponatwert, GeschichtilcheHistorie geschichtilcheH, BearbeitungsHistorie bearbeitungsH, BesitzHistorie besitzH, Bild bild, String beschreibung) {
        this.inventarNummer = inventarNummer;
        this.name = name;
        this.erstellungsDatum = new Date(); // aktuelles Datum
        this.entstehungsDatum = entstehungsDatum;
        this.urheber = urheber;
        this.benötigteAusstellungsfläche = benötigteAusstellungsfläche;
        this.kategorien = kategorien;
        this.epoche = epoche;
        this.herkunftsort = herkunftsort;
        this.förderer = förderer;
        this.exponatwert = exponatwert;
        this.geschichtilcheH = geschichtilcheH;
        this.bearbeitungsH = bearbeitungsH;
        this.besitzH = besitzH;
        this.bild = bild;
        this.beschreibung = beschreibung;
    }

    public Exponat(int inventarNummer, String name, Date entstehungsDatum, double benötigteAusstellungsfläche, ArrayList<String> kategorien, ArrayList<Epoche> epoche, String herkunftsort, ArrayList<Förderer> förderer, Exponatwert exponatwert, GeschichtilcheHistorie geschichtilcheH, BearbeitungsHistorie bearbeitungsH, BesitzHistorie besitzH, Bild bild, String beschreibung) {
        this(inventarNummer, name, entstehungsDatum, new ArrayList<String>(Collections.singleton("Unbekannt")), benötigteAusstellungsfläche, kategorien, epoche, herkunftsort, förderer, exponatwert, geschichtilcheH, bearbeitungsH, besitzH, bild, beschreibung);
    }

    public Exponat(int inventarNummer, String name, Date entstehungsDatum, ArrayList<String> urheber, double benötigteAusstellungsfläche, ArrayList<String> kategorien, ArrayList<Epoche> epoche, String herkunftsort, ArrayList<Förderer> förderer, Exponatwert exponatwert, GeschichtilcheHistorie geschichtilcheH, BearbeitungsHistorie bearbeitungsH, BesitzHistorie besitzH, Bild bild) {
        this(inventarNummer, name, entstehungsDatum, urheber, benötigteAusstellungsfläche, kategorien, epoche, herkunftsort, förderer, exponatwert, geschichtilcheH, bearbeitungsH, besitzH, bild, "Exponat");
    }

    public Exponat(int inventarNummer, String name, Date entstehungsDatum, ArrayList<String> urheber, double benötigteAusstellungsfläche, ArrayList<String> kategorien, ArrayList<Epoche> epoche, String herkunftsort, ArrayList<Förderer> förderer, Exponatwert exponatwert, GeschichtilcheHistorie geschichtilcheH, BearbeitungsHistorie bearbeitungsH, BesitzHistorie besitzH, String beschreibung) {
        this(inventarNummer, name, entstehungsDatum, urheber, benötigteAusstellungsfläche, kategorien, epoche, herkunftsort, förderer, exponatwert, geschichtilcheH, bearbeitungsH, besitzH, new Bild("default Exponat", "default.jpg", "default Bild"), "Exponat");
        // TODO default path of default picture
    }

    public int getInventarNummer() {
        return inventarNummer;
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

    public double getBenötigteAusstellungsfläche() {
        return benötigteAusstellungsfläche;
    }

    public void setBenötigteAusstellungsfläche(double benötigteAusstellungsfläche) {
        this.benötigteAusstellungsfläche = benötigteAusstellungsfläche;
    }

    public ArrayList<String> getKategorien() {
        return kategorien;
    }

    public void setKategorien(ArrayList<String> kategorien) {
        this.kategorien = kategorien;
    }

    public ArrayList<Epoche> getEpoche() {
        return epoche;
    }

    public void setEpoche(ArrayList<Epoche> epoche) {
        this.epoche = epoche;
    }

    public String getHerkunftsort() {
        return herkunftsort;
    }

    public void setHerkunftsort(String herkunftsort) {
        this.herkunftsort = herkunftsort;
    }

    public ArrayList<Förderer> getFörderer() {
        return förderer;
    }

    public void setFörderer(ArrayList<Förderer> förderer) {
        this.förderer = förderer;
    }

    public Exponatwert getExponatwert() {
        return exponatwert;
    }

    public void setExponatwert(Exponatwert exponatwert) {
        this.exponatwert = exponatwert;
    }

    public GeschichtilcheHistorie getGeschichtilcheH() {
        return geschichtilcheH;
    }

    public void setGeschichtilcheH(GeschichtilcheHistorie geschichtilcheH) {
        this.geschichtilcheH = geschichtilcheH;
    }

    public BearbeitungsHistorie getBearbeitungsH() {
        return bearbeitungsH;
    }

    public void setBearbeitungsH(BearbeitungsHistorie bearbeitungsH) {
        this.bearbeitungsH = bearbeitungsH;
    }

    public BesitzHistorie getBesitzH() {
        return besitzH;
    }

    public void setBesitzH(BesitzHistorie besitzH) {
        this.besitzH = besitzH;
    }

    public Bild getBild() {
        return bild;
    }

    public void setBild(Bild bild) {
        this.bild = bild;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
