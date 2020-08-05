package Museum.ObjectManagement;

public interface ElementManager {

    public boolean contains(Object element);
    public boolean persist(Object element);
    public boolean remove(Object element);
    public Object find(int id);
    public boolean edit(Object element);
}
