package split.mockmethods;

import java.util.AbstractList;

public class MockList extends AbstractList<String> {

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
