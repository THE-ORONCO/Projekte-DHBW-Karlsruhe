package Museum.Exponat;

import Museum.MuseumsElement;

public class Epoche extends MuseumsElement { // DIFF erbt von MuseumsElement
    //DIFF so ziemlich das ganze Ding, weil das vorher eher unschön war -> Epochen Manager

    private final String epoche;
    private final String stilrichtung;
    private final String zeitalter;

    /**
     * Kunstepoche aus der ein Exponat stammen kann.
     *
     * @param epochenID eindeutige ID einer Epoche
     * @param epoche Name der Epoche
     * @param stilrichtung stilrichtung innerhalb der Epoche
     * @param zeitalter Zeitalter in der der spezifische Epoche stattfand
     * @param beschreibung kurze Beschreigung der Epoche
     */
    public Epoche(String epochenID, String epoche, String stilrichtung, String zeitalter, String beschreibung) {
        super(epochenID, beschreibung);
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
