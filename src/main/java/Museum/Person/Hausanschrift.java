package Museum.Person;

public class Hausanschrift extends Anschrift {
    private String name;
    private String strasse;
    private int hausnummer;

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
            return String.format("%s%n%s %d%n%d %s%n", this.name, this.strasse, this.hausnummer, this.getPlz(), this.getStadt());
        } else {
            return String.format("%s%n%s %d%n%d %s%n%s%n", this.name, this.strasse, this.hausnummer, this.getPlz(), this.getStadt(), this.getLand());
        }
    }
}
