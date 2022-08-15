package com.humber.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.models.Appointment;
import com.humber.models.AvailableAppointment;
import com.humber.models.Patient;
import com.humber.security.services.AppointmentService;
import com.humber.security.services.AvailableAppointmentService;
import com.humber.security.services.PatientService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	PatientService patientService;

	@Autowired
	AvailableAppointmentService availableAppointmentService;

	@GetMapping("/")
	public String allAccess() {
		return "Welcome";
	}

	// Available Appointment Management //
	@GetMapping("/allavailableappointments")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public List getAllAvailableAppointments() {
		return availableAppointmentService.getAllAvailableAppointment();
	}

	@PostMapping("/availableappointment")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public void addAllAvailableAppointment(@RequestBody AvailableAppointment appointment) {
		availableAppointmentService.addAvailableAppointment(appointment);

	}

	@DeleteMapping("/availableappointment/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAllAvailableAppointment(@PathVariable("id") int id) {
		availableAppointmentService.deleteAvailableAppointment(id);

	}

	// Patient Management //

	@GetMapping("/allpatient")
	@PreAuthorize("hasRole('ADMIN')")
	public List getAllPatient() {
		return patientService.getAllPatient();
	}

	@GetMapping("patient/{id}")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public Patient getPatient(@PathVariable("id") String patientId) {
		try {

			return patientService.getPatient(patientId);

		} catch (Exception e) {
		}
		Patient patient = new Patient();
		return patient;
	}

	@PostMapping("/patient")
	@PreAuthorize("hasRole('ADMIN')")
	public Patient addPatient(@RequestBody Patient patient) {
		patientService.addPatient(patient);
		return patientService.getPatient(patient.getId());
	}

	@PutMapping("/patient/{id}")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public void updatePatient(@PathVariable("id") String patientId, @RequestBody Patient patient) {
		Patient patientFound = new Patient();
		try {
			patientFound = patientService.getPatient(patientId);
		} catch (Exception e) {

			if (patientFound == null) {
				patientService.addPatient(patient);
			} else {
				patientService.updatePatient(patientId, patient);
			}
		}

		if (patientFound == null) {
			patientService.addPatient(patient);
		} else {
			patientService.updatePatient(patientId, patient);
		}

	}

	@DeleteMapping("/patient/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deletePatient(@PathVariable("id") String patientId) {
		patientService.deletePatient(patientId);

	}

	// Appointment Management //

	@GetMapping("/allappointment")
	@PreAuthorize("hasRole('ADMIN')")
	public List getAllAppointment() {
		return appointmentService.getAllAppointment();
	}

	@GetMapping("appointment/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Appointment getAppointment(@PathVariable("id") int appointmentId) {
		try {
			return appointmentService.getAppointment(appointmentId);
		} catch (Exception e) {
		}

		Appointment appointment = new Appointment();
		return appointment;
	}

	@PostMapping("/appointment")

	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public void addAppointment(@RequestBody Appointment appointment) {

		Patient patient = patientService.getPatient(appointment.getUserName());

		appointment.setPatient(patient);

		appointmentService.addAppointment(appointment);

		availableAppointmentService.deleteAvailableAppointment(appointment.getAvailableAppointmentId());

	}

	@GetMapping("user-appointments/{id}")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public Set<Appointment> getUserAppointments(@PathVariable("id") String userName) {
		return appointmentService.getuserAppointment(userName);
	}

	@PutMapping("/appointment/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void updateAppointment(@PathVariable("id") String appointmentId, @RequestBody Appointment appointment) {
		appointmentService.updateAppointment(appointmentId, appointment);
	}

	@DeleteMapping("/appointment/{id}")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public void deleteAppointment(@PathVariable("id") int appointmentId) {

		AvailableAppointment availableappointment = new AvailableAppointment();
		Appointment appointment = appointmentService.getAppointment(appointmentId);

		availableappointment.setAppointmentDate(appointment.getAppointmentDate());
		availableappointment.setAppointmentTime(appointment.getAppointmentTime());
		availableappointment.setDoctorName(appointment.getDoctorName());
		availableappointment.setDepartment(appointment.getDepartment());

		availableAppointmentService.addAvailableAppointment(availableappointment);
		appointmentService.deleteAppointment(appointmentId);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

}
