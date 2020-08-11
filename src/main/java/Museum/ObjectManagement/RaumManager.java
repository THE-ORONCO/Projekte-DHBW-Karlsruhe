package Museum.ObjectManagement;

import Museum.Raum.Raum;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.HashMap;
import java.util.SplittableRandom;

public class RaumManager{//DIFF ElementManager nicht implementiert
    // TODO wie machen wir das mit ElementManager; um ein Interface zu verwenden müssten alle verwalteten Elemente vom gleichen Interface oder Oberklasse kommen

    private HashMap<String, Raum> raume; //DIFF HahMap statt HashSet, da die Räume einfach mit der raumNr identifiziert werden können

    /**
     * Manager-Objekt für Raum-Objekte. Unter anderem verhindert es das mehrere Räume mit gleicher Raumnummer existieren
     * @param raume Räume die in diesem Manager-Objekt verwaltet werden
     */
    public RaumManager(HashMap<String, Raum> raume) {
        this.raume = raume;
    }

    public RaumManager(){
        this(new HashMap<String, Raum>());
    }

    public boolean contains(Raum raum) {
        return raume.containsValue(raum);
    }

    public boolean contains(String raumNr){
        return raume.containsKey(raumNr);
    }

    public void persist(Raum raum) throws ValueException{
        if(this.contains(raum.getPrimaryKey())){
            throw new ValueException("Raum mit der raumNr bereits vorhanden!");
        }
        raume.put(raum.getPrimaryKey(), raum);
    }

    public boolean remove(Raum raum) {
        if(this.contains(raum) ){
            raume.remove(raum);
            return true;
        }
        return false;
    }

    public boolean remove(String raumNr){
        if(this.contains(raumNr)){
            raume.remove(raume.get(raumNr));
            return true;
        }
        return false;
    }

    public Object find(String raumNr) {
        return this.raume.get(raumNr);
    }

    public boolean edit(Raum raum) {
        if(this.contains(raum)){
            raume.put(raum.getPrimaryKey(), raum);
            return true;
        }
        return false;
    }
}
