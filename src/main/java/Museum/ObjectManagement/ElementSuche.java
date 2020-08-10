package Museum.ObjectManagement;

import Museum.Exponat.Exponat;
import Museum.Person.Person;
import Museum.Raum.Raum;

import java.util.ArrayList;

public class ElementSuche {
    private MuseumsManager museumsM;
    private PersonenManager personenM;
    private ExponatManager exponatM;
    private RaumManager raumM;

    public ElementSuche(MuseumsManager museumsM) {
        this.museumsM = museumsM;
        this.personenM = museumsM.getPersonenM();
        this.exponatM = museumsM.getExponatManager();
        this.raumM = museumsM.getRaumManager();
    }


    public MuseumsManager getMuseumsM() {
        return museumsM;
    }

    public void setMuseumsM(MuseumsManager museumsM) {
        this.museumsM = museumsM;
    }

    // TODO Aufbau der Methoden der Suche festlegen
    public ArrayList<Person> suchePerson() {
        return null; //TODO such-Algorythmus
    }


    public ArrayList<Exponat> sucheExponat() {
        return null; //TODO such-Algorythmus
    }

    public ArrayList<Raum> sucheRaum() {
        return null; //TODO such-Algorythmus
    }
}
