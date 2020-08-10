package Museum.ObjectManagement;

public enum ImportableElements {
    EXPONAT(15),
    PERSON(4),
    RAUM(4),
    BILD(3);

    public int nrOfArguments;
    ImportableElements(int nrOfArguments) {
        this.nrOfArguments = nrOfArguments;
    }
}
