package USER;

import java.util.List;
import CONTENT.Genre;
import CORE.Identifiable;

public interface Profile extends Identifiable{
    String getUsername();
    List<Genre> getPreferences();
}