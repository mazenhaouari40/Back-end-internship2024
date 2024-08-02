package com.backend_jenkins.Backend.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Absence")
public class Absence {
    @Id
    @Column(name="Id_absence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_absence;

    @Column(name= "Type")
    private String type;
    // Maladie , Congé Paye, Mariage

    @Column(name="debut_d")
    private Date debut_d;

    @Column(name="fin_d")
    private Date fin_d;

    @ManyToOne
    private User user;

    @Column(name="statut")
    private String status = "demande";

    public Absence(String type, Date debut_d, Date fin_d, User user) {
        this.type = type;
        this.debut_d = debut_d;
        this.fin_d = fin_d;
        this.user = user;
    }

    public Absence() {

    }

    public int getId_absence() {
        return id_absence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDebut_d() {
        return debut_d;
    }

    public void setDebut_d(Date debut_d) {
        this.debut_d = debut_d;
    }

    public Date getFin_d() {
        return fin_d;
    }

    public void setFin_d(Date fin_d) {
        this.fin_d = fin_d;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
