package com.backend_jenkins.Backend.Service;

import com.backend_jenkins.Backend.Model.Absence;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.AbsenceRepository;
import com.backend_jenkins.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private UserRepository userRepository;

    public void addAbsence(Absence absence){
        absenceRepository.save(absence);
    }

    public List<Absence> GetAbsenceUser(int id){
        Optional <User> opuser = userRepository.findById(id);
        return absenceRepository.findAbsencesByUser(opuser.get());
    }

    public List<Absence> GetAbsenceManager(int id){
        return absenceRepository.getAbsencesByManagerId(id);
    }


}
