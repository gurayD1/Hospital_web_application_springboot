package com.humber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.models.AvailableAppointment;

@Repository
public interface AvailableAppointmentRepository extends CrudRepository<AvailableAppointment, Integer> {

}
