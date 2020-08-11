package Museum;

public abstract class MuseumsElement { //DIFF neue Überklasse für alle Elemente welche das managen einfacher macht
    private String beschreibung;

    public MuseumsElement(String beschreibung) {
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

}
