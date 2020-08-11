package Museum.ObjectManagement;

public interface ElementManager { // TODO es wäre potenziell besser wenn wir die hier nicht hätten

    public boolean contains(Object element);
    public boolean persist(Object element);
    public boolean remove(Object element);
    public Object find(String id);
    public boolean edit(Object element);
}
