/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.MuseumsElement;
import Museum.Person.Foerderer;
import Museum.Person.Person;
import Museum.Raum.Raum;
import Museum.StringProcessor;
import de.dhbwka.swe.utils.util.AppLogger;
import de.dhbwka.swe.utils.util.CSVWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MuseumsManager {

    private static final HashMap<Class<? extends MuseumsElement>, ArrayList<MuseumsElement>> DEFAULT_ELEMENTE = new HashMap<>();

    // MuseumsElementManager die die verschiedenen Objekte des Museums speichern
    private final static MuseumsElementManager personenM = new MuseumsElementManager(); //TODO das hier könnte man aufteilen, warum haben wir das nicht?
    private final static MuseumsElementManager raumM = new MuseumsElementManager();
    private final static MuseumsElementManager exponatM = new MuseumsElementManager(); // TODO vielleicht eine Methode zum tracken der Änderungen am Objekt einbauen
    private final static MuseumsElementManager epochenM = new MuseumsElementManager(); // DIFF EpochenManager
    private final static MuseumsElementManager bildM = new MuseumsElementManager(); // DIFF BildManager
    //TODO vielleicht eine globale Liste für alle Kategorien wenn noch Zeit ist

    public static MuseumsElementManager getPersonenManager() {
        return personenM;
    }

    public static MuseumsElementManager getRaumManager() {
        return raumM;
    }

    public static MuseumsElementManager getExponatManager() {
        return exponatM;
    }

    public static MuseumsElementManager getEpochenManager() {
        return epochenM;
    }

    public static MuseumsElementManager getBildManager() {
        return bildM;
    }

    /**
     * Diese Methode testet ob ein MuseumsElement mit einem bestimmte Primarykey im Museum vorhanden ist
     *
     * @param c          Klasse des MuseumsElements
     * @param primaryKey Key des gesuchtes Museumselement
     * @return ob ein Element mit dem Primarykey vorhanden ist
     */
    public static boolean contains(Class<?> c, String primaryKey) {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        return relevanterManager.contains(primaryKey);
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

    /**
     * Diese Methode speichert ein Objekt in den jeweils zuständigen Manager
     *
     * @param c       Klasse des zu speichernden Objekts
     * @param element das zu speichernde Objekt
     * @throws Exception wenn das Objekt oder ein Objekt mit gleichem PrimaryKey bereits vorhanden ist
     */
    public static void persist(Class<?> c, MuseumsElement element) throws Exception {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        relevanterManager.persist(element);
    }

    /**
     * finde ein Objekt anhand seines PrimaryKeys
     *
     * @param c          Klasse des gesuchten Objekts
     * @param primaryKey PrimaryKey des gesuchten Objekts
     * @return das gesuchte Objekt
     */
    public static MuseumsElement find(Class<?> c, String primaryKey) {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        return relevanterManager.find(c, primaryKey);
    }

    /**
     * Entferne das Objekt mit dem gegebenen PrimaryKey aus dem MuseumsManager
     *
     * @param c          Klasse des Objekts
     * @param primaryKey PrimaryKey des zu entfernenden Objekts
     * @return true wenn das Objekt vorhanden war und entfernt wurde; false wenn es nicht vorhanden war
     */
    public static boolean remove(Class<?> c, String primaryKey) {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        return relevanterManager.remove(primaryKey);
    }

    /**
     * Entferne das Objekt mit dem gegebenen PrimaryKey aus dem MuseumsManager
     *
     * @param element das zu entfernende Objekt
     * @return true wenn das Objekt vorhanden war und entfernt wurde; false wenn es nicht vorhanden war
     */
    public static boolean remove(MuseumsElement element) {
        Class<? extends MuseumsElement> c = element.getClass();
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        return relevanterManager.remove(element);
    }

    @Deprecated
    public static void importieren(Class<?> c, String dateiPfad) throws Exception {
        for (MuseumsElement element : MuseumsElementFactory.createElement(c, dateiPfad)) {
            persist(c, element);
        }
    }

    // Standartmethode exportiert im CSV-Format
    public static void exportieren(Class<?> c, String path, boolean ueberschreiben) throws Exception {
        //TODO ueberschreiben einbauen
        //TODO exportieren ausgiebig testen!
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);

        ArrayList<String[]> csvData = relevanterManager.parseToCSV();

        CSVWriter writer = new CSVWriter(path, true);
        writer.writeDataToFile(csvData, relevanterManager.getCSVHeader());
    }

    /**
     * Diese Methode wählt den MuseumsElementManager der für die gegebene Objektklasse zuständig ist.
     *
     * @param c Klasse die verwaltet werden soll
     * @return den für diese Klasse vorgesehenen Manager
     */
    private static MuseumsElementManager waehleRelevantenManager(Class<?> c) {
        if (Person.class.isAssignableFrom(c)) {
            return personenM;
        } else if (c == Raum.class) {
            return raumM;
        } else if (c == Exponat.class) {
            return exponatM;
        } else if (c == Epoche.class) {
            return epochenM;
        } else if (c == Bild.class) {
            return bildM;
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
    }

    /**
     * Diese Methode generiert einen neuen noch unbenutzen PrimaryKey für die gegebene Klasse.
     *
     * @param c Klasse für die ein neuer PrimaryKey generiert werden soll
     * @return einen unbenutzen PrimaryKey
     */
    public static String generiereUnbenutzenSchluessel(Class<?> c) {
        char startingCharacter = StringProcessor.waehleKeyStartCharakter(c);

        MuseumsElementManager relevantenManager = waehleRelevantenManager(c);

        String key = "";

        do {
            key = startingCharacter + StringProcessor.generiereRandomAlphaNumString();
        } while (relevantenManager.contains(key));
        return key;
    }

    /**
     * Diese methode löscht alle Einträge im Museumsmanager. !!!Achtung!!! dies ist eher um das System zu testen
     */
    public static void clearAlles() {
        personenM.clear();
        raumM.clear();
        exponatM.clear();
        epochenM.clear();
        bildM.clear();
    }

    /**
     * Diese Methode läd die default Elemente für Bild, Epoche, Foerderer, Raum
     *
     * @param path        Pfad zum default-Ordner in welchem die Dateien mit Default-Daten liegen oder liegen sollen
     * @throws Exception wenn beim Lese/Schreib-Prozess schiefgeht
     */
    public static void ladeDefaultElemente(String path) throws Exception {
        Class<? extends MuseumsElement>[] defaultElementKlassen = new Class[]{Bild.class, Epoche.class, Foerderer.class, Raum.class};
        ladeDefaultElemente(path, defaultElementKlassen);
    }

    /**
     * Diese Methode läd die default Elemente für eine gegebene Liste an Klassen aus Dateien beziehungsweise läd
     * Default-Werte aus gegebenen Dateien.
     * Die geladenen Werte werden in der HashMap DEFAULT_ELELMENTE mit den Klassen als Key abgelegt.
     *
     *
     * @param path                  Pfad zum default-Ordner in welchem die Dateien mit Default-Daten liegen oder liegen sollen
     * @param defaultElementKlassen Klassen für die die Default-Elemente geladen werden sollen
     * @throws Exception wenn beim Lese/Schreib-Prozess schiefgeht
     */
    public static void ladeDefaultElemente(String path, Class<? extends MuseumsElement>[] defaultElementKlassen) throws Exception {
        ArrayList<MuseumsElement> defaultElemente = new ArrayList<>();

        for (Class<? extends MuseumsElement> typ : defaultElementKlassen) {
            // generiere einen neuen CSV-Reader für jede gelesene Datei
            String name = typ.getSimpleName();
            String dateiPfad = path.endsWith("/") ? path + name + ".csv" : path + "/" + name + ".csv";
            File defaultDatei = new File(dateiPfad);
            if (defaultDatei.exists()) {
                defaultElemente = MuseumsElementFactory.createElement(typ, dateiPfad);

            } else {
                // hard gecodede Default-Daten laden --> nicht empfohlen!!!
                String fallbackDateiPfad = "/mnt/data/the_oronco/Desktop/Projekte-DHBW-Karlsruhe/src/main/fallback-resources/default/" + name + ".csv";//TODO relativen Pfad rausfinden
                defaultElemente = MuseumsElementFactory.createElement(typ, fallbackDateiPfad);
                AppLogger.getInstance().warning("Default-Daten für " + name + " ohne Datei geladen");
            }

            MuseumsManager.DEFAULT_ELEMENTE.put(typ, defaultElemente);
        }
    }

    /**
     * Diese Methode gibt das default Element für die gegebene Klasse zurück.
     *
     * @param c Klasse für das das default Element gesucht wird
     * @return Default-MuseumsElement für die gegebene Klasse
     */
    public static MuseumsElement getDefault(Class<? extends MuseumsElement> c) {
        return DEFAULT_ELEMENTE.get(c).get(0);
    }
}
