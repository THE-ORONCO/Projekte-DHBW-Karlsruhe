/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Exponat.Exponat;
import Museum.MuseumsElement;

import java.util.HashMap;

public class MuseumsElementManager {

    private HashMap<String, MuseumsElement> museumsElemente;

    /**
     * mit diesem Manager lassen sich alle Museums-Elemente verwalten
     *
     * @param museumsElemente von diesem Manager verweltete Elemente
     */
    public MuseumsElementManager(HashMap<String, MuseumsElement> museumsElemente) {
        this.museumsElemente = museumsElemente;
    }

    public MuseumsElementManager() {
        this(new HashMap<String, MuseumsElement>());
    }

    public boolean contains(MuseumsElement element) {
        return this.museumsElemente.containsValue(element);
    }

    public boolean contains(String elementNr) {
        return this.museumsElemente.containsKey(elementNr);
    }

    public void persist(MuseumsElement element) throws Exception {
        if (this.contains(element)) {
            throw new Exception("Museumselement existiert bereits in diesem MuseumsElementManager!");
        }
        this.museumsElemente.put(element.getPrimaryKey(), element);
    }

    public MuseumsElement find(Class<?> c, Object key){
        for (MuseumsElement element: this.museumsElemente.values()){
            if( c.isInstance(element) && element.getPrimaryKey().equals(key) ){
                return element;
            }
        }
        return null;
    }

    public boolean remove(MuseumsElement element){
        if(this.contains(element)){
            this.museumsElemente.remove(element);
            return true;
        }
        return false;
    }

    public boolean remove(String elementNr){
        if(this.contains(elementNr)){
            this.museumsElemente.remove(this.museumsElemente.get(elementNr));
            return true;
        }
        return false;
    }
}
