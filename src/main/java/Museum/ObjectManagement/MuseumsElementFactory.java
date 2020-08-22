/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.*;
import Museum.MuseumsElement;
import Museum.Person.*;
import Museum.Raum.Raum;
import Museum.StringProcessor;
import de.dhbwka.swe.utils.util.CSVReader;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.IOException;
import java.security.KeyException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MuseumsElementFactory { // DIFF eine einzelne universal-Factory anstatt verschiedene

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
        if (linie == 0) {
            throw new ValueException("die 0e Reihe beinhaltet keine Objektdaten.");
        }
        CSVReader reader = new CSVReader(dateiPfad);
        List<String[]> csvData = reader.readData(getNumberOfAttributes(c), CSVSeparationLevel.LEVEL1.toChar(), '#');
        System.out.println(Arrays.toString(csvData.get(linie)));
        return createElement(c, csvData.get(linie));
    }

    /**
     * diese Methode ermöglicht das laden einer kompletten CSV-Datei
     *
     * @param c          Klasse der Elemente in einer CSV-Datei
     * @param dateiPfad  Pfad zu der CSV-Datei
     * @param dropHeader wenn true wird der header einer CSV-File ignoriert
     * @return eine Liste der importierten Elemente
     * @throws IOException    wenn der CSV-Reader der SWE-Tools eine exception wirft
     * @throws ParseException wenn Telefonnummer oder Email-Adresse ein falsches Format haben
     */
    public static ArrayList<MuseumsElement> createElement(Class c, String dateiPfad, boolean dropHeader) throws Exception {
        CSVReader reader = new CSVReader(dateiPfad);
        List<String[]> csvData = reader.readData();

        ArrayList<MuseumsElement> geladeneElemente = new ArrayList<>();

        if (dropHeader) {
            csvData.remove(0);
        }

        for (String[] csvLine : csvData) {
            try {
                geladeneElemente.add(createElement(c, csvLine));
            }catch (Exception e){
                //TODO ist das hadling hier ok?
                if(ParseException.class.isInstance(e)){
                    System.out.println(e.getStackTrace());
                }else if(IllegalArgumentException.class.isInstance(e)){
                    System.out.println(e.getStackTrace());
                }
                System.out.println(Arrays.toString(csvLine) + " wurde ignoriert");
            }
        }

        return geladeneElemente;
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
    public static ArrayList<MuseumsElement> createElement(Class c, String dateiPfad) throws Exception {
        return createElement(c, dateiPfad, true);
    }

    public static Exponat createExponat(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        String inventarNr = csvData[0];

        ueberpruefeExistenz(Exponat.class, inventarNr);

        String name = csvData[1];
        Date entstehungsDatum = new SimpleDateFormat("yyyy.MM.dd").parse(csvData[2]);
        ArrayList<String> urheber = (ArrayList<String>) Arrays.asList(csvData[3].split(CSVSeparationLevel.LEVEL2.toString()));
        double benoetigteAusstellungsflaeche = Double.parseDouble(csvData[4]);
        ArrayList<String> kategorien = (ArrayList<String>) Arrays.asList(csvData[5].split(CSVSeparationLevel.LEVEL2.toString()));
        String epochenNr = csvData[6];
        Epoche epoche = (Epoche) MuseumsManager.find(Epoche.class, epochenNr);
        String herkunftsort = csvData[7];
        Exponatwert exponatwert = createExponatwert(csvData[8].split(CSVSeparationLevel.LEVEL2.toString()));
        Historie geschichtlicheH = createHistorie(csvData[9].split(CSVSeparationLevel.LEVEL2.toString()));
        Historie bearbeitungsH = createHistorie(csvData[10].split(CSVSeparationLevel.LEVEL2.toString()));
        Historie besitzH = createHistorie(csvData[11].split(CSVSeparationLevel.LEVEL2.toString()));
        Bild bild = createBild(csvData[12].split(CSVSeparationLevel.LEVEL2.toString()));
        String beschreibung = csvData[13];

        Exponat exponat = new Exponat(inventarNr, name, entstehungsDatum, urheber, benoetigteAusstellungsflaeche,
                kategorien, epoche, herkunftsort, exponatwert, geschichtlicheH, bearbeitungsH, besitzH, bild, beschreibung);

        // Exponat im Museum ablegen
        MuseumsManager.persist(Exponat.class, exponat);

        return exponat;
    }

    private static Exponatwert createExponatwert(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Exponatwert.class));

        float einkaufswert = Float.parseFloat(csvData[0]);
        float aktuellerSchaetzwert = Float.parseFloat(csvData[1]);
        float leihwert = Float.parseFloat(csvData[2]);

        Exponatwert exponatwert = new Exponatwert(einkaufswert, aktuellerSchaetzwert, leihwert);
        return exponatwert;
    }

    private static Historie createHistorie(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Historie.class));

        HashMap<Date, Ereignis> ereignisse = new HashMap<>();
        for (String ereignisCSV : csvData[0].split(CSVSeparationLevel.LEVEL3.toString())) {
            try {
                Ereignis ereignis = createEreignis(ereignisCSV.split(CSVSeparationLevel.LEVEL3.toString()));
                ereignisse.put(ereignis.getDatum(), ereignis);
            } catch (Exception e) {
                System.out.println(Arrays.toString(csvData) + " wurde ignoriert");
                // TODO vielleicht logging oder so um den User zu informieren das etwas schief gegangen ist
            }
        }

        Historie historie = new Historie(ereignisse);
        return historie;
    }

    private static Ereignis createEreignis(String[] csvData) throws ParseException {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Ereignis.class));

        Date datum = new SimpleDateFormat("yyyy.MM.dd").parse(csvData[0]);
        String beschreibung = csvData[1];

        Ereignis ereignis = new Ereignis(datum, beschreibung);

        return ereignis;
    }

    public static Bild createBild(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Bild.class));

        String bildNr = csvData[0];

        ueberpruefeExistenz(Bild.class, bildNr);

        String altText = csvData[1];
        String dateiName = csvData[2];
        String beschreibung = csvData[3];
        Bild bild = new Bild(bildNr, altText, dateiName, beschreibung);

        // Bild in Museum ablegen
        MuseumsManager.persist(Bild.class, bild);
        return bild;
    }

    public static Raum createRaum(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Raum.class));

        String raumNr = csvData[0];

        ueberpruefeExistenz(Raum.class, raumNr);

        String beschreibung = csvData[1];
        double ausstellungsflaeche = Integer.parseInt(csvData[2]);
        String ausstellungsthema = csvData[3];
        // Bilder finden
        ArrayList<Bild> bilder = new ArrayList<Bild>();
        for (String bildNr : csvData[4].split(String.valueOf(CSVSeparationLevel.LEVEL2))) {
            if (MuseumsManager.contains(Bild.class, bildNr)) {
                bilder.add((Bild) MuseumsManager.find(Bild.class, bildNr));
            } else {
                System.out.println("Bild " + bildNr + " unbekannt");
            }
        }
        // Exponate finden
        ArrayList<Exponat> exponate = new ArrayList<>();
        for (String exponatNr : csvData[5].split(String.valueOf(CSVSeparationLevel.LEVEL2))) {
            if (MuseumsManager.contains(Exponat.class, exponatNr)) {
                bilder.add((Bild) MuseumsManager.find(Bild.class, exponatNr));
            } else {
                System.out.println("Exponat " + exponatNr + " unbekannt");
            }
        }

        Raum raum = new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder, exponate);

        // Raum in Museum ablegen
        MuseumsManager.persist(Raum.class, raum);
        return raum;
    }

    public static Foerderer createFoerderer(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, 5);

        String foerdererNr = csvData[0];

        ueberpruefeExistenz(Foerderer.class, foerdererNr);

        String name = csvData[1];
        String gebDatum = csvData[2];
        String beschreibung = csvData[3];
        // Kontaktdaten
        ArrayList<Kontaktdaten> kontaktdaten = generateKonaktdatenList(
                csvData[4].split(CSVSeparationLevel.LEVEL2.toString()));

        // geförderte Exponate
        Foerderer foerderer = new Foerderer(foerdererNr, name, gebDatum, beschreibung, kontaktdaten);
        for (String exponatNr : csvData[5].split(CSVSeparationLevel.LEVEL2.toString())) {
            // suche das Exponat mit der gegebenen Exponatnummer im MuseumsManager und übergebe es dem erstellten Förderer
            try {
                Exponat exponat = (Exponat) MuseumsManager.find(Exponat.class, exponatNr);
                if (exponat == null) {
                    throw new Exception("Ein Exponat muss angelegt werden bevor es von einem Foerderer gesponsert werden kann.");
                }
                foerderer.foerdereWeiteresExponat(exponat);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }

        if (!csvData[6].equals("")) {
            foerderer.setBild((Bild) MuseumsManager.find(Bild.class, csvData[6]));
        }

        // Förderer im Museum ablegen
        MuseumsManager.persist(Person.class, foerderer);

        return foerderer;
    }

    private static Mitarbeiter createMitarbeiterTemplate(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        checkCSVarghLength(csvData, getNumberOfAttributes(Admin.class));

        String mitarbeiterNr = csvData[0];

        String name = csvData[1];
        String gebDatum = csvData[2];
        String beschreibung = csvData[3];

        //Kontakte laden
        ArrayList<Kontaktdaten> kontaktdaten = generateKonaktdatenList(
                csvData[4].split(CSVSeparationLevel.LEVEL2.toString()));

        Bild bild;
        if (csvData[6].equals("")) {
            bild = (Bild) MuseumsManager.find(Bild.class, "b0");
        } else {
            bild = createBild(csvData[5].split(CSVSeparationLevel.LEVEL2.toString()));
        }

        Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterNr, name, gebDatum, beschreibung, kontaktdaten, bild);

        return mitarbeiter;
    }

    public static Admin createAdmin(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        Mitarbeiter mT = createMitarbeiterTemplate(csvData);
        Admin admin = new Admin(mT.getPrimaryKey(), mT.getName(), mT.getGebDatum().toString(), mT.getBeschreibung(), mT.getKontakt(), mT.getBild());

        ueberpruefeExistenz(Admin.class, admin.getPrimaryKey());

        // Mitarbeiter in Museum ablegen
        MuseumsManager.persist(Admin.class, admin);
        return admin;
    }

    public static User createUser(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        Mitarbeiter mT = createMitarbeiterTemplate(csvData);
        User user = new User(mT.getPrimaryKey(), mT.getName(), mT.getGebDatum().toString(), mT.getBeschreibung(), mT.getKontakt(), mT.getBild());

        ueberpruefeExistenz(User.class, user.getPrimaryKey());

        // Mitarbeiter in Museum ablegen
        MuseumsManager.persist(User.class, user);
        return user;
    }

    public static HR createHR(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

        Mitarbeiter mT = createMitarbeiterTemplate(csvData);
        HR hr = new HR(mT.getPrimaryKey(), mT.getName(), mT.getGebDatum().toString(), mT.getBeschreibung(), mT.getKontakt(), mT.getBild());

        ueberpruefeExistenz(HR.class, hr.getPrimaryKey());

        // Mitarbeiter in Museum ablegen
        MuseumsManager.persist(HR.class, hr);
        return hr;
    }

    private static Kontaktdaten createKontaktdaten(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

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

    private static ArrayList<Kontaktdaten> generateKonaktdatenList(String[] csvData) {
        StringProcessor.trimCSVData(csvData);

        StringProcessor.trimCSVData(csvData);

        ArrayList<Kontaktdaten> kontaktdaten = new ArrayList<>();
        for (String kontakt : csvData) {
            Kontaktdaten neuerKontakt = createKontaktdaten(kontakt.split(CSVSeparationLevel.LEVEL3.toString()));
            kontaktdaten.add(neuerKontakt);
        }
        return kontaktdaten;
    }

    public static Epoche createEpoche(String[] csvData) throws Exception {
        StringProcessor.trimCSVData(csvData);

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

    /**
     * Diese methode überprüft ob die Länge der gegebenen CSV-Daten der gegebenen Länge entspricht
     *
     * @param csvData        überprüfte CSV-Daten
     * @param expectedLength gewünschte Länge
     */
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

    /**
     * Dies Metode überprüft ob ein Objekt mit gegebenem primaryKey bereits im Museum existiert.
     *
     * @param c          Klasse des Objekts
     * @param primaryKey gesuchter Primary key
     */
    private static void ueberpruefeExistenz(Class<?> c, String primaryKey) throws KeyException {
        if (MuseumsManager.contains(c, primaryKey)) {
            throw new KeyException(c.getSimpleName() + " mit gleichem PrimaryKey exisitert bereits");
        }
    }
}
