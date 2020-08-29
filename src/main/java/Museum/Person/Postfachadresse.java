/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

public class Postfachadresse extends Anschrift {
    private int postfachnummer;

    /**
     * Die Postfachadresse ist eine spezifischere Version der Anschrift welche zusätzlich eine Postfachnummer speichert
     * @see Anschrift
     *
     * @param postfachnummer Postfachnummer des adressierten Postfachs
     * @param plz Postleitzahl der Stadt
     * @param stadt Stadt die mit der Anschrift refferenziert wird
     * @param land Land der Anschrift (kann auch weggelassen werden)
     */
    public Postfachadresse(int postfachnummer, int plz, String stadt, String land) {
        super(plz, stadt, land);
        this.postfachnummer = postfachnummer;
    }

    public Postfachadresse(int postfachnummer, int plz, String stadt) {
        this(postfachnummer, plz, stadt, "");
    }

    public int getPostfachnummer() {
        return postfachnummer;
    }

    public void setPostfachnummer(int postfachnummer) {
        this.postfachnummer = postfachnummer;
    }

    @Override
    public String toString() {
        if (this.getLand().equals("")) {
            return String.format("Postfachnummer:%d%n%d %s%n%s", this.postfachnummer, this.getPlz(), this.getStadt(), this.getStadt());
        } else {
            return String.format("Postfachnummer:%d%n%d %s%n%s%n%s", this.postfachnummer, this.getPlz(), this.getStadt(), this.getStadt(), this.getLand());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Postfachadresse that = (Postfachadresse) o;
        return postfachnummer == that.postfachnummer;
    }

    /**
     * konvertiert das Objekt in ein vom SWE-Utils-CSV-Reader/Writer verarbeitbares CSV-Format
     *
     * @return Objekt im CSV-Format
     */
    @Override
    public String[] parseToCSV() {
        String[] csvDaten = new String[]{
                String.valueOf(getPostfachnummer()),
                String.valueOf(getPlz()),
                getStadt(),
                getLand()
        };
        return csvDaten;
    }
}
