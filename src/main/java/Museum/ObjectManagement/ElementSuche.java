package Museum.ObjectManagement;

import Museum.Exponat.Exponat;
import Museum.Person.Person;
import Museum.Raum.Raum;

import java.util.ArrayList;

public class ElementSuche {
    private MuseumsManager museumsM;


    public ElementSuche(MuseumsManager museumsM) {
        this.museumsM = museumsM;

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
