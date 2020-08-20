package Museum.ObjectManagement;

import Museum.Exponat.Exponat;
import Museum.Person.Person;
import Museum.Raum.Raum;

public class ElementSuche { // TODO static machen


    /**
     * Dieses Objekt stellt eine Suche dar mit der auf den MuseumsManager zugegriffen werden kann.
     * Sie ist hier nicht weiter als mit find-methoden implementiert.
     */

    // TODO Aufbau der Methoden der Suche festlegen
    public static Person suchePerson(String primaryKey) {
        return (Person) MuseumsManager.find(Person.class, primaryKey); //TODO such-Algorythmus
    }

    public static Exponat sucheExponat(String primaryKey) {
        return (Exponat) MuseumsManager.find(Exponat.class, primaryKey); //TODO such-Algorythmus
    }

    public static Raum sucheRaum(String primaryKey) {
        return (Raum) MuseumsManager.find(Raum.class, primaryKey); //TODO such-Algorythmus
    }
}
