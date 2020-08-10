package Museum.Exponat;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class BesitzHistorie extends Historie {

    private final HashMap<Date, Ereignis> ereignisse;

    public BesitzHistorie(HashMap<Date, Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
    }

    public BesitzHistorie() {
        this(new HashMap<Date, Ereignis>());
    }
}
