package Museum.ObjectManagement;

import Museum.Bild.Bild;
import Museum.Raum.Raum;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;

public class RaumFactory {

    /**
     * Diese Methoden sind dazu da um die Raumerstellung zu erleichtern
     *
     * @param raumNr              Nummer mit der der Raum im System eindeutig identifiziert werden kann
     * @param beschreibung        eine Kurze Beschreibung des raumes (z.B.: zwei Fenster, eine Säule, 3 Türen)
     * @param ausstellungsflaeche benutzbare Ausstellungsfläche die in diesem Raum zur Verfügung steht in Quadratmetern
     * @param ausstellungsthema   Thema der Ausstellung im aktuellen Raum
     * @param bilder              bilder des Raumes
     * @return den erstellten (und optional im Raummanager gespeicherten) Raum
     */
    public static Raum create(int raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema, ArrayList<Bild> bilder) {
        Raum raum = new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder);
        return raum;
    }

    /**
     * Diese Methoden sind dazu da um die Raumerstellung zu erleichtern und sie wenn nötig automatisch einem RaumManager zu übergeben
     *
     * @param raumNr              Nummer mit der der Raum im System eindeutig identifiziert werden kann
     * @param beschreibung        eine Kurze Beschreibung des raumes (z.B.: zwei Fenster, eine Säule, 3 Türen)
     * @param ausstellungsflaeche benutzbare Ausstellungsfläche die in diesem Raum zur Verfügung steht in Quadratmetern
     * @param ausstellungsthema   Thema der Ausstellung im aktuellen Raum
     * @param bilder              bilder des Raumes
     * @param raumManager         Raum-Manager-Objekt welches den Raum speichern soll
     * @return den erstellten (und optional im Raummanager gespeicherten) Raum
     * @throws ValueException wenn der Raum bereits im Raummanager vorhanden ist
     */
    public static Raum create(int raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema, ArrayList<Bild> bilder, RaumManager raumManager) throws ValueException {
        Raum raum = create(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, bilder);
        raumManager.persist(raum);
        return raum;
    }


    public static Raum create(int raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema) {
        Raum raum = create(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema, new ArrayList<Bild>());
        return raum;
    }

    public static Raum create(int raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema, RaumManager raumManager) throws ValueException {
        Raum raum = create(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema);
        raumManager.persist(raum);
        return raum;
    }

    public static Raum create(int raumNr, double ausstellungsflaeche) {
        Raum raum = create(raumNr, "Raum", ausstellungsflaeche, "nicht zugewiesen");
        return raum;
    }

    public static Raum create(int raumNr, double ausstellungsflaeche, RaumManager raumManager) throws ValueException {
        Raum raum = create(raumNr, ausstellungsflaeche);
        raumManager.persist(raum);
        return raum;
    }


}
