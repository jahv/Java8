package own.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Created by ahernandez on 11/11/16.
 */
public class LambdaExpTest {

    @Test
    public void weirdLambdaExpr() {

        RoutingKeyGenerator r = new RoutingKeyGenerator() {
            @Override
            public String determineRoutingKey(List<Integer> message) {
                return "Simple test";
            }
        };
        String hola = r.determineRoutingKey(new ArrayList<Integer>());
        System.out.println(hola);

        RoutingKeyGenerator r1 = new RoutingKeyGeneratorConfiguration().routingKeyGenerator();
        System.out.println(r1.determineRoutingKey(new ArrayList<>()));

    }
}

interface RoutingKeyGenerator {
    String determineRoutingKey(List<Integer> message);
}

class RoutingKeyGeneratorConfiguration {

    public RoutingKeyGenerator routingKeyGenerator() {
        return list -> {
            if (SharedObj.get("a") != null) {
                return "a existe";
            } else {
                String result = "existe ";
                if (SharedObj.get("b") != null) {
                    result += "b";
                } else {
                    result += "c";
                }
                return result;
            }
        };
    }

}

class SharedObj {
    static Map<String, String> shared = new HashMap<>();

    static {
        shared.put("b", "a");
    }

    static void put(String k, String v) {
        System.out.println("## Put [" + k + " = " + v + "]");
        shared.put(k,v);
    }

    static String get(String s) {
        System.out.println("## Get [" + s + "]");
        return shared.get(s);
    }
}