package com.bh.stream.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1.Stream关注的是对数据的运算，与CPU打交道
 *      集合关注的是数据的存储，与内存打交道
 * 2.①Stream自己不会存储元素。
 *   ②Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream.
 *   ③Sream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 3.Stream 执行流程
 * ① Stream的实例化
 * ② 一系列的中间操作（过滤、映射..）
 * ③ 终止操作
 *
 * 4.说明：
 * 4.1 一个中间操作链，对数据源的数据进行处理
 * 4.2
 */
public class StreamAPITest {
    /**
     * forEach遍历
     */
    @Test
    public void testForEach() {
        // 获取一个Stream流
        Stream<String> stream = Stream.of("迪丽热巴", "古力娜扎", "高圆圆", "李沁");
        // 使用Stream流中的方法forEach对Stream流中的数据进行遍历
        stream.forEach((String name) -> {
            System.out.println(name);
        });
    }

    @Test
    public void testForEach1() {
        List<User> users = UserData.getUsers();

        //forEach(Consumer c)   内部迭代
        users.stream().forEach(System.out::println);

        //使用集合的遍历操作
        users.forEach(System.out::println);
    }

    /**
     * ① Stream的实例化
     */
    //创建Stream方式一：通过集合
    @Test
    public void test1(){
        List<User> users = UserData.getUsers();
        //default Stream<E> stream() :返回一个顺序流
        Stream<User> stream = users.stream();

        //default Stream<E> paralleStream() :返回一个并行流
        Stream<User> parallelStream= users.parallelStream();
    }

    //创建Stream方式二：通过数组
    @Test
    public void test2(){
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};

        //调用Arrays类的静态方法：static <T> Stream<T> stream(T[] array):返回一个流
        /**
         * 重载形式，能够处理对应基本类型的数组:
         * public static IntStream stream(int] array)
         * public static LongStream stream(long[] array)
         * public static DoubleStream stream(double[] array)
         */
        IntStream stream =  Arrays.stream(arr);

        // 能够处理对应基本类型的数组
        User u1 = new User(1011, "张三", 34, 6000.38);
        User u2 = new User(1012, "李四", 18, 3000.38);
        User[] arr1 = new User[]{u1,u2};
        Stream<User> stream1 = Arrays.stream(arr1);
    }

    // 创建Stream方式三：通过Stream的of
    @Test
    public void test3() {
        // 得到的是一个包装类的对象, 不是int类型
        //可以说Stream就是来操作一些容器的
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    // 创建Stream方式四：创建无限流
    @Test
    public void test4() {
        // 迭代
        // static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
        // 遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        // 生成
        // static <T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);;
    }

}
