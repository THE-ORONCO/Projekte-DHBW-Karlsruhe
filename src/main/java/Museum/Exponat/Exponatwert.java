package Museum.Exponat;

public class Exponatwert {

    private final float einkaufsWert;
    private float aktuellerSchätzwert;
    private float leihwert; // DIFF leihwert statt leihwerte

    public Exponatwert(float einkaufsWert, float aktuellerSchätzwert, float leihwert) {
        this.einkaufsWert = einkaufsWert;
        this.aktuellerSchätzwert = aktuellerSchätzwert;
        this.leihwert = leihwert;
    }

    public float getEinkaufsWert() {
        return einkaufsWert;
    }

    public float getAktuellerSchätzwert() {
        return aktuellerSchätzwert;
    }

    public void setAktuellerSchätzwert(float aktuellerSchätzwert) {
        this.aktuellerSchätzwert = aktuellerSchätzwert;
    }

    public float getLeihwert() {
        return leihwert;
    }

    public void setLeihwert(float leihwert) {
        this.leihwert = leihwert;
    }
}
