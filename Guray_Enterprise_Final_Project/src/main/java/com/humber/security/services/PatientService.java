package com.humber.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.models.Appointment;
import com.humber.models.Patient;
import com.humber.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public List getAllPatient() {
		List<Patient> patient = new ArrayList<Patient>();
		patient = (List<Patient>) patientRepository.findAll();

		return patient;

	}

	public Patient getPatient(String id) {
		Optional<Patient> patient = patientRepository.findById(id);
		return patient.orElseGet(null);

	}

	public void addPatient(Patient patient) {
		patientRepository.save(patient);
	}

	public void updatePatient(String id, Patient patient) {

		patientRepository.save(patient);

	}

	public void deletePatient(String id) {
		patientRepository.deleteById(id);
	}

}
