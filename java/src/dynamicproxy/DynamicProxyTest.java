package dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.Map;

public class DynamicProxyTest {
    public static void main(String[] args) {
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(),
                new Class[]{Map.class},
                new DynamicInvocationHandler());

        proxyInstance.put("hello", "world");

        System.out.println("proxyInstance.size() = " + proxyInstance.size());
        System.out.println("proxyInstance.get(\"hello\") = " + proxyInstance.get("hello"));
    }
}
