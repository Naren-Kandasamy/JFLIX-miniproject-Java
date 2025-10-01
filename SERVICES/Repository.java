package SERVICES;

import CORE.Identifiable;
import java.util.List;

public interface Repository <T extends Identifiable>{
    public void add(T item);
    public boolean remove(String id) throws Exception;
    public T findByID(String id) throws Exception;
    public boolean exists(String id);
    public List<T> getAll();
}


