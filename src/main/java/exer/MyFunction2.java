package exer;

/**
 * @author abs
 * @Date 2019/8/19 - 0:15
 */
@FunctionalInterface
public interface MyFunction2<T, R> {
    public R getValue(T t1,T t2);
}
