package Museum.Exponat;

import Museum.Bild.Bild;
import Museum.MuseumsElement;
import Museum.Person.*;
import Museum.Raum.Raum;
import de.dhbwka.swe.utils.util.CSVReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MuseumsElementFactory { // DIFF eine einzelne universal-Factory anstatt verschiedene
    //TODO Factory implementieren

    /**
     * diese Methode ermöglicht das erzeugen eines Objekt welches von MuseumsElement erbt und ein Blatt im Vererbungsbaum ist
     *
     * @param c       Klasse des erzeugten Objekts
     * @param csvData String[] welches die Daten beschreibt die zur erzeugung des Objekts benötigt werden
     * @return das erzeugte Objekt
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static MuseumsElement createElement(Class c, String[] csvData) throws ParseException {
        if (c == Exponat.class) {
            return createExponat(csvData);
        } else if (c == Bild.class) {
            return createBild(csvData);
        } else if (c == Raum.class) {
            return createRaum(csvData);
        } else if (c == Foerderer.class) {
            return createFoerderer(csvData);
        } else if (c == Admin.class) {
            return createAdmin(csvData);
        } else if (c == User.class) {
            return createUser(csvData);
        } else if (c == HR.class) {
            return createHR(csvData);
        } else {
            throw new IllegalArgumentException("Unbekannte Klasse!");
        }
    }

    /**
     * dise Methode ermöglicht das laden einer spezifischen Zeile einer CSV-Datei
     *
     * @param c         Klasse der Elemente in einer CSV-Datei
     * @param dateiPfad Pfad zu der CSV-Datei
     * @param linie     Zeile der CSV-Datei die importiert werden soll (index startet bei 0)
     * @return das importierte Element
     * @throws IOException    wenn der CSV-Reader der SWE-Tools eine exception wirft
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static MuseumsElement createElement(Class c, String dateiPfad, int linie) throws IOException, ParseException {
        CSVReader reader = new CSVReader(dateiPfad);
        List<String[]> csvData = reader.readData();
        return createElement(c, csvData.get(linie));
    }

    /**
     * diese Methode ermöglicht das laden einer kompletten CSV-Datei
     *
     * @param c         Klasse der Elemente in einer CSV-Datei
     * @param dateiPfad Pfad zu der CSV-Datei
     * @return eine Liste der importierten Elemente
     * @throws IOException    wenn der CSV-Reader der SWE-Tools eine exception wirft
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static ArrayList<MuseumsElement> createElement(Class c, String dateiPfad) throws IOException, ParseException {
        CSVReader reader = new CSVReader(dateiPfad);
        List<String[]> csvData = reader.readData();

        ArrayList<MuseumsElement> geladeneElemente = new ArrayList<>();
        for (String[] csvLine : csvData) {
            geladeneElemente.add(createElement(c, csvLine));
        }

        return geladeneElemente;
    }

    public static Exponat createExponat(String[] csvData) {
        return null;
    }

    public static Bild createBild(String[] csvData) {
        if (csvData.length != 3) {
            throw new IllegalArgumentException("falsche Anzahl an Argumenten gegeben: erhalten " + String.valueOf(csvData.length) + " - erwartet 3");
        }
        String altText = csvData[0];
        String dateiName = csvData[1];
        String beschreibung = csvData[2];
        Bild bild = new Bild(altText, dateiName, beschreibung);
        return bild;
    }

    public static Raum createRaum(String[] csvData) {
        if (csvData.length != 5) {
            throw new IllegalArgumentException("falsche Anzahl an Argumenten gegeben: erhalten " + String.valueOf(csvData.length) + " - erwartet 5");
        }
        int raumNr = Integer.parseInt(csvData[0]);
        String beschreibung = csvData[1];
        double ausstellungsflaeche = Integer.parseInt(csvData[2]);
        String ausstellungsthema = csvData[3];
        ArrayList<Bild> bilder = new ArrayList<Bild>(); // TODO methode zum laden der Bilddaten schreiben

        Raum raum = new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder);
        return raum;
    }

    public static Foerderer createFoerderer(String[] csvData) throws ParseException {
        if (csvData.length != 4) { // TODO klären ob Exponate speichern wer sie fördert oder umgekehrt
            throw new IllegalArgumentException("falsche Anzahl an Argumenten gegeben: erhalten " + String.valueOf(csvData.length) + " - erwartet 4");
        }
        String name = csvData[0];
        String gebDatum = csvData[1];
        String beschreibung = csvData[2];
        ArrayList<Kontaktdaten> kontakte = new ArrayList<>();
        for (String kontakt : csvData[3].split(",")) {
            // TODO entweder Kontaktdaten in eigener CSV-Datei oder eigenes Format für die Kontaktdaten in der CSV-Datei
            // TODO vielleicht könnten Listen-Elemente als JSON-Ähnliche Elemente gespeichert werten
        }

        Foerderer foerderer = new Foerderer(name, gebDatum, beschreibung, kontakte);
        return foerderer;
    }

    public static Admin createAdmin(String[] csvData) {
        return null;
    }

    public static User createUser(String[] csvData) {
        return null;
    }

    public static HR createHR(String[] csvData) {
        return null;
    }

    public static Kontaktdaten createKontaktdaten(String[] csvData) {
        return null;
    }
}
