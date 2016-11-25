package own.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

/**
 * Created by ahernandez on 11/23/16.
 */
public class MapToPojoTest {

    @Test
    public void mapToPojoTest() {
        final Map<String, String> map = new LinkedHashMap<String, String>(4);
        map.put("id", "5");
        //map.put("name", "Bob");
        map.put("age", "23");
        map.put("savings", "2500.39");
        map.put("nameId", "foo");
        //map.put("extra", "foo");

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final MyPojo pojo = mapper.convertValue(map, MyPojo.class);

        System.out.println(pojo);
    }
}

class MyPojo {

    private String id;
    private String name;
    private Integer age;
    private Double savings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }

    public String getNameId() {
        return name+id;
    }

    @Override
    public String toString() {
        return String.format(
                "MyPojo[id = %s, name = %s, age = %s, savings = %s]", getId(),
                getName(), getAge(), getSavings());
    }
}