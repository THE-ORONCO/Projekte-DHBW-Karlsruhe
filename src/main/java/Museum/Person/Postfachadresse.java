package Museum.Person;

public class Postfachadresse extends Anschrift {
    private int postfachnummer;

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
            return String.format("%d%n%d %s%n%s%n", this.postfachnummer, this.getPlz(), this.getStadt(), this.getStadt());
        } else {
            return String.format("%d%n%d %s%n%s%n%s%n", this.postfachnummer, this.getPlz(), this.getStadt(), this.getStadt(), this.getLand());
        }
    }
}
