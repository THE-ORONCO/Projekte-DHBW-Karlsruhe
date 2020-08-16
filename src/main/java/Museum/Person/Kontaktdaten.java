/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class Kontaktdaten { //TODO vielleicht von MuseumsElement erben lassen
    private ArrayList<String> emailAdressen;
    private ArrayList<String> teleNr;
    private ArrayList<Anschrift> anschriften;

    /**
     * erstellt ein Kontaktdaten-Objekt welches Daten über alle Kontaktmöglichkeiten über eine Person enthält
     *
     * @param emailAdressen Liste alle E-Mail-Adressen unter der eine Person erreichbar ist
     * @param teleNr Alle Telefonnummern unter der eine Person erreichbar ist
     * @param anschriften Alle Anschriften unter der eine Person erreichbar ist
     * @throws ValueException wenn die E-Mail-Adresse oder die Telefonnummer nicht dem entsprechenden Regex entspricht
     */
    public Kontaktdaten(ArrayList<String> emailAdressen, ArrayList<String> teleNr, ArrayList<Anschrift> anschriften) throws ValueException { //DIFF teleNr ist String, da int oft zu klein und List, da jede Person auch mehrere Telefonnummern haben kann
        this.emailAdressen = new ArrayList<String>();
        // überprüfe die E-Mail-Adressen auf ihre Richtigkeit
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        for (String emailAdresse : emailAdressen) {
            if (emailPattern.matcher(emailAdresse).matches()) {
                this.emailAdressen.add(emailAdresse);
            } else {
                throw new ValueException("E-Mail-Adresse hat falsches Format");
            }
        }

        this.teleNr = new ArrayList<String>();
        // überprüfe die Telefonnummern auf ihre Richtigkeit
        Pattern teleNrPattern = Pattern.compile("^[\\+]?[(]?[0-9]{2,3}[)]?[-\\s\\.]?[0-9]{3,4}[-\\s\\.]?[0-9]{4,7}$");
        for (String tNr : teleNr) {
            if (teleNrPattern.matcher(tNr).matches()) {
                this.teleNr.add(tNr);
            } else {
                throw new ValueException("Telefonnummer hat falsches Format");
            }
        }

        this.anschriften = anschriften;

    }

    public Kontaktdaten(ArrayList<String> emailAdressen, String teleNr, ArrayList<Anschrift> anschriften) {
        /**
         * @param teleNr hier kann auch nur ein einzelner String als Telefonnummer eingegeben werden
         */
        this(emailAdressen, new ArrayList<String>(Collections.singleton(teleNr)), anschriften);
    }

    public Kontaktdaten(String emailAdresse, ArrayList<String> teleNr, ArrayList<Anschrift> anschriften) {
        /**
         * @param teleNr hier kann auch nur ein einzelner String als E-Mail-Adresse eingegeben werden
         */
        this(new ArrayList<String>(Collections.singleton(emailAdresse)), teleNr, anschriften);
    }

    public Kontaktdaten(String emailAdresse, String teleNr, ArrayList<Anschrift> anschriften) {
        /**
         * @param teleNr hier kann auch nur ein einzelner String als Telefonnummer und als E-Mail-Adresse eingegeben werden
         */
        this(new ArrayList<String>(Collections.singleton(emailAdresse)), new ArrayList<String>(Collections.singleton(teleNr)), anschriften);
    }


    public ArrayList<String> getEmailAdressen() {
        return emailAdressen;
    }

    public void setEmailAdressen(ArrayList<String> emailAdressen) {
        this.emailAdressen = emailAdressen;
    }

    public ArrayList<String> getTeleNr() {
        return teleNr;
    }

    public void setTeleNr(ArrayList<String> teleNr) {
        this.teleNr = teleNr;
    }

    public ArrayList<Anschrift> getAnschriften() {
        return anschriften;
    }

    public void setAnschriften(ArrayList<Anschrift> anschriften) {
        this.anschriften = anschriften;
    }

    public boolean addAnschrift(Anschrift anschrift) {
        return this.anschriften.add(anschrift);
    }

    public boolean remove(Anschrift anschrift) {
        return this.anschriften.remove(anschrift);
    }

    @Override
    public String toString() {
        /**
         * @return formatierter String mit allen Argumenten der Kontaktdaten
         */
        String kontakt = "";
        if (this.anschriften.size() > 0) {
            kontakt += String.format("Anschrift:%n");
            for (Anschrift anschrift : this.anschriften) {
                kontakt += String.format("%s%n", anschrift);
            }
        }
        if (this.teleNr.size() > 0) {
            for (String tNr : this.teleNr) {
                kontakt += String.format("Telefon: %s%n", tNr);
            }
        }
        if (this.emailAdressen.size() > 0) {
            for (String emailAdresse : this.emailAdressen) {
                kontakt += String.format("E-Mail: %s", emailAdresse);
            }
        }
        return kontakt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kontaktdaten that = (Kontaktdaten) o;
        return emailAdressen.equals(that.emailAdressen) &&
                teleNr.equals(that.teleNr) &&
                anschriften.equals(that.anschriften);
    }

}

