package com.bh.stream.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试Stream的终止操作
 * 1-匹配与查找find,match
 * 2-聚合max,min,count
 * 3-遍历foreach
 * 4-归约 reduce
 * 5-收集 collect
 *      统计 summarizing,counting,averaging
 *      分组 groupingBy,partitionBy
 *      归约 reduce
 *      归集 toList,toSet,toMap
 */
public class StreamAPITest2 {
    /**
     * 1-匹配与查找find,match
     */
    @Test
    public void test1() {
        List<User> users = UserData.getUsers();
//        allMatch(Predicate p)检查是否匹配所有元素。
//           练习:是否所有的员工的年龄都大于18
        boolean allMatch= users.stream().allMatch(u -> u.getUserAge() > 18);
        System.out.println(allMatch);  //false

        //anyMatch(Predicate p)-----检查是否至少匹配一个元素。
        //  练习:是否存在员工的工资大于10000
        boolean anyMatch= users.stream().anyMatch(u ->u.getUserSalary() > 10000);
        System.out.println(anyMatch);  //false

        //noneMatch(Predicate p)-----检查是否没有匹配的元素。
        //  练习:是否存在员工姓"雷”
        boolean noneMatch = users.stream().noneMatch(u ->u.getUserName().startsWith("雷"));
        System.out.println(noneMatch);   //false

        //findFirst   返回第一个元素
        Optional<User> user = users.stream().findFirst();
        System.out.println(user);

        //findAny   返回当前流中的任意元素
        Optional<User> user1= users.parallelStream().findAny();
        System.out.println(user1);

    }

    /**
     * 2-聚合max,min,count
     * 3-遍历foreach
     */
    @Test
    public void test2() {
        List<User> users = UserData.getUsers();
        //count    返回流中元素的总个数
        long count= users.stream().filter(u -> u.getUserSalary() > 5000).count();
        System.out.println(count);
        //max(Comparator c)   返回流中最大值
        //练习:返回最高的工资:
        Stream<Double> salaryStream= users.stream().map(u -> u.getUserSalary());
        Optional<Double> maxSalary= salaryStream.max(Double :: compare);
        System.out.println(maxSalary);

        //min(Comparator c)   返回流中最小值
        //练习:返回最低工资的员工
        Optional<User> user= users.stream().min((u1,u2) -> Double.compare(u1.getUserSalary(),u2.getUserSalary()));
        System.out.println(user);

        //forEach(Consumer c)   内部迭代
        users.stream().forEach(System.out::println);

        //使用集合的遍历操作
        users.forEach(System.out::println);
    }

    /**
     * 4-归约 reduce
     */
    @Test
    public void test3() {
//        reduce(T identity, BinaryOperator) ---可以将流中元素反复结合起来，得到一个值。返回T
//        练习1:计算1-10的自然数之和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum= list.stream().reduce(0,Integer::sum);
        System.out.println(sum);

//        reduce(BinaryOperator) ---可以将流中元素反复结合起来，得到一个值。返回Optional<T>
//        练习2:计算公司所有员工工资的总和
       List<User> users = UserData.getUsers();
       Stream<Double> salaryStream= users.stream().map(User :: getUserSalary);

//       Optional<Double> sumMoney= salaryStream.reduce(Double :: sum);
        Optional<Double> sumMoney= salaryStream.reduce((d1,d2) -> d1 + d2);

        System.out.println(sumMoney);
    }

    /**
     * 5-收集 collect
     *      统计 summarizing,counting,averaging
     *      分组 groupingBy,partitionBy
     *      归约 reduce
     *      归集 toList,toSet,toMap
     */
    @Test
    public void test4() {
        //collect(Collector c)---将流转换为其他形式。接收一个Collector接口的实现，用于给
        //练习1:查找工资大于6000的员工，结果返回为一个List或Set

        List<User> users = UserData.getUsers();
        List<User> userList= users.stream().filter(u -> u.getUserSalary() > 6000).collect(Collectors.toList());
        userList.forEach(System.out::println);


        Set<User> userSet = users.stream().filter(u -> u.getUserSalary() > 6000).collect(Collectors.toSet());
        userSet.forEach(System.out::println);
    }
}
