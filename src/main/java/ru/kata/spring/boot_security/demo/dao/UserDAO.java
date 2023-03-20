package ru.kata.spring.boot_security.demo.dao;





import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
public interface UserDAO {
    public List<User> getAllUsers();

    //public User show(int id);
    public User show(String username);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);
}
