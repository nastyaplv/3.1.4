package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposotiries.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

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

        // Для UserDetailsService
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
// Переводим нашего юзера в юзер, которого понимает Spring Security
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
