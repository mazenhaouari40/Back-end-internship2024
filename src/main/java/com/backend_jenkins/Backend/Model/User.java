package com.backend_jenkins.Backend.Model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name= "email")
    private String email;

    @Column(name= "num_tel")
    private int num_tel;

    @Column(name="Password")
    private String password;

    @Column(name="Role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "user")
    private List<Absence> absences;

    public User(int num_tel, String email,String nom,String password,String role,User manager) {
        this.num_tel = num_tel;
        this.email = email;
        this.nom = nom;
        this.password = password;
        this.role = role;
        this.manager = manager;
    }
    public User(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }


}
