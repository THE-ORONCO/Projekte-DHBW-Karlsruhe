package Museum.Exponat;

import java.util.Date;

public class Ereignis {
    private Date datum;
    private String beschreibung;

    public Ereignis(Date datum, String beschreibung){
        this.datum = datum;
        this.beschreibung = beschreibung;
    }

    public Ereignis(Date datum){
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
}
