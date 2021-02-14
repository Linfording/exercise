package base;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.time.StopWatch;

import java.lang.reflect.Method;

/**
 * @author dinglingfeng
 * @version 2020/7/17
 * @since JDK1.7
 */
public class CGlibProxyFactory implements MethodInterceptor {
    // 代理的目标对象
    private Object targetObject;

    public Object createProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        // 该类用于生成代理对象
        Enhancer enhancer = new Enhancer();
        // 设置目标类为代理对象的父类
        enhancer.setSuperclass(this.targetObject.getClass());
        // 设置回调用对象本身
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        final Object result = proxy.invoke(targetObject, args);
        watch.stop();
        System.out.println(String.format("花费时间: %s ms",watch.getTime()));
        return result;
    }
}
