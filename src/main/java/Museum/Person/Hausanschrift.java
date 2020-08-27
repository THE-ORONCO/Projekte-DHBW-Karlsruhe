/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;


public class Hausanschrift extends Anschrift {
    private String name;
    private String strasse;
    private int hausnummer;

    /**
     * Die Postfachadresse ist eine spezifischere Version der Anschrift
     * @see Anschrift
     *
     * @param name Name der Person die unter dieser Anschrift gemeldet ist
     * @param strasse Straße der Anschrift
     * @param hausnummer Hausnummer in der Straße
     * @param plz Postleitzahl der Stadt in der die Adresse ansässig ist
     * @param stadt Stadt passend zur Postleitzahl
     * @param land Land in der die Adresse liegt (kann weggelassen werden)
     */
    public Hausanschrift(String name, String strasse, int hausnummer, int plz, String stadt, String land) {
        super(plz, stadt, land);
        this.name = name;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    public Hausanschrift(String name, String strasse, int hausnummer, int plz, String stadt) {
        this(name, strasse, hausnummer, plz, stadt, "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    @Override
    public String toString() {

        if (this.getLand().equals("")) {
            return String.format("%s%n%s %d%n%d %s", this.name, this.strasse, this.hausnummer, this.getPlz(), this.getStadt());
        } else {
            return String.format("%s%n%s %d%n%d %s%n%s", this.name, this.strasse, this.hausnummer, this.getPlz(), this.getStadt(), this.getLand());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hausanschrift that = (Hausanschrift) o;
        return hausnummer == that.hausnummer &&
                name.equals(that.name) &&
                strasse.equals(that.strasse);
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String[] parseToCSV() {
        String[] csvData = new String[]{
                getName(),
                getStrasse(),
                String.valueOf(getHausnummer()),
                String.valueOf(getPlz()),
                getStadt(),
                getLand()
        };
        return csvData;
    }
}
