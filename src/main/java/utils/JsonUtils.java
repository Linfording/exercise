package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author dinglingfeng
 * @version 2021/2/14
 * @since JDK1.7
 */
public class JsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            //gson = new Gson();
            //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,
            // 显示形式是"key":null，
            // 而直接new出来的就没有"key":null的
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }

    //无参的私有构造方法
    private JsonUtils() {
    }

    public static String toJsonString(Object obj) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(obj);
        }
        return gsonString;
    }

}
