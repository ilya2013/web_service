package ru.auroramusic.race.data;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void HashMapAndHashCodeTest() {
        BiConsumer<User, Integer> showMap = (a, b) -> {
            System.out.println(a + ": " +b);
        };
        Map<User, Integer> users = new HashMap<>();
        User user1 = new User(1);
        User user3 = new User(3);
        users.put(user1, user1.getAge());
        users.put(user3, user3.getAge());
        users.forEach(showMap);
        user1.setAge(2);
        users.forEach(showMap);
        showMap.accept(user1, users.get(user1));
        showMap.accept(user3, users.get(user3));
    }

    @Test
    public void StreamReduceTest() {
        Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6);
        Optional<Integer> result = numbersStream.reduce((x, y) -> x * y);
        System.out.println(result.get()); // 720

        Stream<String> wordsStream = Stream.of("мама", "мыла", "раму");
        String sentence = wordsStream.reduce("Результат:", (x,y)->x + " " + y);
        System.out.println(sentence); // Результат: мама мыла раму

        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000),
                new Phone("LG G 4", 32000));

        int sum = phoneStream.reduce(0,
                (x, y)-> {
                    if(y.getPrice() < 50000)
                        return x + y.getPrice();
                    else
                        return x;
                },
                Integer::min);

        System.out.println(sum); // 117000

        phoneStream = Stream.of(new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000),
                new Phone("LG G 4", 32000));
        sum = phoneStream.parallel().reduce(0,
                (x, y)-> {
                    if(y.getPrice() < 50000)
                        return x + y.getPrice();
                    else
                        return x;
                },
                Integer::min);

        System.out.println(sum); // 0
    }

//    @Test
//    public void ParallelStreamWordCountTest() {
//        Stream<Integer> numbersStream = Stream.of(1, 1, 3, 4, 5, 6);
//        Map<Integer, Integer> countMap = new HashMap<>();
//        countMap = numbersStream.parallel()
//                .reduce(new HashMap<Integer, Integer>(),
//                 (x, y)-> {
//
//                },
//                Integer::min);
//    }
}

class Phone{

    private String name;
    private int price;

    public Phone(String name, int price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}