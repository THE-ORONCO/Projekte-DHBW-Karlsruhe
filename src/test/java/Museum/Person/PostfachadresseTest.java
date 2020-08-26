/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.Person;

import Museum.Person.Postfachadresse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostfachadresseTest extends AnschriftTest {


    private Postfachadresse postfachadresse;

    private Postfachadresse initialize() {
        this.postfachadresse = new Postfachadresse(69420, 76149, "Karlsruhe", "Deutschland");
        return this.postfachadresse;
    }

    @Test
    public void getPostfachnummer() {
        initialize();
        assertEquals(postfachadresse.getPostfachnummer(), 69420);
    }

    @Test
    public void setPostfachnummer() {
        initialize();
        postfachadresse.setPostfachnummer(42069);
        assertEquals(postfachadresse.getPostfachnummer(), 42069);
    }

    @Test
    public void testToString() {
        initialize();
        assertEquals(postfachadresse.toString(), "Postfachnummer:69420\n" +
                "76149 Karlsruhe\n" +
                "Karlsruhe\n" +
                "Deutschland");
    }

    @Test
    public void testEquals() {
        initialize();
        assertEquals(postfachadresse, new Postfachadresse(69420, 76149, "Karlsruhe", "Deutschland"));
    }
}