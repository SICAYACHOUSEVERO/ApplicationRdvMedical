package ma.fstt.medical_rdv.repository;

import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.entity.Patient;
import ma.fstt.medical_rdv.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByPatient(Patient patient);

    List<RendezVous> findByMedecin(Medecin medecin);

    List<RendezVous> findByStatut(boolean statut);

}