package USER;

import java.io.Serializable;
import java.util.List;
import CONTENT.Genre;
import CORE.Identifiable;

public interface Profile extends Identifiable, Serializable {
    String getUsername();
    List<Genre> getPreferences();
}