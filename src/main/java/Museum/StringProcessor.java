package Museum;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.ObjectManagement.CSVSeparationLevel;
import Museum.Person.Admin;
import Museum.Person.Foerderer;
import Museum.Person.HR;
import Museum.Person.User;
import Museum.Raum.Raum;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.Random;

public class StringProcessor {

    /**
     * entferne Whitespaces am Anfang und Ende jedes Element der csvDaten
     *
     * @param csvData zu trimmende CSV-Daten
     * @return die getrimmten CSV-Daten
     */
    public static String[] trimCSVData(String[] csvData) {
        if (csvData.length == 1) {
            if (csvData[0].trim().isEmpty()) {
                return new String[0]; // leere Arrays werden ignoriert
            }
        }

        for (int i = 0; i < csvData.length; i++) {
            csvData[i] = csvData[i].trim();
        }
        return csvData;
    }

    /**
     * Validiere den Key fuer den gegebenen Objekttyp
     *
     * @param c          Objekttyp
     * @param primaryKey zu validierender primaryKey
     */
    public static void validierePrimaryKey(Class<? extends MuseumsElement> c, String primaryKey) {
        char startCharacter = waehleKeyStartCharakter(c);
        if (primaryKey.matches("[a-zA-Z0-9]*") && primaryKey.startsWith(String.valueOf(startCharacter))) {
        } else {
            throw new ValueException("PrimaryKey \"" + primaryKey + "\" passt nicht zur Klasse " + c.getName());
        }

    }

    /**
     * generiere einen zufaelligen alphanumerischen String
     *
     * @param laenge laenge des zu generierenden Strings
     * @return einen zufaelligen alphanumerischen String der gegebenen Laenge
     */
    private static String generiereRandomAlphaNumString(int laenge) {
        String alphaNumChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        StringBuilder sb = new StringBuilder(laenge);
        for (int i = 0; i < laenge; i++)
            sb.append(alphaNumChars.charAt(random.nextInt(alphaNumChars.length())));
        return sb.toString();
    }

    /**
     * generiere einen zufaelligen alphanumerischen String der Laenge 1 <= l <= 23
     *
     * @return einen zufaelligen alphanumerischen String der Laenge 1 <= l <= 23
     */
    private static String generiereRandomAlphaNumString() {
        Random random = new Random();
        int high = 23;
        int low = 1;
        int len = random.nextInt(high - low) + low;

        return generiereRandomAlphaNumString(len);
    }

    /**
     * Waehle den dem MuseumsElementTyp zugewiesenen Startbuchstaben
     *
     * @param c typ des MuseumsElements
     * @return den zugewiesenen Buchstaben
     * @throws IllegalArgumentException wenn die Klasse unbekannt ist
     */
    public static char waehleKeyStartCharakter(Class<? extends MuseumsElement> c) {
        char startingCharacter;
        if (c == Admin.class) {
            startingCharacter = 'a';
        } else if (c == User.class) {
            startingCharacter = 'u';
        } else if (c == HR.class) {
            startingCharacter = 'h';
        } else if (c == Foerderer.class) {
            startingCharacter = 'f';
        } else if (c == Raum.class) {
            startingCharacter = 'r';
        } else if (c == Exponat.class) {
            startingCharacter = 'x';
        } else if (c == Epoche.class) {
            startingCharacter = 'e';
        } else if (c == Bild.class) {
            startingCharacter = 'b';
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
        return startingCharacter;
    }

    /**
     * Diese Methode validiert einen String der als einzelnes Element abgeleg werden soll (also keine Liste oder
     * Aufzählung im CSV-Format darstellt).
     *
     * @param csvData der zu validierende String
     * @return ob der String problemlos in einer CSV-Datei abgelegt werden kann ohne dass er beim Einlesenmissverstanden werden kann
     */
    public static boolean validiereCSVDataString(String csvData) {
        String[] illegalCharacters = new String[]{
                CSVSeparationLevel.LEVEL1.wSeparator(),
                CSVSeparationLevel.LEVEL2.wSeparator(),
                CSVSeparationLevel.LEVEL3.wSeparator(),
                CSVSeparationLevel.LEVEL4.wSeparator(),
                CSVSeparationLevel.LEVEL5.wSeparator(),
                "#"};
        for (String c : illegalCharacters) {
            if (csvData.contains(c) || csvData.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * generiere einen valieden primary Key
     *
     * @param c Objekttyp
     * @return den generierten PrimaryKey
     */
    public static String generierePrimaryKey(Class<? extends MuseumsElement> c) {
        return waehleKeyStartCharakter(c) + StringProcessor.generiereRandomAlphaNumString();
    }

    /**
     * generiere einen valieden PrimaryKey der gegebenen Laenge fuer den gegebenen Objekttyp
     *
     * @param c      Objekttyp
     * @param laenge laenge des generierten PrimaryKey
     * @return den generierten PrimaryKey der gegebnen laenge
     */
    public static String generierePrimaryKey(Class<? extends MuseumsElement> c, int laenge) {
        return waehleKeyStartCharakter(c) + StringProcessor.generiereRandomAlphaNumString(laenge - 1);
    }
}
