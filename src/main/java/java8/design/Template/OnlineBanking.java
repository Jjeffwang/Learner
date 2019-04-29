package java8.design.Template;

import java8.User;

import java.util.function.Consumer;

public class OnlineBanking {
    private User user;

    public OnlineBanking(User user) {
        this.user = user;
    }

    public void processCustomer(Consumer<User> userConsumer) {
        userConsumer.accept(user);
    }

    private static void print(User user) {
        System.out.println(user.getId() + " " + user.getName());
    }


    public static void main(String[] args) {
        User user = new User(100, "demo");
        OnlineBanking onlineBanking = new OnlineBanking(user);
        onlineBanking.processCustomer(user1 -> print(user));
    }
}
