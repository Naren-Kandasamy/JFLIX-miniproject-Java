package CORE;

import java.io.Serializable;

public interface Identifiable extends Serializable {
    String getID(); // Any class having ID will use inherently implement and use this function
}

