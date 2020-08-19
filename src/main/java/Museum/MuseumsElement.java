/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum;

public abstract class MuseumsElement { //DIFF neue Überklasse für alle Elemente welche das managen einfacher macht
    private String primaryKey; //DIFF identifikation aller Elemente findet nun über die MuseumsElement-Klasse statt
    private String beschreibung;

    public MuseumsElement(String primaryKey, String beschreibung) {
        this.primaryKey = primaryKey;
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

    public String getPrimaryKey(){
        return primaryKey;
    }
}
