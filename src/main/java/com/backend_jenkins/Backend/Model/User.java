package com.backend_jenkins.Backend.Model;

import jakarta.persistence.*;


@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name= "email")
    private String email;
    @Column(name= "num_tel")
    private int num_tel;
    @Column(name="nom")
    private String nom;
    public User(int num_tel, String email,String nom) {
        this.num_tel = num_tel;
        this.email = email;
        this.nom = nom;
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
}
