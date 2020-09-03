package app;

import Museum.ObjectManagement.MuseumsManager;
import de.dhbwka.swe.utils.util.AppLogger;
import de.dhbwka.swe.utils.util.PropertyManager;
import org.apache.commons.cli.*;


/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
public class SWEMuseumsVerwaltung {
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.home"));

        // setup aller Comandline-Optionen (aktuell nur eine)
        Options optionen = new Options();

        // Pfad
        //SPCL mehr command line options
        Option pfad = new Option("p", "Pfad", true, "Pfad zum Ressourcen Ordner");
        optionen.addOption(pfad);
        //default-Ressourcen-Pfad
        Option defaultPfad = new Option("d", "Default Pfad", true, "Pfad zu Default dateien");
        optionen.addOption(defaultPfad);
        //logging
        Option fileLogging = new Option("l", "Logging zu Datei", true, "Pfad zu einer Logdatei");
        optionen.addOption(fileLogging);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(optionen, args);
        } catch (ParseException e) {
            e.printStackTrace();
            formatter.printHelp("SWE-MuseumsVerwaltung", optionen);

            System.exit(1);
        }

        if (cmd.hasOption("l")) {
            AppLogger.getInstance().log2File(cmd.getOptionValue("l"), true);
        }

        String ressourcenPfad = "";
        if (cmd.hasOption("p")) {
            ressourcenPfad = cmd.getOptionValue("p");
            AppLogger.getInstance().info("Ressourcen unter " + ressourcenPfad + " verwendet");
        } else {
            AppLogger.getInstance().warning("Kein Ressourcenpfad gegeben. SWE-MuseumsVerwaltung wird ohne Daten iniziiert!");
        }

        // ueberpruefe ob ein default Pfad uebergeben wurde
        // wenn nein waehle  "RessourcenPfad + /default" als default Pfad
        String defaultPfadName;
        if (cmd.hasOption("d")) {
            defaultPfadName = cmd.getOptionValue("d");
        } else {
            defaultPfadName = ressourcenPfad + (ressourcenPfad.endsWith("/") ? "default" : "/default");
        }
        AppLogger.getInstance().info("Default Ressourcen unter " + defaultPfadName + " verwendet");


        // lade die default Elemente
        try {
            MuseumsManager.ladeDefaultElemente(defaultPfadName);
            AppLogger.getInstance().info("default Elemente geladen");
        } catch (Exception e) {
            e.printStackTrace();
            AppLogger.getInstance().error("default Elemente konnten nicht geladen werden");
        }


        // test code

        // TODO kann man das hinbekommen, dass der PropertyManager uns alle benötigten Strings läd und das uns die Übersetzung einfach machen könnte?
        /*try {
            PropertyManager proppy = new PropertyManager(null, SWEMuseumsVerwaltung.class, "/ger.property");
            System.out.println(proppy.getProperty("ayyyy"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
