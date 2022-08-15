package com.humber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.models.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

}
