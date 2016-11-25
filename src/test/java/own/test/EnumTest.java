package own.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ahernandez on 11/2/16.
 */
public class EnumTest {

    enum TestValues {
        TEST1, TEST2;

        static Map<String, TestValues> values  = new HashMap<>();
        static {
            values.put("CLOSED", TestValues.TEST1);
            values.put("FREE_READ", TestValues.TEST2);
        }

        public static Map<String, TestValues> getValues() {
            return values;
        }
    }

    @Test
    public void testEnum() {
        System.out.println(TestValues.TEST1.name());

        TestValues enumTestValues = TestValues.valueOf("TEST1");
        System.out.println(enumTestValues);

        System.out.println(TestValues.getValues());
    }
}
