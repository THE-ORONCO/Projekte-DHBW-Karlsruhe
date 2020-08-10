/**
 * @autor Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.BackendTests.Person;

import Museum.Person.Anschrift;
import org.junit.Test;


public class AnschriftTest {

    private Anschrift anschrift;

    private Anschrift initialize(){
        this.anschrift = new Anschrift(89601, "Schelklingen", "Deutscheland");
        return this.anschrift;
    }

    @Test
    public void getStadt() {
        initialize();
        assert anschrift.getStadt().equals("Schelklingen");
    }

    @Test
    public void setStadt() {
        initialize();
        anschrift.setStadt("Baumhausen");
        assert anschrift.getStadt().equals("Baumhausen");
    }

    @Test
    public void getPlz() {
        initialize();
        assert anschrift.getPlz()==89601;
    }

    @Test
    public void setPlz() {
        initialize();
        anschrift.setPlz(69420);
        assert anschrift.getPlz()==69420;
    }

    @Test
    public void getLand() {
        initialize();
        assert anschrift.getLand().equals("Deutscheland");
    }

    @Test
    public void setLand() {
        initialize();
        anschrift.setLand("Mother Russia");
        assert anschrift.getLand().equals("Mother Russia");
    }

    @Test
    public void testToString() {
        initialize();
        String anschriftAlsString = "Schelklingen 89601\n" +
                "Deutscheland";
        assert anschrift.toString().equals(anschriftAlsString);
    }

    @Test
    public void testEquals(){
        initialize();
        Anschrift anschrift2 = new Anschrift(89601, "Schelklingen", "Deutscheland");
        assert anschrift.equals(anschrift2);
    }
}