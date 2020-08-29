package Museum.Exponat;

public class Exponatwert {

    private final float einkaufsWert;
    private float aktuellerSchaetzwert;
    private float leihwert; // DIFF leihwert statt leihwerte, da mehr als einer vlt nicht sinnvoll ist

    /**
     * Diese Klasse representiert den Wert den ein Exponat hat
     *
     * @param einkaufsWert         Einkaufswert des Exponats
     * @param aktuellerSchaetzwert Aktueller schätzwert des Exponats
     * @param leihwert             Leihwert des Exponats
     */
    public Exponatwert(float einkaufsWert, float aktuellerSchaetzwert, float leihwert) {
        this.einkaufsWert = einkaufsWert;
        this.aktuellerSchaetzwert = aktuellerSchaetzwert;
        this.leihwert = leihwert;
    }

    public float getEinkaufsWert() {
        return einkaufsWert;
    }

    public float getAktuellerSchaetzwert() {
        return aktuellerSchaetzwert;
    }

    public void setAktuellerSchaetzwert(float aktuellerSchaetzwert) {
        this.aktuellerSchaetzwert = aktuellerSchaetzwert;
    }

    public float getLeihwert() {
        return leihwert;
    }

    public void setLeihwert(float leihwert) {
        this.leihwert = leihwert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exponatwert)) return false;
        Exponatwert that = (Exponatwert) o;
        return Float.compare(that.getEinkaufsWert(), getEinkaufsWert()) == 0 &&
                Float.compare(that.getAktuellerSchaetzwert(), getAktuellerSchaetzwert()) == 0 &&
                Float.compare(that.getLeihwert(), getLeihwert()) == 0;
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
