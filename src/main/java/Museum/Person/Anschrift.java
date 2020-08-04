package Museum.Person;

public class Anschrift {
    private String stadt;
    private int plz;
    private String land; //DIFF Land hat bei der Definition gefehlt

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
        return String.format("%s %s%n%s%n", this.stadt, this.plz, this.land);
    }
}
