package com.com.unicauca.Parcial3.domain.Repository;

import com.com.unicauca.Parcial3.domain.Stock;
import com.com.unicauca.Parcial3.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose David Chilito Cometa, Valentina Fernandez Guerrero, Juan Esteban Yepez Rodriguez
 */

@Repository
public class UserRepository implements IUserRepository {
    private ArrayList<User> users;

    public UserRepository() {
        users = new ArrayList<User>();
        initDB();
    }

    @Override
    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Initialize the database with some users
     */
    public void initDB (){
        User user1 = new User(1);
        user1.addStock(new Stock(1000, "GOOG", 1000, 1000, 900, 1100));
        users.add(user1);

        User user2 = new User(2);
        user2.addStock(new Stock(1001, "AAPL", 200, 200, 180, 220));
        users.add(user2);

        User user3 = new User(3);
        user3.addStock(new Stock(1002, "MSFT", 300, 300, 270, 330));
        users.add(user3);

        User user4 = new User(4);
        user4.addStock(new Stock(1003, "AMZN", 400, 400, 360, 440));
        users.add(user4);

        User user5 = new User(5);
        user5.addStock(new Stock(1004, "FB", 500, 500, 450, 550));
        users.add(user5);
    }
}
