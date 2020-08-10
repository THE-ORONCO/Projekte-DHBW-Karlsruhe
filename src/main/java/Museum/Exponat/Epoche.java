package Museum.Exponat;

public class Epoche {
    //DIFF so ziemlich das ganze Ding, weil das vorher eher unschön war -> Epochen Manager
    //TODO abklären mit Kai wie das jetzt implementiert werden soll

    private final String epoche;
    private final String stilrichtung;
    private final String zeitalter;

    public Epoche(String epoche, String stilrichtung, String zeitalter) {
        this.epoche = epoche;
        this.stilrichtung = stilrichtung;
        this.zeitalter = zeitalter;
    }

    public String getEpoche() {
        return epoche;
    }

    public String getStilrichtung() {
        return stilrichtung;
    }

    public String getZeitalter() {
        return zeitalter;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", epoche, stilrichtung, zeitalter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epoche)) return false;
        Epoche epoche1 = (Epoche) o;
        return getEpoche().equals(epoche1.getEpoche()) &&
                getStilrichtung().equals(epoche1.getStilrichtung()) &&
                getZeitalter().equals(epoche1.getZeitalter());
    }
}
