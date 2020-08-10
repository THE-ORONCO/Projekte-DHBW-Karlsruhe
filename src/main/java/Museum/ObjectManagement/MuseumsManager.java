package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.*;
import Museum.Person.Foerderer;
import de.dhbwka.swe.utils.util.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MuseumsManager {
    private PersonenManager personenM;
    private RaumManager raumM;
    private ExponatManager exponatM;

    public MuseumsManager(PersonenManager personenM, RaumManager raumM, ExponatManager exponatM) {
        this.personenM = personenM;
        this.raumM = raumM;
        this.exponatM = exponatM;
    }

    public PersonenManager getPersonenM() {
        return personenM;
    }

    public void setPersonenManager(PersonenManager personenM) {
        this.personenM = personenM;
    }

    public RaumManager getRaumManager() {
        return raumM;
    }

    public void setRaumManager(RaumManager raumM) {
        this.raumM = raumM;
    }

    public ExponatManager getExponatManager() {
        return exponatM;
    }

    public void setExponatManger(ExponatManager exponatM) {
        this.exponatM = exponatM;
    }

    public boolean importieren(String path, ImportableElements type) throws IOException {
        CSVReader reader = new CSVReader(path);
        String[] header = reader.readFirstCommentsFromFile(type.nrOfArguments, CSVReader.DEFAULT_DELIMITER, "#");
        ArrayList<String[]> data = new ArrayList<String[]>(reader.readData(type.nrOfArguments, CSVReader.DEFAULT_DELIMITER, CSVReader.DEFAULT_COMMENT));

        switch (type) {
            case EXPONAT:
                // jedes exponat mit hilfe der Daten der CSV-Datei generieren
                for (String[] exponat : data) {
                    int inventarNummer = Integer.parseInt(exponat[0]);
                    String name = exponat[1];
                    Date entstehungsdatum = new Date();//TODO rausfinden wie Datumse und co gemacht werden
                    ArrayList<String> urheber = new ArrayList<String>(Arrays.asList(exponat[3].split(",")));
                    double benötigteAusstellungsfläche = Double.valueOf(exponat[4]);
                    ArrayList<String> kategorien = new ArrayList<String>(Arrays.asList(exponat[5].split(",")));
                    ArrayList<Epoche> epoche = new ArrayList<Epoche>(); //TODO Epochen-Implementierung damit das hier funktioniert
                    String herkunftsort = exponat[7];
                    ArrayList<Foerderer> foerderer = null; //TODO foerderer von personenM abgreifen
                    // exponartwert
                    String[] exponatwertAttribute = exponat[9].split(",");
                    Float einkaufswert = Float.valueOf(exponatwertAttribute[0]);
                    Float aktuellerSchätzwert = Float.valueOf(exponatwertAttribute[1]);
                    Float leihwert = Float.valueOf(exponatwertAttribute[2]);
                    Exponatwert exponatwert = new Exponatwert(einkaufswert, aktuellerSchätzwert, leihwert);
                    GeschichtilcheHistorie geschichtilcheH = null; //TODO geschichtliche Historie iwoher anfordern
                    BearbeitungsHistorie bearbeitungsH = null; //TODO Bearbeitungshistorie iwoher anfordern
                    BesitzHistorie besitzH = null; //TODO Besitzhistorie iwoher anfordern
                    //bild
                    String[] bildAttribute = exponat[13].split(",");
                    Bild bild = new Bild(bildAttribute[0], bildAttribute[1], bildAttribute[3]);
                    String beschreibung = exponat[14];

                    Exponat exponatInstance = new Exponat(inventarNummer, name, entstehungsdatum, urheber,
                            benötigteAusstellungsfläche, kategorien, epoche, herkunftsort, foerderer, exponatwert,
                            geschichtilcheH, bearbeitungsH, besitzH, bild, beschreibung);
                    this.getExponatManager().persist(exponatInstance);
                }
                break;
            case PERSON:
                break;
            case RAUM:
                break;
            case BILD:
                break;
            default:

        }
        for (String item : header) {

        }

        //TODO Spezifikation der Methodensignatur
        //TODO SWE-Tools benutzen um dat zu importieren
        return false;
    }

    // TODO vielleicht sollte man die export-Methoden für die einzelnen Elementtypen aufteilen
    // Standartmethode exportiert im CSV-Format
    public boolean exportieren(String path, ArrayList<Object> exportedElements, boolean ueberschreiben) {
        return this.exportieren(path, exportedElements, ueberschreiben, "CSV");
    }

    public boolean exportieren(String path, ArrayList<Object> exportedElements, boolean ueberschreiben, String exportType) {
        // TODO SWE-Tools benutzen um dat zu exportieren
        return false;
    }
}
