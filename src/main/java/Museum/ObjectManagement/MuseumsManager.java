package Museum.ObjectManagement;

import java.util.ArrayList;

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

    public boolean importieren(String path, Class importetType) {//TODO Spezifikation der Methodensignatur
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
