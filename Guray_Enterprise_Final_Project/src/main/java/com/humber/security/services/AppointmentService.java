package com.humber.security.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.models.Appointment;
import com.humber.models.Patient;
import com.humber.repository.AppointmentRepository;
import com.humber.repository.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PatientRepository patientRepository;

	public List getAllAppointment() {
		List<Appointment> appointment = new ArrayList<Appointment>();
		appointment = (List<Appointment>) appointmentRepository.findAll();
		return appointment;

	}

	public Appointment getAppointment(int id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		return appointment.orElseGet(null);

	}


	public Set<Appointment> getuserAppointment(String username) {
		Optional<Patient> patient = patientRepository.findById(username);

		if (patient.isPresent()) {
			Patient p = patient.get();
			return p.getAppointments();

		}

		Set<Appointment> appointments = new HashSet<>();
		return appointments;

	}

	public void addAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	public void updateAppointment(String id, Appointment appointment) {

		appointmentRepository.save(appointment);

	}

	public void deleteAppointment(int id) {
		appointmentRepository.deleteById(id);
	}

}
