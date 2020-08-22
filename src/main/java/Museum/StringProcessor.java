package Museum;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.Person.Admin;
import Museum.Person.Foerderer;
import Museum.Person.HR;
import Museum.Person.User;
import Museum.Raum.Raum;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class StringProcessor {

    public static String[] trimCSVData(String[] csvData) {
        for (int i = 0; i < csvData.length; i++) {
            csvData[i] = csvData[i].trim();
        }
        return csvData;
    }

    public static boolean validierePrimaryKey(Class<?> c, String primaryKey) {
        char startCharacter = waehleKeyStartCharakter(c);
        if (primaryKey.matches("[a-zA-Z0-9]*") && primaryKey.startsWith(String.valueOf(startCharacter))) {
            return true;
        }
        return false;
    }

    public static String generiereRandomAlphaNumString(int laenge) {
        String alphaNumChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        StringBuilder sb = new StringBuilder( laenge );
        for( int i = 0; i < laenge; i++ )
            sb.append( alphaNumChars.charAt( random.nextInt(alphaNumChars.length()) ) );
        return sb.toString();
    }

    public static String generiereRandomAlphaNumString(){
        Random random = new Random();
        int high = 23;
        int low = 1;
        int len = random.nextInt(high - low) + low;

        return generiereRandomAlphaNumString(len);
    }

    public static char waehleKeyStartCharakter(Class<?>c){
        char startingCharacter;
        if (c == Admin.class) {
            startingCharacter = 'a';
        } else if (c == User.class) {
            startingCharacter = 'u';
        } else if (c == HR.class) {
            startingCharacter = 'h';
        } else if (c == Foerderer.class) {
            startingCharacter = 'f';
        } else if (c == Raum.class) {
            startingCharacter = 'r';
        } else if (c == Exponat.class) {
            startingCharacter = 'x';
        } else if (c == Epoche.class) {
            startingCharacter = 'e';
        } else if (c == Bild.class) {
            startingCharacter = 'b';
        } else throw new IllegalArgumentException("Unbekante Klasse: " + c);
        return startingCharacter;
    }
}
