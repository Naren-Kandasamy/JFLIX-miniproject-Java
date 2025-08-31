package USER;

import java.util.List;
import CONTENT.Genre;

public interface Profile {
    String getUsername();
    List<Genre> getPreferences();
}

