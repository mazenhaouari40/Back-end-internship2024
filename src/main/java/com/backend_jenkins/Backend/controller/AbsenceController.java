package com.backend_jenkins.Backend.controller;


import com.backend_jenkins.Backend.Model.Absence;
import com.backend_jenkins.Backend.Model.ResponseLoginUser;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.Service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/absence")
@CrossOrigin(origins = "http://localhost:4200")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @PostMapping
        public ResponseEntity<?> addAbsence(@RequestBody Absence absence){
        absenceService.addAbsence(absence);
        return ResponseEntity.ok(new ResponseLoginUser("Absence added successfuly",true));

    }

    @GetMapping("{id}")
    public List<Absence> getListAbsenceByUserId(@PathVariable Integer id){
        return absenceService.GetAbsenceUser(id);
    }

    @GetMapping("/manager/{id}")
    public List<Absence> getListAbsenceByManagerId(@PathVariable Integer id){
    return absenceService.GetAbsenceManager(id);
    }


}
