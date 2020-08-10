package Museum.Exponat;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Historie {
    //DIFF abstract statt interface weil die Methoden alle gleich implementiert sind
    /* TODO vlt sollte das eher eine abstrakte Klasse sein oder vielleicht alles eine einzelne Klasse anstatt das in 4 aufzuteilen
     da sonst in jeder Historie die gleichen Methoden nochmals implementiert und die Ereignisse instanziert werden müssen
     für konkrete Implementierung der Methoden siehe Besitzhistorie.java*/
    private HashMap<Date, Ereignis> ereignisse;

    public void addEreignis(Ereignis ereignis){
        this.ereignisse.put(ereignis.getDatum(), ereignis);
    }

    public Ereignis findEreignis(Date datum){
        return this.ereignisse.get(datum);
    }

    /**
     * Diese Methode modifiziert ein Ereignis in der jeweiligen Historie
     * @param datum
     * @param beschreibung
     * @return es wird ein boolean zurückgegeben der anzeigt ob ein Ereignis mit gegebenem Datum vorhanden war um es zu bearbeiten.
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
