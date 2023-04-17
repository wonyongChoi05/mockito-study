package domain;

public class Car {

    private final Name name;

    public Car(Name name) {
        this.name = name;
    }

    public boolean checkCar(String name) {
        return this.name.isEqualsName(name);
    }

    public Name getName() {
        return name;
    }
}
