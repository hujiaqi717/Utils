import java.lang.reflect.Array;

/**
 * @auther HuJiaqi
 * @createTime 2016年04月07日 15时51分
 * @discription 数组工具类
 */
public class ArrayUtils {

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 13时10分
     * @discription 数组合并，仅支持相同类型数组合并
     */
    public static <T> T[] merge(T[] array1, T[] array2) {
        if ((array1 == null) && (array2 == null))
            return null;
        if ((array2 == null) || (array2.length == 0))
            return array1;
        if ((array1 == null) || (array1.length == 0))
            return array2;
        Class c = array1.getClass().getComponentType();
        Object[] array = (Object[]) Array.newInstance(c, array1.length + array2.length);
        System.arraycopy(array1, 0, array, 0, array1.length);
        System.arraycopy(array2, 0, array, array1.length, array2.length);
        @SuppressWarnings("unchecked")
        T[] result = (T[]) array;
        return result;
    }

}
