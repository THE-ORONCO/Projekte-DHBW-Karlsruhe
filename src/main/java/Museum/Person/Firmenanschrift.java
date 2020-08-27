/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

public class Firmenanschrift extends Anschrift {
    private String firma;
    private String name;
    private String strasse;
    private int hausnummer;

    /**
     * Die Firmenanschrift ist eine spezifischere Version der Anschrift
     * @see Anschrift
     *
     * @param firma Firma an der die Person mit dieser Anschrift arbeitet
     * @param name Name der Person mit dieser Anschrift
     * @param strasse Straße der Anschrift
     * @param hausnummer Hausnummer in der Straße
     * @param plz Postleitzahl der Stadt
     * @param stadt Stadt die mit der Anschrift refferenziert wird
     * @param land Land der Anschrift (kann auch weggelassen werden)
     */
    public Firmenanschrift(String firma, String name, String strasse, int hausnummer, int plz, String stadt, String land) {
        super(plz, stadt, land);
        this.firma = firma;
        this.name = name;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    public Firmenanschrift(String firma, String name, String strasse, int hausnummer, int plz, String stadt) {
        this(firma, name, strasse, hausnummer, plz, stadt, "");
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
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
            return String.format("%s%n%s%n%s %d%n%d %s", this.firma, this.name, this.strasse, this.hausnummer, this.getPlz(), this.getStadt());
        } else {
            return String.format("%s%n%s%n%s %d%n%d %s%n%s", this.firma, this.name, this.strasse, this.hausnummer, this.getPlz(), this.getStadt(), this.getLand());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Firmenanschrift that = (Firmenanschrift) o;
        return hausnummer == that.hausnummer &&
                firma.equals(that.firma) &&
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
                getFirma(),
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
