package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.Person.Person;
import Museum.Raum.Raum;

public class ElementSuche {

    /**
     * Diese Methode soll das suchen nach einer Person ermöglichen.
     * Aktuell kann man aber nur abfragen ob eine Person mit entsprechendem PrimaryKey vorhanaden ist.
     * Die Implementierung wir in der Durchführung der SWE-Projektarbeit nicht stattfinden.
     *
     * @param primaryKey gesuchter primaryKey
     * @return das gesuchte Objekt
     */
    public static Person suchePerson(String primaryKey) {
        return (Person) MuseumsManager.find(Person.class, primaryKey);
    }

    /**
     * Diese Methode soll das suchen nach einer Exponaten ermöglichen.
     * Aktuell kann man aber nur abfragen ob eine Person mit entsprechendem PrimaryKey vorhanaden ist.
     * Die Implementierung wir in der Durchführung der SWE-Projektarbeit nicht stattfinden.
     *
     * @param primaryKey gesuchter primaryKey
     * @return das gesuchte Objekt
     */
    public static Exponat sucheExponat(String primaryKey) {
        return (Exponat) MuseumsManager.find(Exponat.class, primaryKey);
    }

    /**
     * Diese Methode soll das suchen nach einer Räumen ermöglichen.
     * Aktuell kann man aber nur abfragen ob eine Person mit entsprechendem PrimaryKey vorhanaden ist.
     * Die Implementierung wir in der Durchführung der SWE-Projektarbeit nicht stattfinden.
     *
     * @param primaryKey gesuchter primaryKey
     * @return das gesuchte Objekt
     */
    public static Raum sucheRaum(String primaryKey) {
        return (Raum) MuseumsManager.find(Raum.class, primaryKey);
    }

    /**
     * Diese Methode soll das suchen nach einer Epochen ermöglichen.
     * Aktuell kann man aber nur abfragen ob eine Person mit entsprechendem PrimaryKey vorhanaden ist.
     * Die Implementierung wir in der Durchführung der SWE-Projektarbeit nicht stattfinden.
     *
     * @param primaryKey gesuchter primaryKey
     * @return das gesuchte Objekt
     */
    public static Epoche sucheEpoche(String primaryKey){
        return (Epoche) MuseumsManager.find(Epoche.class, primaryKey);
    }

    /**
     * Diese Methode soll das suchen nach einer Bild ermöglichen.
     * Aktuell kann man aber nur abfragen ob eine Person mit entsprechendem PrimaryKey vorhanaden ist.
     * Die Implementierung wir in der Durchführung der SWE-Projektarbeit nicht stattfinden.
     *
     * @param primaryKey gesuchter primaryKey
     * @return das gesuchte Objekt
     */
    public static Bild sucheBild(String primaryKey){
        return (Bild) MuseumsManager.find(Bild.class, primaryKey);
    }
}
