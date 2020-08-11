package Museum.ObjectManagement;

import Museum.Exponat.Exponat;
import java.util.HashSet;

public class ExponatManager implements ElementManager{
    HashSet<Exponat> exponate;

    public ExponatManager(HashSet<Exponat> exponate) {
        this.exponate = exponate;
    }

    public ExponatManager() {
        this(new HashSet<Exponat>());
    }

    public boolean contains(Object element) {
        return this.exponate.contains(element);
    }

    public boolean persist(Object element) {
        return this.exponate.add((Exponat) element);
    }

    public boolean remove(Object element) {
        return this.exponate.remove(element);
    }

    public Object find(String inventarNr) {
        for(Exponat exponat : this.exponate){
            if(exponat.getPrimaryKey() == inventarNr){
                return exponat;
            }
        }
        return null;
    }

    public boolean edit(Object element) {
        if(this.contains(element)){
            this.remove(element);
            this.persist(element);
            return true;
        }
        return false;
    }
}
