package com.humber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.models.Patient;



@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {

}
