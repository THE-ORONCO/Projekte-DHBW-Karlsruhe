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
import de.dhbwka.swe.utils.util.CSVWriter;

import java.util.ArrayList;

public class MuseumsManager {
    private final static MuseumsElementManager personenM = new MuseumsElementManager(); //TODO das hier könnte man aufteilen, warum haben wir das nicht?
    private final static MuseumsElementManager raumM = new MuseumsElementManager();
    private final static MuseumsElementManager exponatM = new MuseumsElementManager();
    private final static MuseumsElementManager epochenM = new MuseumsElementManager(); // DIFF EpochenManager
    private final static MuseumsElementManager bildM = new MuseumsElementManager(); // DIFF BildManager

    //TODO standartwerte in den Museumsmanager laden (default-bild, Lagerraum und co)

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

    public static void persist(Class<?> c, MuseumsElement element) throws Exception {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        relevanterManager.persist(element);
    }

    public static MuseumsElement find(Class<?> c, String primaryKey) {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        return relevanterManager.find(c, primaryKey);
    }

    public static boolean remove(Class<?> c, String primaryKey) {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        return relevanterManager.remove(primaryKey);
    }

    public static boolean remove(Class<?> c, MuseumsElement element) {
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
        return relevanterManager.remove(element);
    }

    @Deprecated
    public static void importieren(Class<?> c, String dateiPfad) throws Exception {
        // TODO vielleicht hier exception handling einfügen -> methode gibt false zurück wenn das importieren fehlgeschlagen ist
        for (MuseumsElement element : MuseumsElementFactory.createElement(c, dateiPfad)) {
            persist(c, element);
        }
    }

    // Standartmethode exportiert im CSV-Format
    public static boolean exportieren(Class<?> c, String path, boolean ueberschreiben) throws Exception {
        // TODO export methode
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);

        ArrayList<String[]> csvData = relevanterManager.parseToCSV();

        CSVWriter writer = new CSVWriter(path, true);
        writer.writeDataToFile(csvData, relevanterManager.getCSVHeader());


        return false;
    }

    private static MuseumsElementManager waehleRelevantenManager(Class<?> c) {
        if (c == Person.class) {
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

}
