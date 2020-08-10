package Museum.ObjectManagement;

import Museum.Person.Admin;
import Museum.Person.Foerderer;
import Museum.Person.HR;
import Museum.Person.User;

import java.util.HashSet;

public class PersonenManager implements ElementManager {

    private HashSet<Foerderer> foerderer;
    private HashSet<Admin> admins;
    private HashSet<User> user;
    private HashSet<HR> hr;

    public PersonenManager(Admin admin, HashSet<Foerderer> foerderer, HashSet<Admin> admins, HashSet<User> user, HashSet<HR> hr) {
        this.foerderer = foerderer;
        this.admins = admins;
        this.admins.add(admin);
        this.user = user;
        this.hr = hr;
    }

    public PersonenManager(Admin admin) {
        this(admin, new HashSet<Foerderer>(), new HashSet<Admin>(), new HashSet<User>(), new HashSet<HR>());
    }

    public boolean contains(Object element) {
        return false;
    }

    public boolean persist(Object element) {
        return false;
    }

    public boolean remove(Object element) {
        return false;
    }

    public Object find(int id) {
        return null;
    }

    public boolean edit(Object element) {
        return false;
    }
}
