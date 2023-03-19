package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
@Repository
@Transactional
public class UserServiceImpl implements UserService {

    public UserDAO userDAO;
    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }


    public List<User> getAllUsers() {
            return userDAO.getAllUsers();
        }

        public User show(int id) {
            return userDAO.show(id);
        }

        public void save(User user) {
            userDAO.save(user);
        }

        public void update(int id, User updatedUser) {
            userDAO.update(id, updatedUser);
        }

        public void delete(int id) {
            userDAO.delete(id);
        }
}
