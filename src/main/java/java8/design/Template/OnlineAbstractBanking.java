package java8.design.Template;

import java8.User;

public abstract class OnlineAbstractBanking {

    abstract void makeCustomerHappy(User user);

    public void processCustomer(int id){
        User user=new User(id,"test");
        makeCustomerHappy(user);
    }

}
