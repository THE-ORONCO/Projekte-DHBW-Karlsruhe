/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.MuseumsElement;

import java.util.ArrayList;
import java.util.HashMap;

public class MuseumsElementManager{

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

    /**
     * gibt zurück ob ein Element in diesem MuseumsElementManager enthalten ist
     *
     * @param element das gesuchte Objekt
     * @return ob das Objekt enthalten ist als boolean
     */
    public boolean contains(MuseumsElement element) {
        return this.museumsElemente.containsValue(element);
    }

    /**
     * gibt zurück ob ein Element mit diesem Primarykey in diesem MuseumsElementManager enthalten ist
     *
     * @param primaryKey der Primarykey des gesuchte Objekts
     * @return ob das Objekt mit gesuchtem Primarykey enthalten ist als boolean
     */
    public boolean contains(String primaryKey) {
        return this.museumsElemente.containsKey(primaryKey);
    }

    /**
     * Überebe ein Objekt dem MuseumsElementManager zur Speicherung
     *
     * @param element zu speicherndes Element
     * @throws Exception wenn das Objekt oder ein Objekt mit gleichem Primarykey bereits vorhanden ist
     */
    public void persist(MuseumsElement element) throws Exception {
        if (this.contains(element)) {
            throw new Exception("Museumselement existiert bereits in diesem MuseumsElementManager!");
        } else if (this.contains(element.getPrimaryKey())) {
            throw new Exception("Museumselement mit diesem PrimaryKey existiert bereits in diesem MuseumsElementManager!");
        }
        this.museumsElemente.put(element.getPrimaryKey(), element);
    }

    /**
     * finde ein Element anhand des Primarykeys
     *
     * @param c   Klasse des gesuchten Objekts
     * @param key Primarykey des gesuchten Objekts
     * @return das gesuchte Objekt oder null
     */
    public MuseumsElement find(Class<?> c, Object key) {
        for (MuseumsElement element : this.museumsElemente.values()) {
            if (c.isInstance(element) && element.getPrimaryKey().equals(key)) {
                return element;
            }
        }
        return null;
    }

    /**
     * entferne das gegebene Objekt aus dem MuseumselementManager
     *
     * @param element zu entfernendes Element
     * @return ob ein solches Objekt vorhanden war und es somit entfernt wurde als boolean
     */
    public boolean remove(MuseumsElement element) {
        if (this.contains(element)) {
            this.museumsElemente.remove(element.getPrimaryKey());
            return true;
        }
        return false;
    }

    /**
     * entferne das gegebene Objekt aus dem MuseumselementManager
     *
     * @param primaryKey Primarykey des zu entfernendes Element
     * @return ob ein solches Objekt vorhanden war und es somit entfernt wurde als boolean
     */
    public boolean remove(String primaryKey) {
        if (this.contains(primaryKey)) {
            this.museumsElemente.remove(primaryKey);
            return true;
        }
        return false;
    }

    public HashMap<String, MuseumsElement> getMuseumsElemente(){
        return this.museumsElemente;
    }

    /**
     * Diese Methode löscht alle Einträge im MuseumsElementManager
     */
    public void clear(){
        this.museumsElemente = new HashMap<>();
    }

    public ArrayList<String[]> parseToCSV(){
        ArrayList<String[]> csvStrings = new ArrayList<>();
        for(MuseumsElement element: this.museumsElemente.values()){
            csvStrings.add(element.parsToCSV());
        }
        return csvStrings;
    }

    public String[] getCSVHeader() throws Exception {
        if(this.museumsElemente.size() <= 0){
            throw new Exception("ElementManger hat noch keine Elemente. Die beschaffenheit der Elemente kann nicht bestimmt werden");
        }

        // methode mit Reflection-API abfragen
        Class<?> relevanteKlasse = this.museumsElemente.get(0).getClass();
        return (String[]) relevanteKlasse.getMethod("getCSVHeader").invoke(null);
    }
}
