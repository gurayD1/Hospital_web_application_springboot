package com.humber.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.models.Appointment;
import com.humber.models.AvailableAppointment;
import com.humber.repository.AvailableAppointmentRepository;

@Service
public class AvailableAppointmentService {
	
	@Autowired
	private AvailableAppointmentRepository availableAppointmentRepository;
	
	
	public List getAllAvailableAppointment() {
		List<AvailableAppointment> appointment = new ArrayList<AvailableAppointment>();
		appointment = (List<AvailableAppointment>) availableAppointmentRepository.findAll();

		return appointment;

	}
	
	public void addAvailableAppointment(AvailableAppointment appointment) {
		availableAppointmentRepository.save(appointment);
	}
	
	public void deleteAvailableAppointment(int id ) {
		availableAppointmentRepository.deleteById(id);
	}
	

}
