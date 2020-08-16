package Museum.Exponat;

import java.util.Date;
import java.util.HashMap;

public class Historie {
    //DIFF nur eine Historie, da alle Historien praktisch das gleiche machen

    private HashMap<Date, Ereignis> ereignisse;

    /**
     * Eine Historie ist eine Ansammlung an verschiedensten Ereignissen die sich aus dem Datum und einer Beschreibung zusammensetzen.
     *
     *  @param ereignisse sind die Ereignisse die in dieser Historie festgehalten werden
     */
    public Historie(HashMap<Date, Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
    }

    public void addEreignis(Ereignis ereignis){
        this.ereignisse.put(ereignis.getDatum(), ereignis);
    }

    public Ereignis findEreignis(Date datum){
        return this.ereignisse.get(datum);
    }

    /**
     * Diese Methode modifiziert ein Ereignis in der jeweiligen Historie
     *
     * @param datum
     * @param beschreibung
     * @return es wird ein boolean zurückgegeben der anzeigt ob ein Ereignis mit gegebenem Datum vorhanden war und es bearbeitet wurde.
     */
    public boolean modifyEreignis(Date datum, String beschreibung){
        if(this.ereignisse.containsKey(datum)){
            this.ereignisse.put(datum, new Ereignis(datum, beschreibung));
            return true;
        }
        return false;
    }

    public boolean modifyEreignis(Ereignis ereignis){
        if (this.ereignisse.containsKey(ereignis.getDatum())){
            this.ereignisse.put(ereignis.getDatum(), ereignis);
            return true;
        }
        return false;
    }

    public Ereignis removeEreignis(Date datum){
        return this.ereignisse.remove(datum);
    }

    public Ereignis removeEreignis(Ereignis ereignis){
        return this.ereignisse.remove(ereignis.getDatum());
    }

    HashMap<Date, Ereignis> getEreignisse(){
        return this.ereignisse;
    }
}
