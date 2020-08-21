/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Exponat;

import Museum.ObjectManagement.CSVSeparationLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Historie {
    //DIFF nur eine Historie, da alle Historien praktisch das gleiche machen

    private HashMap<Date, Ereignis> ereignisse;

    /**
     * Eine Historie ist eine Ansammlung an verschiedensten Ereignissen die sich aus dem Datum und einer Beschreibung zusammensetzen.
     *
     * @param ereignisse sind die Ereignisse die in dieser Historie festgehalten werden
     */
    public Historie(HashMap<Date, Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
    }

    public Historie() {
        this(new HashMap<>());
    }

    public void addEreignis(Ereignis ereignis) {
        this.ereignisse.put(ereignis.getDatum(), ereignis);
    }

    public Ereignis findEreignis(Date datum) {
        return this.ereignisse.get(datum);
    }

    /**
     * Diese Methode modifiziert ein Ereignis in der jeweiligen Historie
     *
     * @param datum
     * @param beschreibung
     * @return es wird ein boolean zurückgegeben der anzeigt ob ein Ereignis mit gegebenem Datum vorhanden war und es bearbeitet wurde.
     */
    public boolean modifyEreignis(Date datum, String beschreibung) {
        if (this.ereignisse.containsKey(datum)) {
            this.ereignisse.put(datum, new Ereignis(datum, beschreibung));
            return true;
        }
        return false;
    }

    public boolean modifyEreignis(Ereignis ereignis) {
        if (this.ereignisse.containsKey(ereignis.getDatum())) {
            this.ereignisse.put(ereignis.getDatum(), ereignis);
            return true;
        }
        return false;
    }

    public Ereignis removeEreignis(Date datum) {
        return this.ereignisse.remove(datum);
    }

    public Ereignis removeEreignis(Ereignis ereignis) {
        return this.ereignisse.remove(ereignis.getDatum());
    }

    public HashMap<Date, Ereignis> getEreignisse() {
        return this.ereignisse;
    }

    @Override
    public String toString() {
        String historie = "";
        for (Date datum : this.ereignisse.keySet()) {
            historie += String.format("%s%n", ereignisse.get(datum).toString());
        }
        return historie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Historie)) return false;
        Historie historie = (Historie) o;
        return getEreignisse().equals(historie.getEreignisse());
    }

    public String[] parseToCSV() {
        ArrayList<String> csvData = new ArrayList<>();
        for (Ereignis ereignis : this.ereignisse.values()) {
            csvData.add(String.join(CSVSeparationLevel.LEVEL3.toString(), ereignis.parsToCSV()));
        }
        return csvData.toArray(new String[csvData.size()]);
    }
}
