package com.com.unicauca.Parcial3.domain.service;

import com.com.unicauca.Parcial3.domain.Repository.IUserRepository;
import com.com.unicauca.Parcial3.domain.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    /**
     * @param idUser User id to register
     */
    public void registerUser(int idUser){
        if(userRepository.findById(idUser) != null){
            return;
        }
        userRepository.addUser(new User(idUser));
    }
}
