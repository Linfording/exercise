package base;

import org.apache.commons.lang3.time.StopWatch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dinglingfeng
 * @version 2020/7/17
 * @since JDK1.7
 */
public class ProxyHandler implements InvocationHandler {

    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        method.invoke(object, args);
        watch.stop();
        System.out.println("Time Elapsed: " + watch.getTime());
        return null;
    }
}
