/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
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

import java.util.Arrays;
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
    public static MuseumsElement createElement(Class c, String[] csvData) throws Exception {
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
    public static MuseumsElement createElement(Class c, String dateiPfad, int linie) throws Exception {
        CSVReader reader = new CSVReader(dateiPfad);
        List<String[]> csvData = reader.readData(getNumberOfAttributes(c), CSVSeparationLevel.LEVEL1.toChar(), '#');
        System.out.println(Arrays.toString(csvData.get(linie)));
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
    public static ArrayList<MuseumsElement> createElement(Class c, String dateiPfad) throws Exception {
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

    public static Bild createBild(String[] csvData) throws Exception {
        checkCSVarghLength(csvData, getNumberOfAttributes(Bild.class));

        String bildNr = csvData[0];

        // Existiert ein Raum mit der RaumNR bereits im Museum?
        if (MuseumsManager.contains(Raum.class, bildNr)) {
            throw new Exception("Bild mit gleicher BildNr exisitert bereits");
        }

        String altText = csvData[1];
        String dateiName = csvData[2];
        String beschreibung = csvData[3];
        Bild bild = new Bild(bildNr, altText, dateiName, beschreibung);

        // Bild in Museum ablegen
        MuseumsManager.persist(Bild.class, bild);
        return bild;
    }

    public static Raum createRaum(String[] csvData) throws Exception {
        checkCSVarghLength(csvData, getNumberOfAttributes(Raum.class));

        String raumNr = csvData[0];

        // Existiert ein Raum mit der RaumNR bereits im Museum?
        if (MuseumsManager.contains(Raum.class, raumNr)) {
            throw new Exception("Raum mit gleicher RaumNr exisitert bereits");
        }

        String beschreibung = csvData[1];
        double ausstellungsflaeche = Integer.parseInt(csvData[2]);
        String ausstellungsthema = csvData[3];
        // Bilder finden
        ArrayList<Bild> bilder = new ArrayList<Bild>(); // TODO methode zum laden der Bilddaten schreiben
        for (String bildNr : csvData[4].split(String.valueOf(CSVSeparationLevel.LEVEL2))) {
            if (MuseumsManager.contains(Exponat.class, bildNr)) {
                bilder.add((Bild) MuseumsManager.find(Bild.class, bildNr));
            } else {
                System.out.println("Bild " + bildNr + "ignoriert");
            }
        }
        // Exponate finden
        ArrayList<Exponat> exponate = new ArrayList<>(); // TODO methode zum laden der Exponate schreiben
        for (String exponatNr : csvData[5].split(String.valueOf(CSVSeparationLevel.LEVEL2))) {
            if (MuseumsManager.contains(Exponat.class, exponatNr)) {
                bilder.add((Bild) MuseumsManager.find(Bild.class, exponatNr));
            }else {
                System.out.println("Exponat " + exponatNr + "ignoriert");
            }
        }

        Raum raum = new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder, exponate);

        // Raum in Museum ablegen
        MuseumsManager.persist(Raum.class, raum);
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
        }

        Foerderer foerderer = new Foerderer(foerdererNr, name, gebDatum, beschreibung, kontakte);
        return foerderer;
    }

    public static Admin createAdmin(String[] csvData) throws Exception {
        checkCSVarghLength(csvData, getNumberOfAttributes(Admin.class));

        String mitarbeiterNr = csvData[0];

        // Existiert ein Raum mit der RaumNR bereits im Museum?
        if (MuseumsManager.contains(Admin.class, mitarbeiterNr)) {
            throw new Exception("Bild mit gleicher BildNr exisitert bereits");
        }

        String name = csvData[1];
        String gebDatum = csvData[2];
        String beschreibung = csvData[3];

        //Kontakte laden
        ArrayList<Kontaktdaten> kontaktdaten = new ArrayList<>();
        for(String kontakt: csvData[4].split(CSVSeparationLevel.LEVEL2.toString())){
            Kontaktdaten neuerKontakt = createKontaktdaten(kontakt.split(CSVSeparationLevel.LEVEL3.toString()));
            kontaktdaten.add(neuerKontakt);
        }

        Admin admin = new Admin(mitarbeiterNr, name, gebDatum, beschreibung, kontaktdaten);

        // Bild in Museum ablegen
        MuseumsManager.persist(Admin.class, admin);
        return admin;
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
        for (String email : csvData[0].split(String.valueOf(CSVSeparationLevel.LEVEL4))) {
            emailAdressen.add(email);
        }
        // Telefonnummern
        ArrayList<String> teleNr = new ArrayList<>();
        for (String tele : csvData[1].split(String.valueOf(CSVSeparationLevel.LEVEL4))) {
            teleNr.add(tele);
        }
        // Anschriften
        ArrayList<Anschrift> anschriften = new ArrayList<>();
        // mehrere Anschriften sind mit | separiert, da diese Attribute der Anschriften wiederum mit , separiert sind
        for (String anschrift : csvData[2].split(String.valueOf(CSVSeparationLevel.LEVEL4))) {
            String[] anschriftAttribute = anschrift.split(String.valueOf(CSVSeparationLevel.LEVEL5));
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


    public static Epoche createEpoche(String[] csvData) throws Exception {
        checkCSVarghLength(csvData, getNumberOfAttributes(Epoche.class));

        String epochnenID = csvData[0];

        // Existiert eine Epoche mit der RaumNR bereits im Museum?
        if (MuseumsManager.contains(Raum.class, epochnenID)) {
            throw new Exception("Raum mit gleicher RaumNr exisitert bereits");
        }

        String epochenName = csvData[1];
        String stilrichtung = csvData[2];
        String zeitalter = csvData[3];
        String beschreibung = csvData[4];

        Epoche epoche = new Epoche(epochnenID, epochenName, stilrichtung, zeitalter, beschreibung);

        // Epoche dem Museum hinzufügen
        MuseumsManager.persist(Epoche.class, epoche);
        return epoche;
    }

    private static void checkCSVarghLength(String[] csvData, int expectedLength) {
        if (csvData.length != expectedLength) {
            String errorMessage = String.format("falsche Anzahl an Argumenten gegeben: erhalten %d - erwartet %d", csvData.length, expectedLength);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * diese Methode gibt die Anzahl der Attribute die gegebene Klasse c zusammen mit ihren Überkalssen besitzt
     *
     * @param c untersuchte Klasse
     * @return Anzahl der Attribute der Klasse
     */
    private static int getNumberOfAttributes(Class<?> c) {
        int counter = 0;
        while (c.getSuperclass() != null) {
            counter += c.getDeclaredFields().length;
            c = c.getSuperclass();
        }

        return counter;
    }
}
