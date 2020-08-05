package Museum.ObjectManagement;

import Museum.Raum.Raum;

public class RaumFactory {


    public static Raum create(int raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema, RaumManager raumManager) {
        Raum raum = RaumFactory.create(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema);
        raumManager.persist(raum);
        return raum;
    }

    public static Raum create(int raumNr, String beschreibung, double ausstellungsflaeche, String ausstellungsthema){
        return new Raum(raumNr, beschreibung, ausstellungsflaeche, ausstellungsthema);
    }

    public static Raum create(int raumNr, double ausstellungsflaeche){
        return new Raum(raumNr, ausstellungsflaeche);
    }


}
