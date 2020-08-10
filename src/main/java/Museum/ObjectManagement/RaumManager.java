package Museum.ObjectManagement;

import Museum.Raum.Raum;

import java.util.HashSet;

public class RaumManager implements ElementManager {

    private HashSet<Raum> raume;

    public RaumManager(HashSet<Raum> raume) {
        this.raume = raume;
    }

    public RaumManager(){
        this(new HashSet<Raum>());
    }

    public boolean contains(Object element) {
        return raume.contains(element);
    }

    public boolean persist(Object element) {
        return raume.add((Raum) element);
    }

    public boolean remove(Object element) {
        return this.raume.remove(element);
    }

    public Object find(int raumNr) {
        for(Raum raum : this.raume){
            if(raum.getRaumNr() == raumNr){
                return raum;
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
