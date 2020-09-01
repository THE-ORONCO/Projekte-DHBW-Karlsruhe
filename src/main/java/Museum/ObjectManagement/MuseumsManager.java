/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.MuseumsElement;
import Museum.Person.Foerderer;
import Museum.Person.Mitarbeiter;
import Museum.Person.Person;
import Museum.Raum.Raum;
import Museum.StringProcessor;
import app.SWEMuseumsVerwaltung;
import de.dhbwka.swe.utils.util.AppLogger;
import de.dhbwka.swe.utils.util.CSVReader;
import de.dhbwka.swe.utils.util.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MuseumsManager {

    private static final HashMap<Class<? extends MuseumsElement>, ArrayList<MuseumsElement>> DEFAULT_ELEMENTE = new HashMap<>();

    // MuseumsElementManager die die verschiedenen Objekte des Museums speichern
    private final static MuseumsElementManager mitarbeiterM = new MuseumsElementManager(); // TODO das hier koennte man aufteilen, warum haben wir das nicht?
    private final static MuseumsElementManager foerdererM = new MuseumsElementManager(); // DIFF PersonenManger aufgesplittet
    private final static MuseumsElementManager raumM = new MuseumsElementManager();
    private final static MuseumsElementManager exponatM = new MuseumsElementManager(); // TODO vielleicht eine Methode zum tracken der Änderungen am Objekt einbauen
    private final static MuseumsElementManager epochenM = new MuseumsElementManager(); // DIFF EpochenManager
    private final static MuseumsElementManager bildM = new MuseumsElementManager(); // DIFF BildManager
    //TODO vielleicht eine globale Liste fuer alle Kategorien wenn noch Zeit ist

    /*public static MuseumsElementManager getPersonenManager() {
        return personenM;
    }*/

    public static MuseumsElementManager getMitarbeiterM() {
        return mitarbeiterM;
    }

    public static MuseumsElementManager getFoerdererM() {
        return foerdererM;
    }

    public static MuseumsElementManager getRaumM() {
        return raumM;
    }

    public static MuseumsElementManager getExponatM() {
        return exponatM;
    }

    public static MuseumsElementManager getEpochenM() {
        return epochenM;
    }

    public static MuseumsElementManager getBildM() {
        return bildM;
    }

    /**
     * Diese Methode testet ob ein MuseumsElement mit einem bestimmte Primarykey im Museum vorhanden ist
     *
     * @param c          Klasse des MuseumsElements
     * @param primaryKey Key des gesuchtes Museumselement
     * @return ob ein Element mit dem Primarykey vorhanden ist
     */
    public static boolean contains(Class<? extends MuseumsElement> c, String primaryKey) {
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
    public static boolean contains(Class<? extends MuseumsElement> c, MuseumsElement element) {
        return contains(c, element.getPrimaryKey());
    }

    /**
     * Diese Methode speichert ein Objekt in den jeweils zuständigen Manager
     *
     * @param c       Klasse des zu speichernden Objekts
     * @param element das zu speichernde Objekt
     * @throws Exception wenn das Objekt oder ein Objekt mit gleichem PrimaryKey bereits vorhanden ist
     */
    public static void persist(Class<? extends MuseumsElement> c, MuseumsElement element) throws Exception {
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
    public static MuseumsElement find(Class<? extends MuseumsElement> c, String primaryKey) {
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
    public static boolean remove(Class<? extends MuseumsElement> c, String primaryKey) {
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
    public static void importieren(Class<? extends MuseumsElement> c, String dateiPfad) throws Exception {
        MuseumsElementFactory.createElement(c, dateiPfad);
    }


    public static void exportieren(Class<? extends MuseumsElement> c, String path, boolean ueberSchreiben) throws Exception {
        exportieren(c, path, c.getSimpleName() + ".csv", ueberSchreiben);
    }

    // Standartmethode exportiert im CSV-Format
    public static void exportieren(Class<? extends MuseumsElement> c, String path, String dateiName, boolean ueberSchreiben) throws Exception {
        MuseumsElementManager alleElementeDerKlasse = MuseumsManager.waehleRelevantenManager(c);
        exportieren(c, path, dateiName, ueberSchreiben, alleElementeDerKlasse.getMuseumsElementeAsList());
    }

    public static void exportieren(Class<? extends MuseumsElement> c, String path, String dateiName, boolean ueberSchreiben, ArrayList<MuseumsElement> exportElemente) throws Exception{
        //TODO exportieren ausgiebig testen!
        String dateiPfad = path + File.separator + dateiName;
        File exportDatei = new File(dateiPfad);
        // ueberpruefen ob die Datei existiert
        if (exportDatei.exists()) {
            // ueberpruefen ob die Datei ueberschrieben werden soll
            if (ueberSchreiben) {
                // ueberpruefen ob die Datei geloescht und neu geschrieben werden kann
                if (exportDatei.delete()) {
                    AppLogger.getInstance().info("Datei " + exportDatei.getAbsolutePath() + " wurde ueberschrieben.");
                } else {
                    String errorNachricht = "Datei " + exportDatei.getAbsolutePath() + " konnte nicht ueberschrieben werden.";
                    AppLogger.getInstance().error(errorNachricht);
                    throw new IOException(errorNachricht);
                }
            } else {
                String errorMessage = "Datei existiert bereits.";
                AppLogger.getInstance().error(errorMessage);
                throw new IOException(errorMessage);
            }
        }

        // schreibe neue Datei
        if (exportDatei.createNewFile()) {
            MuseumsElementManager relevanterManager = waehleRelevantenManager(c);
            String[] header;

            if (Person.class.isAssignableFrom(c)) {
                header = Mitarbeiter.getCSVHeader();
            } else if (c == Foerderer.class) {
                header = Foerderer.getCSVHeader();
            } else if (c == Raum.class) {
                header = Raum.getCSVHeader();
            } else if (c == Exponat.class) {
                header = Exponat.getCSVHeader();
            } else if (c == Epoche.class) {
                header = Epoche.getCSVHeader();
            } else if (c == Bild.class) {
                header = Bild.getCSVHeader();
            } else throw new IllegalArgumentException("Unbekante Klasse: " + c);

            ArrayList<String[]> csvData = new ArrayList<>();
            for (MuseumsElement element : exportElemente){
                csvData.add(element.parsToCSV());
            }

            if(csvData.size() > 0){
                CSVWriter writer = new CSVWriter(exportDatei.getAbsolutePath(), true);
                System.out.println(csvData);
                writer.writeDataToFile(csvData, header);
            }else {
                AppLogger.getInstance().info("keine Daten zu exportieren!");
            }


        } else {
            String errorMessage = "Datei konnte nicht geschrieben werden.";
            AppLogger.getInstance().error(errorMessage);
            throw new IOException(errorMessage);
        }
    }

    /**
     * Diese Methode wählt den MuseumsElementManager der fuer die gegebene Objektklasse zuständig ist.
     *
     * @param c Klasse die verwaltet werden soll
     * @return den fuer diese Klasse vorgesehenen Manager
     */
    private static MuseumsElementManager waehleRelevantenManager(Class<? extends MuseumsElement> c) {
        if (Mitarbeiter.class.isAssignableFrom(c)) {
            return mitarbeiterM;
        } else if (c == Foerderer.class) {
            return foerdererM;
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
     * Diese Methode generiert einen neuen noch unbenutzen PrimaryKey fuer die gegebene Klasse.
     *
     * @param c Klasse fuer die ein neuer PrimaryKey generiert werden soll
     * @return einen unbenutzen PrimaryKey
     */
    public static String generiereUnbenutzenSchluessel(Class<? extends MuseumsElement> c) {
        char startingCharacter = StringProcessor.waehleKeyStartCharakter(c);

        MuseumsElementManager relevantenManager = waehleRelevantenManager(c);

        String key = "";

        do {
            key = StringProcessor.generierePrimaryKey(c);
        } while (relevantenManager.contains(key));
        return key;
    }

    /**
     * Diese methode loescht alle Einträge im Museumsmanager. !!!Achtung!!! dies ist eher um das System zu testen
     */
    public static void clearAlles() {
        mitarbeiterM.clear();
        foerdererM.clear();
        raumM.clear();
        exponatM.clear();
        epochenM.clear();
        bildM.clear();
    }

    /**
     * Diese Methode läd die default Elemente fuer Bild, Epoche, Foerderer, Raum
     *
     * @param path Pfad zum default-Ordner in welchem die Dateien mit Default-Daten liegen oder liegen sollen
     * @throws Exception wenn beim Lese/Schreib-Prozess schiefgeht
     */
    public static void ladeDefaultElemente(String path) throws Exception {
        Class<? extends MuseumsElement>[] defaultElementKlassen = new Class[]{Bild.class, Epoche.class, Foerderer.class, Raum.class};
        ladeDefaultElemente(path, defaultElementKlassen);
    }

    /**
     * Diese Methode läd die default Elemente fuer eine gegebene Liste an Klassen aus Dateien beziehungsweise läd
     * Default-Werte aus gegebenen Dateien.
     * Die geladenen Werte werden in der HashMap DEFAULT_ELELMENTE mit den Klassen als Key abgelegt.
     *
     * @param path                  Pfad zum default-Ordner in welchem die Dateien mit Default-Daten liegen oder liegen sollen
     * @param defaultElementKlassen Klassen fuer die die Default-Elemente geladen werden sollen
     * @throws Exception wenn beim Lese/Schreib-Prozess schiefgeht
     */
    public static void ladeDefaultElemente(String path, Class<? extends MuseumsElement>[] defaultElementKlassen) throws Exception {
        ArrayList<MuseumsElement> defaultElemente = new ArrayList<>();

        for (Class<? extends MuseumsElement> typ : defaultElementKlassen) {
            // generiere einen neuen CSV-Reader fuer jede gelesene Datei
            String name = typ.getSimpleName();
            String dateiName = name + ".csv";
            String dateiPfad = path.endsWith("/") ? path + dateiName : path + "/" + dateiName;
            File defaultDatei = new File(dateiPfad);
            if (defaultDatei.exists()) {
                defaultElemente = MuseumsElementFactory.createElement(typ, dateiPfad);


            } else {
                // wenn der Dateipfad nicht valide ist, dann werden die Default-Daten aus Jar-internen Dateien geladen
                // Interne Jar-Files müssen mit BufferedReader und co gelesen werden
                InputStream in = SWEMuseumsVerwaltung.class.getResourceAsStream("/default/" + dateiName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                ArrayList<String[]> defaulDaten = new ArrayList<>();
                while (reader.ready()) {
                    String line = reader.readLine();
                    String[] csvData = line.split(String.valueOf(CSVReader.DEFAULT_DELIMITER));
                    defaulDaten.add(csvData);
                }

                for (String[] defaultElementDaten : defaulDaten) {
                    if (defaultElementDaten[0].startsWith("#")) {
                        defaultElemente.add(MuseumsElementFactory.createElement(typ, defaultElementDaten));
                    }
                }

                AppLogger.getInstance().warning("Keine Default Datei unter " + dateiPfad + " gefunden." +
                        "Default-Daten fuer " + name + " aus interner default Datei geladen.");
            }

            MuseumsManager.DEFAULT_ELEMENTE.put(typ, defaultElemente);
        }
    }

    /**
     * Diese Methode gibt das default Element fuer die gegebene Klasse zurueck.
     *
     * @param c Klasse fuer das das default Element gesucht wird
     * @return Default-MuseumsElement fuer die gegebene Klasse
     */
    public static MuseumsElement getDefault(Class<? extends MuseumsElement> c) {
        return DEFAULT_ELEMENTE.get(c).get(0);
    }
}
