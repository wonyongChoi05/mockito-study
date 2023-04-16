package split.gettingstart.captor;

public class CaptorOuterFixture {

    private CaptorInnerFixture captorInnerFixture;

    public CaptorOuterFixture(final CaptorInnerFixture captorInnerFixture) {
        this.captorInnerFixture = captorInnerFixture;
    }

    public void show(final String name, final int id, final boolean isExist) {
        final CaptorMessageFixture captorMessageFixture = new CaptorMessageFixture(name, id, isExist);
        captorInnerFixture.print(captorMessageFixture);
    }
}
