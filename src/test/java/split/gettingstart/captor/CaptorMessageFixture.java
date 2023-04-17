package split.gettingstart.captor;

public class CaptorMessageFixture {

    private final String name;
    private final int number;
    private final boolean isExist;

    public CaptorMessageFixture(final String name, final int number, final boolean isExist) {
        this.name = name;
        this.number = number;
        this.isExist = isExist;
    }

    public String getName() {
        return name;
    }
}
