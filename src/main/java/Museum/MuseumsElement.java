/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum;

public abstract class MuseumsElement { //DIFF neue ueberklasse fuer alle Elemente welche das managen einfacher macht
    private String primaryKey; //DIFF identifikation aller Elemente findet nun ueber die MuseumsElement-Klasse statt
    private String beschreibung;

    /**
     * Dies bildet ein generelles Element innerhalb des Museums ab
     *
     * @param primaryKey   eindeutiger Identifikator des Elements
     * @param beschreibung Beschreibung des Elements
     */
    public MuseumsElement(String primaryKey, String beschreibung) {
        this.primaryKey = primaryKey.trim();
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MuseumsElement)) return false;
        MuseumsElement that = (MuseumsElement) o;
        return getBeschreibung().equals(that.getBeschreibung());
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    public abstract String[] parsToCSV();

    public static String[] getCSVHeader() {
        return new String[0];
    }
}
