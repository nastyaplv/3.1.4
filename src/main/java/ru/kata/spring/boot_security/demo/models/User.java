package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "users")
public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
//    private int id;
    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private int age;

    public User() {

    }

    public User(String username, String password, String name, String lastName, int age){
        this.username=username;
        this.password=password;
        this.name = name;
        this.lastname = lastName;
        this.age = age;
    }


    //public User(int id, String username, String password, String name, String lastName, int age) {
//        //this.id = id;
//        this.username=username;
//        this.password=password;
//        this.name = name;
//        this.lastname = lastName;
//        this.age = age;
//    }
//    public User(String name, String lastName, int age) {
//        this.name = name;
//        this.lastname = lastName;
//        this.age = age;
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}