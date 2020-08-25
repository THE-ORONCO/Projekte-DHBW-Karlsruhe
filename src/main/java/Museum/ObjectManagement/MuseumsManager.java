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
import de.dhbwka.swe.utils.util.CSVReader;
import de.dhbwka.swe.utils.util.CSVWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class MuseumsManager {

    // Default Werte für CSV-Dateien wenn diese nicht vorhanden sind
    private static final HashMap<Class<? extends MuseumsElement>, ArrayList<String[]>> DEFAULT_CSV_DATEN =
            new HashMap<Class<? extends MuseumsElement>, ArrayList<String[]>>() {{
                put(Bild.class, new ArrayList<String[]>() {{
                    add(new String[]{"bildNr", "altText", "dateiName", "beschreibung"});
                    add(new String[]{"b0", "default Bild", "bilder/default.png", "default Bild"});
                }});
                put(Epoche.class, new ArrayList<String[]>() {{
                    add(new String[]{"epochenID", "Epoche", "Stil", "Zeitalter", "Beschreibung"});
                    add(new String[]{"e0", "unbekannte Epoche", "kein bekannter Stil", "unbekanntes Zeitalter", "Beschreibung"});
                }});
                put(Foerderer.class, new ArrayList<String[]>() {{
                    add(new String[]{"foerdererNr", "name", "gebDatum", "beschreibung", "kontakt", "gefoerdererteExponate", "bild"});
                    add(new String[]{"f0", "Das Museum", "2020.01.01", "Museum default Foerderer", "swe.museum@baum.uff | muse@um.swe, 010101010101 | 081532020, SWE-Museum_ Museum_ Museumsstrasse_ 1_ 76149_ Karlsruhe_ Deutschland | 42069_ 76149_ Karlsruhe_ Deutschland", "", "b0"});
                }});
                put(Raum.class, new ArrayList<String[]>() {{
                    add(new String[]{"raumNr", "ausstellungsflaeche", "ausstellungsthema", "bilder", "ausgestellteExponate", "beschreibung"});
                    add(new String[]{"r0", "209.3", "alles", "b10", "x2, x4, x5", "Raum in dem alles abgestellt wir was aktuell nicht ausgestellt ist"});
                }});
            }};
    // Default Werte für die Default-PrimaryKeys der Default-Elemente
    public static final HashMap<Class<? extends MuseumsElement>, String> DEFAULT_PIMARYKEYS =
            new HashMap<Class<? extends MuseumsElement>, String>(){{
                put(Bild.class, DEFAULT_CSV_DATEN.get(Bild.class).get(1)[0]);
                put(Epoche.class, DEFAULT_CSV_DATEN.get(Epoche.class).get(1)[0]);
                put(Foerderer.class, DEFAULT_CSV_DATEN.get(Foerderer.class).get(1)[0]);
                put(Raum.class, DEFAULT_CSV_DATEN.get(Raum.class).get(1)[0]);
            }};


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
        MuseumsElementManager relevanterManager = waehleRelevantenManager(c);

        ArrayList<String[]> csvData = relevanterManager.parseToCSV();

        CSVWriter writer = new CSVWriter(path, true);
        writer.writeDataToFile(csvData, relevanterManager.getCSVHeader());
    }

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

    public static void ladeDefaultElemente(String path, boolean generateNew) throws Exception {
        Class<? extends MuseumsElement>[] defaultElementKlassen = new Class[]{Bild.class, Epoche.class, Foerderer.class, Raum.class};
        ladeDefaultElemente(path, defaultElementKlassen, generateNew);

    }

    public static void ladeDefaultElemente(String path, Class<? extends MuseumsElement>[] defaultElementKlassen, boolean generateNew) throws Exception {
        HashMap<Class<? extends MuseumsElement>, CSVReader> defaultDateien = new HashMap<>();
        for (Class<? extends MuseumsElement> typ : defaultElementKlassen) {
            // generiere einen neuen CSV-Reader für jede gelesene Datei
            String name = typ.getSimpleName();
            String dateiPfad = path.endsWith("/") ? path + name + ".csv" : path + "/" + name + ".csv";
            try {
                defaultDateien.put(typ, new CSVReader(dateiPfad));
            } catch (IllegalArgumentException e) {
                // schaue ob eine neue Datei generiert werden soll
                if (e.getMessage().equals("File does not exist") && generateNew) {
                    CSVWriter writer = new CSVWriter(dateiPfad, true);
                    String[] header = DEFAULT_CSV_DATEN.get(typ).get(0);
                    ArrayList<String[]> csvData = new ArrayList<String[]>() {{
                        add(DEFAULT_CSV_DATEN.get(typ).get(1));
                    }};

                    writer.writeDataToFile(csvData, header);
                } else {
                    throw e;
                }
            }
            MuseumsElementFactory.createElement(typ, dateiPfad);
        }
    }

    public static MuseumsElement getDefault(Class<? extends MuseumsElement> c) {
        return find(c, DEFAULT_PIMARYKEYS.get(c));
    }
}
