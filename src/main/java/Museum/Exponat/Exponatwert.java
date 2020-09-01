package Museum.Exponat;

public class Exponatwert {

    private final double einkaufsWert;
    private double aktuellerSchaetzwert;
    private double leihwert; // DIFF leihwert statt leihwerte, da mehr als einer vlt nicht sinnvoll ist

    /**
     * Diese Klasse representiert den Wert den ein Exponat hat
     *
     * @param einkaufsWert         Einkaufswert des Exponats
     * @param aktuellerSchaetzwert Aktueller schätzwert des Exponats
     * @param leihwert             Leihwert des Exponats
     */
    public Exponatwert(double einkaufsWert, double aktuellerSchaetzwert, double leihwert) {
        this.einkaufsWert = einkaufsWert;
        this.aktuellerSchaetzwert = aktuellerSchaetzwert;
        this.leihwert = leihwert;
    }

    public double getEinkaufsWert() {
        return einkaufsWert;
    }

    public double getAktuellerSchaetzwert() {
        return aktuellerSchaetzwert;
    }

    public void setAktuellerSchaetzwert(double aktuellerSchaetzwert) {
        this.aktuellerSchaetzwert = aktuellerSchaetzwert;
    }

    public double getLeihwert() {
        return leihwert;
    }

    public void setLeihwert(double leihwert) {
        this.leihwert = leihwert;
    }

    @Override
    public String toString() {
        return String.format("Einkaufswert: %f€ Schaetzwert: %f€ Leihwert: %f€", einkaufsWert, aktuellerSchaetzwert, leihwert);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exponatwert)) return false;
        Exponatwert that = (Exponatwert) o;
        return Double.compare(that.getEinkaufsWert(), getEinkaufsWert()) == 0 &&
                Double.compare(that.getAktuellerSchaetzwert(), getAktuellerSchaetzwert()) == 0 &&
                Double.compare(that.getLeihwert(), getLeihwert()) == 0;
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    public String[] parsToCSV() {
        String[] csvData = new String[]{
                String.valueOf(this.getEinkaufsWert()),
                String.valueOf(this.getAktuellerSchaetzwert()),
                String.valueOf(this.getLeihwert())
        };
        return csvData;
    }
}
