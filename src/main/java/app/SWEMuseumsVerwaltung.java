package app;

import Museum.Bild.Bild;
import Museum.Exponat.Epoche;
import Museum.Exponat.Exponat;
import Museum.MuseumsElement;
import Museum.ObjectManagement.MuseumsManager;
import Museum.Person.Foerderer;
import Museum.Person.Mitarbeiter;
import Museum.Raum.Raum;
import de.dhbwka.swe.utils.util.AppLogger;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
public class SWEMuseumsVerwaltung {
    public static void main(String[] args) {

        System.out.println(System.getProperty("!!!!Bevor Sie sich wundern wo das GUI ist schauen sie bitte in die README.txt!!!!"));

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
            ressourcenPfad = ressourcenPfad.endsWith("/") ? ressourcenPfad : ressourcenPfad + "/";
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
            defaultPfadName = ressourcenPfad + "default";
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

        //Alle Dateien im gegebenen Ressourcenpfad importieren
        Class<? extends MuseumsElement>[] classes = new Class[]{Bild.class, Epoche.class, Exponat.class, Foerderer.class, Mitarbeiter.class, Raum.class};
        for (Class<? extends MuseumsElement> c : classes) {
            try {
                ArrayList<MuseumsElement> elemente = MuseumsManager.importieren(c, ressourcenPfad + c.getSimpleName() + ".csv", false);
                AppLogger.getInstance().info("es wurden " + elemente.size() + " Elemente vom Typ " + c.getSimpleName() + " importiert.");
            } catch (Exception e) {
                AppLogger.getInstance().error("beim Laden der gegebenen Daten ist etwas schiefgelaufen");
                e.printStackTrace();
            }
        }


        // dieser Codeabschnitt hier wurde nur eingefügt um für sie das Testen der Funktionalität zu erleichtern
        MuseumsManager.getExponatM().clear(); // erstmal alle geladenen Exponate löschen

        Class c = Exponat.class;
        Scanner userInputOhneGUI = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Pfad zu der zu importierenden Datei mit den Exponaten ein.");
        String pfadZuExponat = userInputOhneGUI.nextLine();
        ArrayList<MuseumsElement> importierteExponate = new ArrayList<>();
        try {
            importierteExponate = MuseumsManager.importieren(c, pfadZuExponat, false);
            System.out.println("Folgende Exponate wurden importiert:");
            int counter = 1;
            for (MuseumsElement e : importierteExponate) {
                System.out.println("======================================================");
                System.out.println("Exponat " + counter + ":");
                System.out.println(e.toString());
            }
        } catch (Exception e) {
            System.out.println("ups da ist etwas schiefgegangen beim Importieren");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Bitte geben Sie den Pfad zum Export-Ort ein. (Ohne Dateiname!)");
        String exportPfad = userInputOhneGUI.nextLine();
        System.out.println("Bitte geben Sie den Dateiname ein.");
        String dateiName = userInputOhneGUI.nextLine();
        try {
            MuseumsManager.exportieren(c, exportPfad, dateiName, false);
            System.out.println();
        } catch (Exception e) {
            System.out.println("ups da ist etwas schiefgegangen beim Exportieren");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("nun wird diese Datei reimportiert und mit dem alten Import verglichen");
        String reimportPfad = exportPfad.endsWith("/") ? exportPfad + dateiName : exportPfad + "/" + dateiName;
        MuseumsManager.getExponatM().clear(); // die alten Daten müssen gelöscht werden sonst wird garnichts importiert
        ArrayList<MuseumsElement> reimportierteExponate = new ArrayList<>();
        try {
            reimportierteExponate = MuseumsManager.importieren(Exponat.class, reimportPfad, false);
            System.out.println("es wurden gleich viele Exponate importiert: " + (importierteExponate.size() == reimportierteExponate.size()));
            for (int i = 0; i < reimportierteExponate.size(); i++) {
                boolean gleich = reimportierteExponate.get(i).equals(importierteExponate.get(i));
                System.out.println("Exponat " + i + " ist unverändert nach dem reimport?: " + gleich);
            }
        } catch (Exception e) {
            System.out.println("ups da ist etwas schiefgegangen beim Importieren");
            e.printStackTrace();
        }

        System.out.println("leider kann das Programm nicht mehr aktuell. Für mehr Informationenen schauen sie in das Abgabedokument in Kapitel 7 oder der README.txt nach.");
    }
}
