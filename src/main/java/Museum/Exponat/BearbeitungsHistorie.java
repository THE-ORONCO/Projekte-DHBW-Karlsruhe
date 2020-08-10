package Museum.Exponat;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class BearbeitungsHistorie extends Historie{
    private final HashMap<Date, Ereignis> ereignisse;

    public BearbeitungsHistorie(HashMap<Date, Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
    }

    public BearbeitungsHistorie() {
        this(new HashMap<Date, Ereignis>());
    }

}
