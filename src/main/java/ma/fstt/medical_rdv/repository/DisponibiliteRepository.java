package ma.fstt.medical_rdv.repository;

import ma.fstt.medical_rdv.entity.Disponibilite;
import ma.fstt.medical_rdv.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {

    List<Disponibilite> findByMedecin(Medecin medecin);

}