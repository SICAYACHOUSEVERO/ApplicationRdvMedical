package ma.fstt.medical_rdv.repository;

import ma.fstt.medical_rdv.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    List<Medecin> findBySpecialite(String specialite);

}