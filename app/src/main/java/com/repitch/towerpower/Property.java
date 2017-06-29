package com.repitch.towerpower;

import java.util.List;
import java.util.Map;

/**
 * Created by repitch on 30.06.17.
 */
public class Property {
    private String name;
    private String value;

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static String propertiesToString(List<Property> properties) {
        StringBuilder sb = new StringBuilder();
        for (Property property: properties) {
            sb.append(String.format("%s: %s\n", property.getName(), property.getValue()));
        }
        return sb.toString();
    }
}
