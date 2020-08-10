package Museum.Exponat;

import java.util.Date;
import java.util.HashSet;

public class BesitzHistorie implements Historie {

    private HashSet<Ereignis> ereignisse;

    public BesitzHistorie(HashSet<Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
    }

    public BesitzHistorie() {
        this(new HashSet<Ereignis>());
    }

    public boolean addEreignis(Ereignis ereignis) {
        return this.ereignisse.add(ereignis);
    }

    public Ereignis findEreignis(Date datum) {
        for (Ereignis ereignis : this.ereignisse) {
            if (ereignis.getDatum() == datum) {
                return ereignis;
            }
        }
        return null;
    }

    public boolean modifyEreignis(Date datum, String beschreibung) {
        return modifyEreignis(new Ereignis(datum, beschreibung));
    }

    public boolean modifyEreignis(Ereignis ereignis) {
        Ereignis altesEreigns = findEreignis(ereignis.getDatum());
        if (altesEreigns != null) {
            this.ereignisse.remove(altesEreigns);
            this.ereignisse.add(ereignis);
            return true;
        }
        return false;
    }

    public boolean removeEreignis(Date datum) {
        Ereignis ereignis = findEreignis(datum);
        return removeEreignis(ereignis);
    }

    public boolean removeEreignis(Ereignis ereignis) {
        return this.ereignisse.remove(ereignis);
    }

    public HashSet<Ereignis> getEreignisse() {
        return this.ereignisse;
    }
}
