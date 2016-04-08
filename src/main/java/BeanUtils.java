import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther HuJiaqi
 * @createTime 2016年04月07日 14时04分
 * @discription bean工具类
 */
public class BeanUtils {

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月07日 14时12分
     * @discription byte数组转object，建议处理异常
     */
    public static Object bytesToObject(byte[] b) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();
            bis.close();
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("byte数组转object异常", e);
        }
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月07日 14时14分
     * @discription object转byte数组，建议处理异常
     */
    public static byte[] objectToBytes(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] b = bos.toByteArray();
            oos.close();
            bos.close();
            return b;
        } catch (Exception e) {
            throw new RuntimeException("object转byte数组异常", e);
        }
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月07日 14时41分
     * @discription map转object，建议处理异常
     */
    public static Object mapToObject(Class c, Map map) {
        try {
            BeanInfo bi = Introspector.getBeanInfo(c);
            Object obj = c.newInstance();
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                if (map.containsKey(name)) {
                    pd.getWriteMethod().invoke(obj, map.get(name));
                }
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("map转object异常" + e);
        }
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月07日 14时47分
     * @discription object转map，建议处理异常
     */
    public static Map<String, String> objectToMap(Object obj) {
        try {
            Class c = obj.getClass();
            Map<String, String> map = new HashMap<String, String>();
            BeanInfo bi = Introspector.getBeanInfo(c);
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String key = pd.getName();
                if (!key.equals("class")) {
                    Object result = pd.getReadMethod().invoke(obj);
                    if (result != null) {
                        map.put(key, result.toString());
                    } else {
                        map.put(key, "");
                    }
                }
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException("object转map异常" + e);
        }
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月07日 14时24分
     * @discription 修饰filed的自定义注解，用于复制bean
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface CopyName {
        @SuppressWarnings("unused")
        String value() default "";
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月07日 16时33分
     * @discription 这个方法默认子类不会有与父类相同的field，没有处理这种情况
     */
    public static void beanCopyAll(Object from, Object to) {
        if (from == null || to == null) {
            return;
        }
        Class c = from.getClass();
        Field[] fields = c.getDeclaredFields();
        c = c.getSuperclass();
        while (c != null) {
            Field[] temp = c.getDeclaredFields();
            fields = ArrayUtils.merge(fields, temp);
            c = c.getSuperclass();
        }
        if (fields != null && fields.length > 0) {
            for (Field fromField : fields) {
                fromField.setAccessible(true);
                String fieldName;
                if (fromField.isAnnotationPresent(CopyName.class)) {
                    CopyName adjuster = fromField.getAnnotation(CopyName.class);
                    fieldName = !"".equals(adjuster.value()) ? adjuster.value() : fromField.getName();
                } else {
                    fieldName = fromField.getName();
                }
                try {
                    Object obj = fromField.get(from);
                    Field toField = null;
                    c = to.getClass();
                    while (toField == null && c != null) {
                        try {
                            toField = c.getDeclaredField(fieldName);
                            break;
                        } catch (Exception e) {
                            c = c.getSuperclass();
                            // 如果异常了，接着找父类
                        }
                    }
                    if (toField != null) {
                        toField.setAccessible(true);
                        toField.set(to, obj);
                    }
                } catch (Exception e) {
                    // 什么都不干，异常就当跳过了
                }
            }
        }
    }

}



