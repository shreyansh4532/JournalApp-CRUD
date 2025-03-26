package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    //  CREATE
    public void saveUser(User user) {
        userRepo.save(user);
    }

    //  GET ALL
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    //  GET ONE
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    //  DELETE
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
