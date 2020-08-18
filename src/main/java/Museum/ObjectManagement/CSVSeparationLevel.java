package Museum.ObjectManagement;

public enum CSVSeparationLevel {
    LEVEL1(';'),
    LEVEL2(','),
    LEVEL3('|'),
    LEVEL4('_'),
    LEVEL5('~');

    private char separator;
    CSVSeparationLevel(char separator) {
        this.separator = separator;
    }

    public char toChar(){
        return this.separator;
    }

    @Override
    public String toString() {
        return String.valueOf(this.separator);
    }
}
