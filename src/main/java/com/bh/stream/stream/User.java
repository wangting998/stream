package com.bh.stream.stream;

import java.util.Objects;

public class User {
    private int userId;
    private String userName;
    private int userAge;
    private double userSalary;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                userAge == user.userAge &&
                Double.compare(user.userSalary, userSalary) == 0 &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userAge, userSalary);
    }

    public User(int userId, String userName, int userAge, double userSalary) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userSalary = userSalary;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public double getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(double userSalary) {
        this.userSalary = userSalary;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSalary=" + userSalary +
                '}';
    }
}
