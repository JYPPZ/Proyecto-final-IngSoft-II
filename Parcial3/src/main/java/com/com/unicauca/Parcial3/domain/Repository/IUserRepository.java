package com.com.unicauca.Parcial3.domain.Repository;

import com.com.unicauca.Parcial3.domain.User;

import java.util.List;

/**
 * @author Jose David Chilito Cometa, Valentina Fernandez Guerrero, Juan Esteban Yepez Rodriguez
 */

public interface IUserRepository {
    /**
     * @param id User id to search
     * @return User if found, null if not found
     */
    User findById(int id);

    /**
     * @return List of all users
     */
    List<User> findAll();

    /**
     * @param user User to add
     */
    void addUser(User user);
}
