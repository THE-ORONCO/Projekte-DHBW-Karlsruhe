import Museum.ObjectManagement.MuseumsManager;
import de.dhbwka.swe.utils.util.AppLogger;
import org.apache.commons.cli.*;

import java.util.Arrays;

/**
 * @author Théo Roncoletta - TINF18B1
 * @version 1.0
 */
public class SWEMuseumsVerwaltung {
    public static void main(String[] args) {

        // setup aller Comandline-Optionen (aktuell nur eine)
        Options optionen = new Options();

        // pfad
        Option pfad = new Option("p", "Pfad", true, "Pfad zum Ressourcen Ordner");
        optionen.addOption(pfad);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;
        
        try{
            cmd = parser.parse(optionen, args);
        }catch (ParseException e){
            e.printStackTrace();
            formatter.printHelp("SWE-MuseumsVerwaltung", optionen);
            
            System.exit(1);
        }


        String resourcePath = cmd.getOptionValue("p");

        // setup
        String defaultPath = resourcePath + (resourcePath.endsWith("/") ? "default" : "/default");
        System.out.println(defaultPath);
        try {
            MuseumsManager.ladeDefaultElemente(defaultPath);
        } catch (Exception e) {
            e.printStackTrace();
            AppLogger.getInstance().error("default Elemente konnten nicht geladen werden");
        }
    }
}
