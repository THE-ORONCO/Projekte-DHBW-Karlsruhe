package Museum.ObjectManagement;

import Museum.Raum.Raum;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.HashMap;

public class RaumManager{//DIFF ElementManager nicht implementiert
    // TODO wie machen wir das mit ElementManager; um ein Interface zu verwenden müssten alle verwalteten Elemente vom gleichen Interface oder Oberklasse kommen

    private HashMap<Integer, Raum> raume; //DIFF HahMap statt HashSet, da die Räume einfach mit der raumNr identifiziert werden können

    /**
     * Manager-Objekt für Raum-Objekte. Unter anderem verhindert es das mehrere Räume mit gleicher Raumnummer existieren
     * @param raume Räume die in diesem Manager-Objekt verwaltet werden
     */
    public RaumManager(HashMap<Integer, Raum> raume) {
        this.raume = raume;
    }

    public RaumManager(){
        this(new HashMap<Integer, Raum>());
    }

    public boolean contains(Raum raum) {
        return raume.containsValue(raum);
    }

    public boolean contains(int raumNr){
        return raume.containsKey(raumNr);
    }

    public void persist(Raum raum) throws ValueException{
        if(this.contains(raum.getRaumNr())){
            throw new ValueException("Raum mit der raumNr bereits vorhanden!");
        }
        raume.put(raum.getRaumNr(), raum);
    }

    public boolean remove(Raum raum) {
        if(this.contains(raum) ){
            raume.remove(raum);
            return true;
        }
        return false;
    }

    public boolean remove(int raumNr){
        if(this.contains(raumNr)){
            raume.remove(raume.get(raumNr));
            return true;
        }
        return false;
    }

    public Object find(int raumNr) {
        return this.raume.get(raumNr);
    }

    public boolean edit(Raum raum) {
        if(this.contains(raum)){
            raume.put(raum.getRaumNr(), raum);
            return true;
        }
        return false;
    }
}
