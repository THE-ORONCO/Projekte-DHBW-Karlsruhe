/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

public class Anschrift {
    private String stadt;
    private int plz;
    private String land; //DIFF Land hat bei der Definition gefehlt

    /**
     * definiert die Anschrift einer Person auf einem Stadt-Level
     * @param plz Postleitzahl der Stadt
     * @param stadt Stadt die mit der Anschrift refferenziert wird
     * @param land Land der Anschrift (kann auch weggelassen werden)
     */
    public Anschrift(int plz, String stadt, String land) {
        this.stadt = stadt;
        this.plz = plz;
        this.land = land;
    }

    public Anschrift(String stadt, int plz) {
        this(plz, stadt, "");
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n%s", this.stadt, this.plz, this.land);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anschrift anschrift = (Anschrift) o;
        return plz == anschrift.plz &&
                stadt.equals(anschrift.stadt) &&
                land.equals(anschrift.land);
    }

    public String[] parseToCSV(){
        return new String[]{};//TODO parseToCSV für die Anschriften
    }
}
