package Museum.Exponat;

import java.util.Date;
import java.util.HashSet;

public interface Historie {
    /* TODO vlt sollte das eher eine abstrakte Klasse sein:
     da sonst in jeder Historie die gleichen Methoden nochmals implementiert und die Ereignisse instanziert werden müssen
     für konkrete Implementierung der Methoden siehe Besitzhistorie.java*/

    boolean addEreignis(Ereignis ereignis);

    Ereignis findEreignis(Date datum);

    boolean modifyEreignis(Date datum, String beschreibung);

    boolean modifyEreignis(Ereignis ereignis);

    boolean removeEreignis(Date datum);

    boolean removeEreignis(Ereignis ereignis);

    HashSet<Ereignis> getEreignisse();
}
