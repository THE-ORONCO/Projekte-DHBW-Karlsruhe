/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.MuseumsElement;
import Museum.Person.Person;
import Museum.Raum.Raum;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class MuseumsManager { //TODO das hier könnte komplett static sein, da es nur einen geben darf
    private static MuseumsElementManager personenM = new MuseumsElementManager(); //TODO das hier könnte man aufteilen, warum haben wir das nicht?
    private static MuseumsElementManager raumM = new MuseumsElementManager();
    private static MuseumsElementManager exponatM = new MuseumsElementManager();
    private static MuseumsElementManager epochenM = new MuseumsElementManager(); // DIFF EpochenManager
    private static MuseumsElementManager bildM = new MuseumsElementManager(); // DIFF BildManager

    public MuseumsManager(MuseumsElementManager personenM, MuseumsElementManager raumM, MuseumsElementManager exponatM, MuseumsElementManager epochenM, MuseumsElementManager bildM) {
        MuseumsManager.personenM = personenM;
        MuseumsManager.raumM = raumM;
        MuseumsManager.exponatM = exponatM;
        MuseumsManager.epochenM = epochenM;
        MuseumsManager.bildM = bildM;
    }

    public MuseumsManager() {
        this(new MuseumsElementManager(), new MuseumsElementManager(), new MuseumsElementManager(), new MuseumsElementManager(), new MuseumsElementManager());
    }

    public MuseumsElementManager getPersonenM() {
        return personenM;
    }

    public void setPersonenM(MuseumsElementManager personenM) {
        MuseumsManager.personenM = personenM;
    }

    public MuseumsElementManager getRaumM() {
        return raumM;
    }

    public void setRaumM(MuseumsElementManager raumM) {
        MuseumsManager.raumM = raumM;
    }

    public MuseumsElementManager getExponatM() {
        return exponatM;
    }

    public void setExponatM(MuseumsElementManager exponatM) {
        MuseumsManager.exponatM = exponatM;
    }

    public MuseumsElementManager getEpochenM() {
        return epochenM;
    }

    public void setEpochenM(MuseumsElementManager epochenM) {
        MuseumsManager.epochenM = epochenM;
    }

    /**
     * Diese Methode testet ob ein MuseumsElement mit einem bestimmte Primarykey im Museum vorhanden ist
     *
     * @param c          Klasse des MuseumsElements
     * @param primaryKey Key des gesuchtes Museumselement
     * @return ob ein Element mit dem Primarykey vorhanden ist
     */
    public static boolean contains(Class<?> c, String primaryKey) {
        if (c == Person.class) {
            return personenM.contains(primaryKey);
        } else if (c == Raum.class) {
            return raumM.contains(primaryKey);
        } else if (c == Exponat.class) {
            return exponatM.contains(primaryKey);
        } else if (c == Epoche.class) {
            return epochenM.contains(primaryKey);
        } else if (c == Bild.class) {
            return bildM.contains(primaryKey);
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
    }

    /**
     * Diese Methode testet ob ein MuseumsElement im Muesum vorhanden ist
     *
     * @param c       Klasse des MuseumsElements
     * @param element Key des gesuchtes Museumselement
     * @return ob das Element vorhanden ist
     */
    public static boolean contains(Class<?> c, MuseumsElement element) {
        return contains(c, element.getPrimaryKey());
    }

    public static void persist(Class<?> c, MuseumsElement element) throws Exception {
        if (c == Person.class) {
            personenM.persist(element);
        } else if (c == Raum.class) {
            raumM.persist(element);
        } else if (c == Exponat.class) {
            exponatM.persist(element);
        } else if (c == Epoche.class) {
            epochenM.persist(element);
        } else if (c == Bild.class) {
            bildM.persist(element);
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
    }

    public static MuseumsElement find(Class<?> c, String primaryKey) {
        if (c == Person.class) {
            return personenM.find(c, primaryKey);
        } else if (c == Raum.class) {
            return raumM.find(c, primaryKey);
        } else if (c == Exponat.class) {
            return exponatM.find(c, primaryKey);
        } else if (c == Epoche.class) {
            return epochenM.find(c, primaryKey);
        } else if (c == Bild.class) {
            return bildM.find(c, primaryKey);
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
    }

    public static boolean remove(Class<?> c, String primaryKey) {
        if (c == Person.class) {
            return personenM.remove(primaryKey);
        } else if (c == Raum.class) {
            return raumM.remove(primaryKey);
        } else if (c == Exponat.class) {
            return exponatM.remove(primaryKey);
        } else if (c == Epoche.class) {
            return epochenM.remove(primaryKey);
        } else if (c == Bild.class) {
            return bildM.remove(primaryKey);
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
    }

    public static boolean remove(Class<?> c, MuseumsElement element) {
        if (c == Person.class) {
            return personenM.remove(element);
        } else if (c == Raum.class) {
            return raumM.remove(element);
        } else if (c == Exponat.class) {
            return exponatM.remove(element);
        } else if (c == Epoche.class) {
            return epochenM.remove(element);
        } else if (c == Bild.class) {
            return bildM.remove(element);
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
    }

    @Deprecated
    public void importieren(Class<?> c, String dateiPfad) throws Exception {
        // TODO vielleicht hier exception handling einfügen -> methode gibt false zurück wenn das importieren fehlgeschlagen ist
        for (MuseumsElement element : MuseumsElementFactory.createElement(c, dateiPfad)) {
            persist(c, element);
        }
    }

    // Standartmethode exportiert im CSV-Format
    public boolean exportieren(Class<?> c, String path, boolean ueberschreiben) {
        // TODO export methode
        MuseumsElementManager relevanterManager;
        if (c == Person.class) {
            relevanterManager = personenM;
        } else if (c == Raum.class) {
            relevanterManager = raumM;
        } else if (c == Exponat.class) {
            relevanterManager = exponatM;
        } else if (c == Epoche.class) {
            relevanterManager = epochenM;
        } else if (c == Bild.class) {
            relevanterManager = bildM;
        } else {
            throw new ValueException("Falsche Klasse angegeben");
        }
        return false;
    }

}
