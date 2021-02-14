package base;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author dinglingfeng
 * @version 2021/2/14
 * @since JDK1.7
 */
public abstract class MethodRockonTime {

    public abstract void coreMethod();

    public void run(){
        StopWatch watch = new StopWatch();
        watch.start();
        coreMethod();
        watch.stop();
        System.out.println(String.format("花费时间: %s ms",watch.getTime()));
    }
}
