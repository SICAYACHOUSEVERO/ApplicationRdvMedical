package ma.fstt.medical_rdv.repository;

import ma.fstt.medical_rdv.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}