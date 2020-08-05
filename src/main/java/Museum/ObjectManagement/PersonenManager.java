package Museum.ObjectManagement;

public class PersonenManager implements ElementManager{
    public boolean contains(Object element) {
        return false;
    }

    public boolean persist(Object element) {
        return false;
    }

    public boolean remove(Object element) {
        return false;
    }

    public Object find(String id) {
        return null;
    }

    public boolean edit(Object element) {
        return false;
    }
}
