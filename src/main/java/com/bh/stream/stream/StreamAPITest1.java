package com.bh.stream.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 * 1-筛选与切片 filter
 * 2-映射 map
 * 3-排序 sorted
 */
public class StreamAPITest1 {
    /**
     * 1-筛选与切片 filter
     */
    @Test
    public void test1(){
        List<User> list = UserData.getUsers();
//        filter(Predicate P)---接收 Lambda , 从流中排除某些元素。
        Stream<User> stream = list.stream();
        //练习：查询员工表中薪资大于7000的员工信息
        stream.filter(u -> u.getUserSalary() > 7000).forEach(System.out::println);
        System.out.println();

//        limit(n)---截断流 ，使其元素不超过给定数量。
        //生成一个新的stream  list.stream()
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();

//        skip(n)---跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补.
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();

//        distinct()-筛选 ，通过流所生成元素的 hashCode() 和 equals()去除重复元素
        list.add(new User(1010,"刘强东",40,8000));
        list.add(new User(1010,"刘强东",41,8000));
        list.add(new User(1010,"刘强东",40,8000));
        list.add(new User(1010,"刘强东",40,8000));
        list.add(new User(1010,"刘强东",40,8000));

//        System.out.println(list);
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 2-映射 map
     */
    @Test
    public void test2(){
        //map(Function f)---接收一个函数作为参数，将元素转换成其他形式或提取信息。该函数会被应用到每个元素上，井将其映射成一个新的元素.
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        // 练习1:获取员工姓名长度大于3的员工的姓名。
        List<User> users = UserData.getUsers();
        Stream<String> namesStream= users.stream().map(User :: getUserName);
        namesStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println();

        //练习2:
        Stream<Stream<Character>> streamStream= list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s ->{
            s.forEach(System.out::println);
        });

        //flatMap(Function f)----接收一个函数作为参数，将流中的每个值都换成另一个流,然后把所有流连接成一个流.
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    /**
     * 将字符串中的多个字符构成的集合转换为对应的Stream的实例
     * @param str
     * @return
     */
    public static Stream<Character> fromStringToStream(String str){ //aa
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test3() {
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        //list1.add(list2);
        list1.addAll(list2);
        System.out.println(list1);
    }

    /**
     * 3-排序 sorted
     */
    @Test
    public void test4(){
//        sorted ----自然顺序
        List<Integer> list= Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);
        //抛异常，原因：User没有实现Comparable接口
//        List<User> users= UserData.getUsers();
//        users.stream().sorted().forEach(System.out::println);

//        sorted(Comparator com)---定制排序
        List<User> users= UserData.getUsers();
        users.stream().sorted( (u1,u2) -> {
            int ageValue = Integer.compare(u1.getUserAge(),u2.getUserAge());
            if (ageValue !=0){
                return ageValue;
            }else {
                //-Double 从大到小
                return -Double.compare(u1.getUserAge(),u2.getUserAge());
            }
        }).forEach(System.out::println);
    }
}
