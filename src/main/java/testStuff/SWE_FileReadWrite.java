/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package testStuff;

import de.dhbwka.swe.utils.util.CSVReader;
import de.dhbwka.swe.utils.util.CSVWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SWE_FileReadWrite {

    public static void main(String[] args) throws IOException {


        String path = "/mnt/data/the_oronco/Desktop/Projekte-DHBW-Karlsruhe/src/main/java/testStuff/big_OuFFFfff.csv";
        //writing test
        System.out.println("\n\nwriting:\n");
        CSVWriter write = new CSVWriter(path, true);
        List<String[]> schwabäm = new ArrayList<String[]>();
        String[] header = new String[]{"first ufff", "stuff", "other stuff", "last uff"};

        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            String[] stuff = new String[]{String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt())};
            System.out.println(Arrays.toString(stuff));
            schwabäm.add(stuff);
        }
        write.writeDataToFile(schwabäm, header);

        //reading test
        System.out.println("\n\nreading:\n");
        CSVReader read = new CSVReader(path);
        //List<String[]> uff = read.readData();
        //System.out.println(uff.get(0)[0]);;

        List<String[]> argh = read.readData(4, ';', '#');
        for (String[] s : argh) {
            System.out.println(Arrays.toString(s));
        }

        System.out.println(CSVReader.DEFAULT_COMMENT);

        //check if everything is the same
        for (int i = 0; i < argh.size(); i++) {
            for (int j = 0; j < argh.get(0).length; j++) {
                if (argh.get(i)[j].equals(schwabäm.get(i)[j])) {
                    System.out.println("gooooood");
                } else {
                    System.out.println("baaaaaad");
                }
            }
        }

        System.out.println(Arrays.toString(read.readFirstCommentsFromFile(4, ';', "#")));

    }
}
