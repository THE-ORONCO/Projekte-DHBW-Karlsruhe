/**
 * @author Theo Roncoletta - TINF18B1
 * @version 1.0
 */
package Museum.ObjectManagement;

public enum CSVSeparationLevel {
    LEVEL1(";", ";"),
    LEVEL2(",", ","),
    LEVEL3("\\|", "|"),
    LEVEL4("_", "_"),
    LEVEL5("~", "~");

    private final String writeSeparator;
    private final String readSeparator;

    /**
     * Das CSVSeparationLevel dient dazu um einen Standart für die CSV-Separation innerhalb der CSV-Dateien zu definieren.
     *
     * @param readSeparator  CSV-Separator der beim lesen der Datei verwendet wird
     * @param writeSeparator CSV-Separator der beim schreiben der Datei verwendet wird
     */
    CSVSeparationLevel(String readSeparator, String writeSeparator) {
        this.readSeparator = readSeparator;
        this.writeSeparator = writeSeparator;
    }

    /**
     * Diese Methode ist dazu da um den Seperator zum lesen einer CSV-Datei zu bekommen wenn sie mit String.split() gespalten werden soll
     *
     * @return den Separator der gebraucht wird um eine CSV-Datei einzulesen
     */
    public String rSeparator() {
        return this.readSeparator;
    }

    /**
     * Diese Methode ist dazu da um den Seperator zum schreiben von CSV-Daten zu bekommen wenn sie mit String.join() zusammengefuegt werden sollen
     *
     * @return den Separator der gebraucht wird um eine CSV-Datei zu schreiben
     */
    public String wSeparator() {
        return this.writeSeparator;
    }
}
