package Museum.Exponat;

import java.util.Date;
import java.util.HashSet;

public class BearbeitungsHistorie implements Historie{
    public boolean addEreignis(Ereignis ereignis) {
        return false;
    }

    public Ereignis findEreignis(Date datum) {
        return null;
    }

    public Ereignis findEreignis(Ereignis ereignis) {
        return null;
    }

    public boolean modifyEreignis(Date datum, String beschreibung) {
        return false;
    }

    public boolean modifyEreignis(Ereignis ereignis) {
        return false;
    }

    public boolean removeEreignis(Date datum) {
        return false;
    }

    public boolean removeEreignis(Ereignis ereignis) {
        return false;
    }

    public HashSet<Ereignis> getEreignisse() {
        return null;
    }
}
