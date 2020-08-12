package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
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
        } else if (c == Epoche.class) {
            return createEpoche(csvData);
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
        if (csvData.length != 4) {
            throw new IllegalArgumentException("falsche Anzahl an Argumenten gegeben: erhalten " + String.valueOf(csvData.length) + " - erwartet 4");
        }
        String bildNr = csvData[0];
        String altText = csvData[1];
        String dateiName = csvData[2];
        String beschreibung = csvData[3];
        Bild bild = new Bild(bildNr, altText, dateiName, beschreibung);
        return bild;
    }

    public static Raum createRaum(String[] csvData) {
        if (csvData.length != 5) {
            throw new IllegalArgumentException("falsche Anzahl an Argumenten gegeben: erhalten " + String.valueOf(csvData.length) + " - erwartet 5");
        }
        String raumNr = csvData[0];
        String beschreibung = csvData[1];
        double ausstellungsflaeche = Integer.parseInt(csvData[2]);
        String ausstellungsthema = csvData[3];
        ArrayList<Bild> bilder = new ArrayList<Bild>(); // TODO methode zum laden der Bilddaten schreiben

        Raum raum = new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder);
        return raum;
    }

    public static Foerderer createFoerderer(String[] csvData) throws ParseException {
        checkCSVarghLength(csvData, 5);

        String foerdererNr = csvData[0];
        String name = csvData[1];
        String gebDatum = csvData[2];
        String beschreibung = csvData[3];
        ArrayList<Kontaktdaten> kontakte = new ArrayList<>();
        for (String kontakt : csvData[4].split(",")) {
            // TODO entweder Kontaktdaten in eigener CSV-Datei oder eigenes Format für die Kontaktdaten in der CSV-Datei
            // TODO vielleicht könnten Listen-Elemente als JSON-Ähnliche Elemente gespeichert werten
        }

        Foerderer foerderer = new Foerderer(foerdererNr, name, gebDatum, beschreibung, kontakte);
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

    private static Kontaktdaten createKontaktdaten(String[] csvData) {
        checkCSVarghLength(csvData, 3);

        // E-Mail-Adressen
        ArrayList<String> emailAdressen = new ArrayList<>();
        for (String email : csvData[0].split(",")) {
            emailAdressen.add(email);
        }
        // Telefonnummern
        ArrayList<String> teleNr = new ArrayList<>();
        for (String tele : csvData[1].split(",")) {
            emailAdressen.add(tele);
        }
        // Anschriften
        ArrayList<Anschrift> anschriften = new ArrayList<>();
        // mehrere Anschriften sind mit | separiert, da diese Attribute der Anschriften wiederum mit , separiert sind
        // TODO vielleicht eigene Datei mit Attributen
        for (String anschrift : csvData[2].split("|")) {
            String[] anschriftAttribute = anschrift.split(",");
            //Postfachadresse
            if (anschriftAttribute.length == 4) {
                int postfachnummer = Integer.getInteger(anschriftAttribute[0]);
                int plz = Integer.getInteger(anschriftAttribute[1]);
                String stadt = anschriftAttribute[2];
                String land = anschriftAttribute[3];
                anschriften.add(new Postfachadresse(postfachnummer, plz, stadt, land));
                //Hausanschrift
            } else if (anschriftAttribute.length == 6) {
                String name = anschriftAttribute[0];
                String strasse = anschriftAttribute[1];
                int hausnummer = Integer.getInteger(anschriftAttribute[2]);
                int plz = Integer.getInteger(anschriftAttribute[3]);
                String stadt = anschriftAttribute[4];
                String land = anschriftAttribute[5];
                anschriften.add(new Hausanschrift(name, strasse, hausnummer, plz, stadt, land));
                //Firmenanschrift
            } else if (anschriftAttribute.length == 7) {
                String fimra = anschriftAttribute[0];
                String name = anschriftAttribute[1];
                String strasse = anschriftAttribute[2];
                int hausnummer = Integer.getInteger(anschriftAttribute[3]);
                int plz = Integer.getInteger(anschriftAttribute[4]);
                String stadt = anschriftAttribute[5];
                String land = anschriftAttribute[6];
                anschriften.add(new Firmenanschrift(fimra, name, strasse, hausnummer, plz, stadt, land));
            } else {
                throw new IllegalArgumentException("keine der Anschrifttypen matched die gegebenen Daten");
            }
        }
        Kontaktdaten kontakt = new Kontaktdaten(emailAdressen, teleNr, anschriften);
        return kontakt;
    }


    public static MuseumsElement createEpoche(String[] csvData) {
        checkCSVarghLength(csvData, Epoche.class.getDeclaredFields().length + 2);

        String epochnenID = csvData[0];
        String epoche = csvData[1];
        String stilrichtung = csvData[2];
        String zeitalter = csvData[3];
        String beschreibung = csvData[4];

        Epoche e = new Epoche(epochnenID, epoche, stilrichtung, zeitalter, beschreibung);
        return e;
    }

    private static boolean checkCSVarghLength(String[] csvData, int expectedLength) {
        if (csvData.length != expectedLength) { // TODO klären ob Exponate speichern wer sie fördert oder umgekehrt
            String errorMessage = String.format("falsche Anzahl an Argumenten gegeben: erhalten %d - erwartet %d", csvData.length, expectedLength);
            throw new IllegalArgumentException(errorMessage);
        }
        return true;
    }
}
