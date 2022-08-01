package ru.auroramusic.race.data;

import java.util.Objects;

public class User {
    private Integer Age;

    public User(Integer age) {
        Age = age;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "Age=" + Age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Age.equals(user.Age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Age);
    }
}
