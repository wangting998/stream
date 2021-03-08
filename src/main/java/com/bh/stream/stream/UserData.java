package com.bh.stream.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供用于测试的数据
 */
public class UserData {
    public static List<User>getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(1001, "马化腾", 34, 6000.38));
        list.add(new User(1002, "马云", 12, 9876.12));
        list.add(new User(1003, "刘强东", 33, 3000.82));
        list.add(new User(1004, "雷军", 26, 7657.37));
        list.add(new User(1005, "李彦宏", 34, 5555.32));
        list.add(new User(1006, "比尔盖茨", 42, 9500.43));
        list.add(new User(1007, "任正非", 26, 4333.32));
        list.add(new User(1008, "扎克伯格", 10, 2500.32));
        return list;
    }
}
