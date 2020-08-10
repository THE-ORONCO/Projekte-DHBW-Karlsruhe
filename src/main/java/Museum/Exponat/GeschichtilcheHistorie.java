package Museum.Exponat;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class GeschichtilcheHistorie extends Historie{
    private final HashMap<Date, Ereignis> ereignisse;

    public GeschichtilcheHistorie(HashMap<Date, Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
    }

    public GeschichtilcheHistorie() {
        this(new HashMap<Date, Ereignis>());
    }
}
