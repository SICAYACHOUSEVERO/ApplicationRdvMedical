package ma.fstt.medical_rdv.repository;

import ma.fstt.medical_rdv.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}