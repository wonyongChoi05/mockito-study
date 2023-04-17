package domain;

import java.util.Objects;

/**
 * @author 베베
 * @version 1.0.0
 * @since by 베베 on 2023. 04. 16.
 */
public class Name {

    private final String name;

    public Name(String name) {
        this.name = name;
    }

    public boolean isEqualsName(String name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
