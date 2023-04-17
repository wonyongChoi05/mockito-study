package gettingstart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockInjectFixture {

    private Map<String, String> firstMap;  //final 일시 테스트가 되지 않음 ( mock이 주입되어야 하는데 불변이기 때문에 )
    private Map<String, String> secondMap;  //final 일시 테스트가 되지 않음 ( mock이 주입되어야 하는데 불변이기 때문에 )
    private List<String> list;

    public MockInjectFixture() {
        this.firstMap = new HashMap<>();
        this.secondMap = new HashMap<>();
        this.list = new ArrayList<>();
    }

    public MockInjectFixture(Map<String, String> map) {
        this();
        this.firstMap = map;
    }

    public void addToFirst(final String field, final String value) {
        firstMap.put(field, value);
    }

    public String getFromFirst(final String value) {
        return firstMap.get(value);
    }

    public void addToSecond(final String field, final String value) {
        secondMap.put(field, value);
    }

    public String getFromSecond(final String value) {
        return secondMap.get(value);
    }

    public Map<String, String> getFirstMap() {
        return firstMap;
    }

    public Map<String, String> getSecondMap() {
        return secondMap;
    }

    public List<String> getList() {
        return list;
    }
}
