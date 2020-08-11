/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Exponat.Exponat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Foerderer extends Person {

    private ArrayList<Exponat> gefoerderteExponate;

    public Foerderer(String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt, ArrayList<Exponat> gefoerderteExponate) throws ParseException {
        super(name, gebDatum, beschreibung, kontakt);
        this.gefoerderteExponate = gefoerderteExponate;
    }

    public Foerderer(String name, String gebDatum, String beschreibung, ArrayList<Kontaktdaten> kontakt) throws ParseException {
        super(name, gebDatum, beschreibung, kontakt);
        this.gefoerderteExponate = new ArrayList<>();
    }

    public ArrayList<Exponat> getGefoerderteExponate() {
        return gefoerderteExponate;
    }

    public Exponat getGefoerdertesExponat(int inventarNR) {
        for (Exponat exponat : this.gefoerderteExponate) {
            if(exponat.getInventarNummer() == inventarNR){
                return exponat;
            }
        }
        return null;
    }

    public void setGeförderteExponate(ArrayList<Exponat> geförderteExponate) {
        this.gefoerderteExponate = geförderteExponate;
    }

    @Override
    public String toString() {
        String foerderer = "";
        foerderer += super.toString();
        for (Exponat exponat : this.gefoerderteExponate) {
            foerderer += String.format("Exponat: %s%n", exponat);
        }
        return foerderer;
    }
}
