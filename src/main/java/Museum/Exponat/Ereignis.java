package Museum.Exponat;

import Museum.ObjectManagement.CSVSeparationLevel;

import java.text.SimpleDateFormat;
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

    public String[] parsToCSV(){
        String pattern = "yyyy.MM.dd";
        String[] csvData = new String[]{
                new SimpleDateFormat(pattern).format(this.datum),
                this.beschreibung
        };
        return csvData;
    }

    public static void main(String[] args) {
        String pattern = "yyyy.MM.dd";

        Date d = new Date();
        System.out.println(CSVSeparationLevel.LEVEL3);
        System.out.println(new SimpleDateFormat(pattern).format(d));
    }
    /*
    2020.08.01 | Erschaffung der Mona Lisa , 2020.08.02|Mona Lisa wurde von unserem Museum aufgekauft
     */

}
