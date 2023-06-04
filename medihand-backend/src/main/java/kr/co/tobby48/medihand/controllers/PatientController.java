package kr.co.tobby48.medihand.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tobby48.medihand.models.Patient;
import kr.co.tobby48.medihand.repository.PatientRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;

	@GetMapping("/{userId}")
	public List<Patient> getPatientByUsers(@PathVariable Long userId) {
		return patientRepository.findByUser_Id(userId);
	}
	
}
