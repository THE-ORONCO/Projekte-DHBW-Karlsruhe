package Museum.ObjectManagement;

import Museum.Exponat.Epoche;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.HashMap;

public class EpochenMannager { //DIFF Klasse wurde erstellt um die Epochen besser zu verwalten
    private HashMap< String,Epoche> epochen = new HashMap<String, Epoche>();

    public boolean contains(Epoche e){
        return this.epochen.containsValue(e);
    }

    public void persist(Epoche e) throws Exception{
        if(this.contains(e)){
            throw new ValueException("Epoche ist bereits im System");
        }else this.epochen.put(e.toString(), e);
    }

    public Epoche find(String key){
        return this.epochen.get(key);
    }

    public Epoche remove(Epoche e){
        return this.remove(e.toString());
    }

    public Epoche remove(String key){
        return this.epochen.remove(key);
    }
}
