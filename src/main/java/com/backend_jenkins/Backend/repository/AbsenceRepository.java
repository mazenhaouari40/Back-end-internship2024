package com.backend_jenkins.Backend.repository;

import com.backend_jenkins.Backend.Model.Absence;
import com.backend_jenkins.Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence,Integer> {

    @Query("SELECT a FROM Absence a JOIN a.user u WHERE u.manager.id = :id")
    List<Absence> getAbsencesByManagerId(@Param("id") int id);

    List<Absence> findAbsencesByUser(@Param("user") User user);
    void deleteAbsencesByUser(User user);
}
