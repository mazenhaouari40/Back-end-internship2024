package com.backend_jenkins.Backend.Service;

import com.backend_jenkins.Backend.Jwt.ImageUtil;
import com.backend_jenkins.Backend.Model.Absence;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.AbsenceRepository;
import com.backend_jenkins.Backend.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
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
        List<Absence> list_absence = absenceRepository.getAbsencesByManagerId(id);
        for (Absence absence : list_absence) {
            byte[] image = ImageUtil.decompressImage(  absence.getUser().getImageuser());
            String base64Image = Base64.encodeBase64String(image);
          absence.getUser().setImageencode_64bits(base64Image);
        }
        return list_absence;
    }

    public void Update_absence(int id_absence,String status){
        Optional<Absence> a = absenceRepository.findById(id_absence);
        if (a.isPresent()){
            Absence ab = a.get();
            if (status != null){
                ab.setStatus(status);
            }
            absenceRepository.save(ab);
        }


    }

}
