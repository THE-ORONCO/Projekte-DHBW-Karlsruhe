/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.*;
import Museum.MuseumsElement;
import Museum.Person.*;
import Museum.Raum.Raum;
import Museum.StringProcessor;
import de.dhbwka.swe.utils.util.AppLogger;
import de.dhbwka.swe.utils.util.CSVReader;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.IOException;
import java.security.KeyException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// TODO override boolean in createElement etc. Methoden um ein erstelltes Objekt mit gleicher pk zu ueberschreiben

public class MuseumsElementFactory { // DIFF eine einzelne universal-Factory anstatt verschiedene

    /**
     * diese Methode ermoeglicht das erzeugen eines Objekt welches von MuseumsElement erbt und ein Blatt im Vererbungsbaum ist
     *
     * @param c       Klasse des erzeugten Objekts
     * @param csvData String[] welches die Daten beschreibt die zur erzeugung des Objekts benoetigt werden
     * @return das erzeugte Objekt
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static MuseumsElement createElement(Class<? extends MuseumsElement> c, String[] csvData) throws Exception {
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
        } else if (c == Mitarbeiter.class) {
            return createMitarbeiter(csvData);
        } else {
            throw new IllegalArgumentException("Unbekannte Klasse!");
        }
    }

    /**
     * dise Methode ermoeglicht das laden einer spezifischen Zeile einer CSV-Datei
     *
     * @param c         Klasse der Elemente in einer CSV-Datei
     * @param dateiPfad Pfad zu der CSV-Datei
     * @param linie     Zeile der CSV-Datei die importiert werden soll (index startet bei 0)
     * @return das importierte Element
     * @throws IOException    wenn der CSV-Reader der SWE-Tools eine exception wirft
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static MuseumsElement createElement(Class<? extends MuseumsElement> c, String dateiPfad, int linie) throws Exception {
        CSVReader reader = new CSVReader(dateiPfad);
        List<String[]> csvData = reader.readData(getNumberOfAttributes(c), CSVReader.DEFAULT_DELIMITER, CSVReader.DEFAULT_COMMENT);
        return createElement(c, csvData.get(linie));
    }

    /**
     * Funktioniert wie die createElement-Methode mit längerer Signatur, nur das der Header einer CSV-Datei immer ignoriert wird
     *
     * @param c         Klasse der Elemente in einer CSV-Datei
     * @param dateiPfad Pfad zu der CSV-Datei
     * @return eine Liste der importierten Elemente
     * @throws IOException    wenn der CSV-Reader der SWE-Tools eine exception wirft
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static ArrayList<MuseumsElement> createElement(Class<? extends MuseumsElement> c, String dateiPfad) throws Exception {
        return createElement(c, dateiPfad, true);
    }

    /**
     * diese Methode ermoeglicht das laden einer kompletten CSV-Datei
     *
     * @param c          Klasse der Elemente in einer CSV-Datei
     * @param dateiPfad  Pfad zu der CSV-Datei
     * @param dropHeader wenn true wird der header einer CSV-File ignoriert
     * @return eine Liste der importierten Elemente
     * @throws IOException    wenn der CSV-Reader der SWE-Tools eine exception wirft
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static ArrayList<MuseumsElement> createElement(Class<? extends MuseumsElement> c, String dateiPfad, boolean dropHeader) throws Exception {
        CSVReader reader = new CSVReader(dateiPfad);
        List<String[]> csvData = reader.readData(getNumberOfAttributes(c), CSVReader.DEFAULT_DELIMITER, CSVReader.DEFAULT_COMMENT);

        ArrayList<MuseumsElement> geladeneElemente = new ArrayList<>();

        if (dropHeader) {
            csvData.remove(0);
        }

        for (String[] csvLine : csvData) {
            try {
                geladeneElemente.add(createElement(c, csvLine));
            } catch (Exception e) {
                AppLogger.getInstance().warning(c.getSimpleName() + " nicht erstellt aus den Daten: " + Arrays.toString(csvLine) +
                        " Es wurde eine Exception geworfen ->" + e.getMessage());
            }
        }

        return geladeneElemente;
    }

    /**
     * Diese Methode generiert ein Exponat Objekt aus csvDaten
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     */
    public static Exponat createExponat(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        String inventarNr = csvData[0];
        StringProcessor.validierePrimaryKey(Exponat.class, inventarNr);
        ueberpruefeExistenz(Exponat.class, inventarNr);

        String name = csvData[1];
        Date entstehungsDatum = new SimpleDateFormat("yyyy.MM.dd").parse(csvData[2]);
        ArrayList<String> urheber = new ArrayList<>(Arrays.asList(csvData[3].split(CSVSeparationLevel.LEVEL2.rSeparator())));
        double benoetigteAusstellungsflaeche = Double.parseDouble(csvData[4]);
        ArrayList<String> kategorien = new ArrayList<>(Arrays.asList(csvData[5].split(CSVSeparationLevel.LEVEL2.rSeparator())));
        Epoche epoche;
        if (MuseumsManager.contains(Epoche.class, csvData[6])) {
            epoche = (Epoche) MuseumsManager.find(Epoche.class, csvData[6]);
        } else {
            epoche = (Epoche) MuseumsManager.getDefault(Epoche.class);
            AppLogger.getInstance().info("Epoche mit der Epochennummer " + csvData[6] + " ignoriert -> Default Epoche geladen.");
        }
        String herkunftsort = csvData[7];
        Exponatwert exponatwert = createExponatwert(csvData[8].split(CSVSeparationLevel.LEVEL2.rSeparator()));
        Historie geschichtlicheH = createHistorie(new String[]{csvData[9]});
        Historie bearbeitungsH = createHistorie(new String[]{csvData[10]});
        Historie besitzH = createHistorie(new String[]{csvData[11]});
        Bild bild;
        if (MuseumsManager.contains(Bild.class, csvData[12])) {
            bild = (Bild) MuseumsManager.find(Bild.class, csvData[12]);
        } else {
            bild = (Bild) MuseumsManager.getDefault(Bild.class);
            AppLogger.getInstance().info("Bild mit der Bildnummer " + csvData[12] + " ignoriert -> Default Bild geladen.");
        }

        String beschreibung = csvData[13];

        Exponat exponat = new Exponat(inventarNr, name, entstehungsDatum, urheber, benoetigteAusstellungsflaeche,
                kategorien, epoche, herkunftsort, exponatwert, geschichtlicheH, bearbeitungsH, besitzH, bild, beschreibung);

        // Exponat im Museum ablegen
        MuseumsManager.persist(Exponat.class, exponat);

        return exponat;
    }

    /**
     * Diese Methode generiert ein ExponatWert Objekt aus csvDaten.
     * Diese Metoode wird nur fuer die Erstellung eines Exponat-Objekts verwendet.
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     */
    private static Exponatwert createExponatwert(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Exponatwert.class));

        double einkaufswert = Double.parseDouble(csvData[0]);
        double aktuellerSchaetzwert = Double.parseDouble(csvData[1]);
        double leihwert = Double.parseDouble(csvData[2]);

        Exponatwert exponatwert = new Exponatwert(einkaufswert, aktuellerSchaetzwert, leihwert);
        return exponatwert;
    }

    /**
     * Diese Methode generiert ein Historien Objekt aus csvDaten.
     * Diese Metoode wird nur fuer die Erstellung eines Exponat-Objekts verwendet.
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     */
    private static Historie createHistorie(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Historie.class));

        HashMap<Date, Ereignis> ereignisse = new HashMap<>();
        for (String ereignisCSV : StringProcessor.trimCSVData(
                csvData[0].split(CSVSeparationLevel.LEVEL2.rSeparator()))) {
            try {
                Ereignis ereignis = createEreignis(ereignisCSV.split(CSVSeparationLevel.LEVEL3.rSeparator()));
                ereignisse.put(ereignis.getDatum(), ereignis);
            } catch (Exception e) {
                AppLogger logger = AppLogger.getInstance();
                logger.info("Datensatz: " + Arrays.toString(csvData) + " konnte nicht in ein Ereignis umgewandelt werden -> ignoriert");
            }
        }

        Historie historie = new Historie(ereignisse);
        return historie;
    }

    /**
     * Diese Methode generiert ein Ereignis Objekt aus csvDaten.
     * Diese Metoode wird nur fuer die Erstellung eines Historie-Objekts verwendet.
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws ParseException wenn das Datum in den csvDaten im falschen Format vorliegt
     */
    private static Ereignis createEreignis(String[] csvData) throws ParseException {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Ereignis.class));

        Date datum = new SimpleDateFormat("yyyy.MM.dd").parse(csvData[0]); //TODO vlt variables Date-Time-Fromat reinbringen
        String beschreibung = csvData[1];

        Ereignis ereignis = new Ereignis(datum, beschreibung);

        return ereignis;
    }

    /**
     * Diese Methode generiert ein Bild Objekt aus csvDaten.
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     */
    public static Bild createBild(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Bild.class));

        String bildNr = csvData[0];
        StringProcessor.validierePrimaryKey(Bild.class, bildNr);
        ueberpruefeExistenz(Bild.class, bildNr);

        String altText = csvData[1];
        String dateiName = csvData[2];
        String beschreibung = csvData[3];
        Bild bild = new Bild(bildNr, altText, dateiName, beschreibung);

        // Bild in Museum ablegen
        MuseumsManager.persist(Bild.class, bild);
        return bild;
    }

    /**
     * Diese Methode generiert ein Raum Objekt aus csvDaten
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     */
    public static Raum createRaum(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Raum.class));

        String raumNr = csvData[0];
        StringProcessor.validierePrimaryKey(Raum.class, raumNr);
        ueberpruefeExistenz(Raum.class, raumNr);


        double ausstellungsflaeche = Double.parseDouble(csvData[1]);
        String ausstellungsthema = csvData[2];
        // Bilder finden
        ArrayList<Bild> bilder = new ArrayList<Bild>();
        for (String bildNr : StringProcessor
                .trimCSVData(csvData[3].split(String.valueOf(CSVSeparationLevel.LEVEL2)))) {
            if (MuseumsManager.contains(Bild.class, bildNr)) {
                bilder.add((Bild) MuseumsManager.find(Bild.class, bildNr));
            } else {
                System.out.println("Bild " + bildNr + " unbekannt -> ignoriert");
                AppLogger.getInstance().info("Bild " + bildNr + " unbekannt -> ignoriert");
            }
        }
        // Exponate finden
        ArrayList<Exponat> exponate = new ArrayList<>();
        for (String exponatNr : StringProcessor
                .trimCSVData(csvData[4].split(CSVSeparationLevel.LEVEL2.rSeparator()))) {
            if (MuseumsManager.contains(Exponat.class, exponatNr)) {
                exponate.add((Exponat) MuseumsManager.find(Exponat.class, exponatNr));
            } else {
                System.out.println("Exponat " + exponatNr + " unbekannt -> ignoriert");
                AppLogger.getInstance().info("Exponat  " + exponatNr + " unbekannt -> ignoriert");
            }
        }
        String beschreibung = csvData[5];

        Raum raum = new Raum(raumNr, ausstellungsflaeche, ausstellungsthema, bilder, exponate, beschreibung);

        // Raum in Museum ablegen
        MuseumsManager.persist(Raum.class, raum);
        return raum;
    }

    /**
     * Diese Methode generiert ein Foerderer Objekt aus csvDaten
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception      wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     * @throws ValueException wenn die Telefonnummern oder Emailadressen falsch fomatiert sind
     */
    public static Foerderer createFoerderer(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Foerderer.class));

        String foerdererNr = csvData[0];
        StringProcessor.validierePrimaryKey(Foerderer.class, foerdererNr);
        ueberpruefeExistenz(Foerderer.class, foerdererNr);

        String name = csvData[1];
        Date gebDatum = new SimpleDateFormat("yyyy.MM.dd").parse(csvData[2]);
        String beschreibung = csvData[3];
        // Kontaktdaten
        Kontaktdaten kontaktdaten = createKontaktdaten(csvData[4].split(CSVSeparationLevel.LEVEL2.rSeparator()));

        // Bild
        String bildNr = csvData[6];
        Bild bild;
        if (MuseumsManager.contains(Bild.class, bildNr)) {
            bild = (Bild) MuseumsManager.find(Bild.class, bildNr);
        } else {
            bild = (Bild) MuseumsManager.getDefault(Bild.class);
            AppLogger.getInstance().info("Kein Bild mit der Bildnummer " + bildNr + " gefunden -> default Bild geladen");
        }

        // gefoerderte Exponate
        Foerderer foerderer = new Foerderer(foerdererNr, name, gebDatum, beschreibung, kontaktdaten, bild);
        for (String exponatNr : StringProcessor
                .trimCSVData(csvData[5].split(CSVSeparationLevel.LEVEL2.rSeparator()))) {
            // suche das Exponat mit der gegebenen Exponatnummer im MuseumsManager und uebergebe es dem erstellten Foerderer
            if (!exponatNr.isEmpty()) {
                Exponat exponat = (Exponat) MuseumsManager.find(Exponat.class, exponatNr);
                if (exponat == null) {
                    AppLogger.getInstance().info("Kein Exponat mit der Exponatnummer " + exponatNr + " gefunden -> ignoriert");
                } else {
                    foerderer.foerdereWeiteresExponat(exponat);
                }
            }
        }


        // Foerderer im Museum ablegen
        MuseumsManager.persist(Person.class, foerderer);

        return foerderer;
    }

    /**
     * Diese Methode generiert dynamisch anhand CSV-Daten und der darin enthaltenen Mitarbeiternummer den jeweils
     * richtigen Mitarbeitertyp.
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception      wenn der Mitarbeiter bereits existiert
     * @throws ValueException wenn die Telefonnummern oder Emailadressen falsch fomatiert sind
     */
    public static Mitarbeiter createMitarbeiter(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Mitarbeiter.class));

        String mitarbeiterNr = csvData[0];
        if (mitarbeiterNr.startsWith(String.valueOf(StringProcessor.waehleKeyStartCharakter(Admin.class)))) {
            return createAdmin(csvData);
        }
        if (mitarbeiterNr.startsWith(String.valueOf(StringProcessor.waehleKeyStartCharakter(User.class)))) {
            return createUser(csvData);
        }
        if (mitarbeiterNr.startsWith(String.valueOf(StringProcessor.waehleKeyStartCharakter(HR.class)))) {
            return createHR(csvData);
        }
        return null;
    }

    /**
     * Diese Methode generiert ein Mitarbeiter Objekt aus csvDaten welches als temporärer Datenspeicher verwendet wird,
     * um daraus spezifischere Mitarbeiter zu generieren
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     */
    private static Mitarbeiter createMitarbeiterTemplate(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Admin.class));

        String mitarbeiterNr = csvData[0];

        String name = csvData[1];
        Date gebDatum = new SimpleDateFormat("yyyy.MM.dd").parse(csvData[2]);
        String beschreibung = csvData[3];

        //Kontakte laden
        Kontaktdaten kontaktdaten = createKontaktdaten(csvData[4].split(CSVSeparationLevel.LEVEL2.rSeparator()));

        String bildNr = csvData[5];
        Bild bild;
        if (MuseumsManager.contains(Bild.class, bildNr)) {
            bild = (Bild) MuseumsManager.find(Bild.class, bildNr);
        } else {
            bild = (Bild) MuseumsManager.getDefault(Bild.class);
            AppLogger.getInstance().info("Kein Bild mit der Bildnummer " + bildNr + " gefunden -> default Bild geladen");
        }

        Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterNr, name, gebDatum, beschreibung, kontaktdaten, bild);

        return mitarbeiter;
    }

    /**
     * Diese Methode generiert ein Admin Objekt aus csvDaten
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     */
    public static Admin createAdmin(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        Mitarbeiter mT = createMitarbeiterTemplate(csvData);
        StringProcessor.validierePrimaryKey(Admin.class, mT.getPrimaryKey());
        Admin admin = new Admin(mT.getPrimaryKey(), mT.getName(), mT.getGebDatum(), mT.getBeschreibung(), mT.getKontakt(), mT.getBild());

        ueberpruefeExistenz(Admin.class, admin.getPrimaryKey());

        // Mitarbeiter in Museum ablegen
        MuseumsManager.persist(Admin.class, admin);
        return admin;
    }

    /**
     * Diese Methode generiert ein User Objekt aus csvDaten
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     */
    public static User createUser(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        Mitarbeiter mT = createMitarbeiterTemplate(csvData);
        StringProcessor.validierePrimaryKey(User.class, mT.getPrimaryKey());
        User user = new User(mT.getPrimaryKey(), mT.getName(), mT.getGebDatum(), mT.getBeschreibung(), mT.getKontakt(), mT.getBild());

        ueberpruefeExistenz(User.class, user.getPrimaryKey());

        // Mitarbeiter in Museum ablegen
        MuseumsManager.persist(User.class, user);
        return user;
    }

    /**
     * Diese Methode generiert ein HR Objekt aus csvDaten
     *
     * @param csvData die csvDaten aus denen das Objekt generiert werden soll
     * @return das generierte Objekt
     * @throws Exception wenn ein Objekt mit gleichem PrimaryKey bereits im MuseumsManager vorhanden ist
     */
    public static HR createHR(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        Mitarbeiter mT = createMitarbeiterTemplate(csvData);
        StringProcessor.validierePrimaryKey(HR.class, mT.getPrimaryKey());
        HR hr = new HR(mT.getPrimaryKey(), mT.getName(), mT.getGebDatum(), mT.getBeschreibung(), mT.getKontakt(), mT.getBild());

        ueberpruefeExistenz(HR.class, hr.getPrimaryKey());

        // Mitarbeiter in Museum ablegen
        MuseumsManager.persist(HR.class, hr);
        return hr;
    }

    /**
     * Diese Methode generiert ein Kontaktdaten Objekt aus csvDaten
     * Diese Methode wird nur von Foerderer-, und Mitarbeiter-Objekten verwendet
     *
     * @param csvData CSV-Daten aus denen ein Kontakt-Objekt generiert werden soll
     * @return das generierte Kontakt-Objekt
     */
    public static Kontaktdaten createKontaktdaten(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, 3);

        // E-Mail-Adressen
        ArrayList<String> emailAdressen = new ArrayList<>(
                Arrays.asList(StringProcessor.trimCSVData(
                        csvData[0].split(CSVSeparationLevel.LEVEL3.rSeparator()))));
        // Telefonnummern
        ArrayList<String> teleNr = new ArrayList<>(
                Arrays.asList(StringProcessor.trimCSVData(
                        csvData[1].split(CSVSeparationLevel.LEVEL3.rSeparator()))));
        // Anschriften
        ArrayList<Anschrift> anschriften = new ArrayList<>();
        // mehrere Anschriften sind mit | separiert, da diese Attribute der Anschriften wiederum mit , separiert sind

        for (String anschrift : StringProcessor
                .trimCSVData(csvData[2].split(CSVSeparationLevel.LEVEL3.rSeparator()))) {
            String[] anschriftAttribute = StringProcessor.trimCSVData(anschrift.split(CSVSeparationLevel.LEVEL4.rSeparator()));
            Anschrift generierteAnschrift = createAnschrift(anschriftAttribute);
            if (generierteAnschrift == null) {
                AppLogger.getInstance().info("CSV-Daten: " + Arrays.toString(csvData) + "konnten nicht in eine Anschrifft umgewandelt werden -> ignoriert");
            } else {
                anschriften.add(generierteAnschrift);
            }
        }
        Kontaktdaten kontakt = new Kontaktdaten(emailAdressen, teleNr, anschriften);
        return kontakt;
    }

    /**
     * Diese Methode generiert ein Anschrift Objekt aus csvDaten
     *
     * @param csvData CSV-Daten aus denen ein Kontakt-Objekt generiert werden soll
     * @return das generierte Kontakt-Objekt
     */
    private static Anschrift createAnschrift(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

        if (csvData.length == 4) {
            //Postfachadresse
            int postfachnummer = Integer.parseInt(csvData[0]);
            int plz = Integer.parseInt(csvData[1]);
            String stadt = csvData[2];
            String land = csvData[3];
            return new Postfachadresse(postfachnummer, plz, stadt, land);

        } else if (csvData.length == 6) {
            //Hausanschrift
            String name = csvData[0];
            String strasse = csvData[1];
            int hausnummer = Integer.parseInt(csvData[2]);
            int plz = Integer.parseInt(csvData[3]);
            String stadt = csvData[4];
            String land = csvData[5];
            return new Hausanschrift(name, strasse, hausnummer, plz, stadt, land);

        } else if (csvData.length == 7) {
            //Firmenanschrift
            String fimra = csvData[0];
            String name = csvData[1];
            String strasse = csvData[2];
            int hausnummer = Integer.parseInt(csvData[3]);
            int plz = Integer.parseInt(csvData[4]);
            String stadt = csvData[5];
            String land = csvData[6];
            return new Firmenanschrift(fimra, name, strasse, hausnummer, plz, stadt, land);
        } else {
            return null;
        }
    }

    /**
     * Diese Methode generiert ein Epochen Objekt aus csvDaten
     *
     * @param csvData CSV-Daten aus denen ein Kontakt-Objekt generiert werden soll
     * @return das generierte Kontakt-Objekt
     */
    public static Epoche createEpoche(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Epoche.class));

        String epochnenID = csvData[0];
        StringProcessor.validierePrimaryKey(Epoche.class, epochnenID);
        ueberpruefeExistenz(Epoche.class, epochnenID);

        // Existiert eine Epoche mit der EpochenNr bereits im Museum?
        if (MuseumsManager.contains(Epoche.class, epochnenID)) {
            throw new Exception("Raum mit gleicher RaumNr exisitert bereits");
        }

        String epochenName = csvData[1];
        String stilrichtung = csvData[2];
        String zeitalter = csvData[3];
        String beschreibung = csvData[4];

        Epoche epoche = new Epoche(epochnenID, epochenName, stilrichtung, zeitalter, beschreibung);

        // Epoche dem Museum hinzufuegen
        MuseumsManager.persist(Epoche.class, epoche);
        return epoche;
    }

    /**
     * Diese methode ueberprueft ob die Länge der gegebenen CSV-Daten der gegebenen Länge entspricht
     *
     * @param csvData        ueberpruefte CSV-Daten
     * @param expectedLength gewuenschte Länge
     */
    private static void checkCSVarghLength(String[] csvData, int expectedLength) {
        if (csvData.length != expectedLength) {
            String errorMessage = String.format("falsche Anzahl an Argumenten gegeben: erhalten %d - erwartet %d", csvData.length, expectedLength);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * diese Methode gibt die Anzahl der Attribute die gegebene Klasse c zusammen mit ihren ueberkalssen besitzt
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

    /**
     * Dies Metode ueberprueft ob ein Objekt mit gegebenem primaryKey bereits im Museum existiert.
     *
     * @param c          Klasse des Objekts
     * @param primaryKey gesuchter Primary key
     */
    private static void ueberpruefeExistenz(Class<? extends MuseumsElement> c, String primaryKey) throws KeyException {
        if (MuseumsManager.contains(c, primaryKey)) {
            throw new KeyException(c.getSimpleName() + " mit gleichem PrimaryKey exisitert bereits");
        }
    }

}
