package Museum.Exponat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ereignis {
    private Date datum;
    private String beschreibung;

    /**
     * Ein Ereignis-Objekt repräsentiert ein Ereignis in einer Historie verbunden mit Datum und Beschreibung.
     *
     * @param datum        Zeitpunkt an dem Das Ereignis geschehen ist
     * @param beschreibung Die Beschreibung was wärend des Ereignisses passiert ist
     */
    public Ereignis(Date datum, String beschreibung) {
        this.datum = datum;
        this.beschreibung = beschreibung;
    }

    /**
     * Ein Ereignis-Objekt repräsentiert ein Ereignis in einer Historie verbunden mit Datum und Beschreibung.
     * Dies ist ein Ausweichkonstruktor, wenn keine Beschreibung bekannt ist.
     *
     * @param datum        Zeitpunkt an dem Das Ereignis geschehen ist
     */
    public Ereignis(Date datum) {
        this(datum, "Ereignis am " + datum.toString());
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        String ereignis = "";
        ereignis += String.format("%s : %s", datum.toString(), beschreibung);
        return ereignis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ereignis)) return false;
        Ereignis ereignis = (Ereignis) o;
        return getDatum().equals(ereignis.getDatum()) &&
                getBeschreibung().equals(ereignis.getBeschreibung());
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    public String[] parsToCSV() {
        String pattern = "yyyy.MM.dd";
        String[] csvData = new String[]{
                new SimpleDateFormat(pattern).format(this.datum),
                this.beschreibung
        };
        return csvData;
    }

}
