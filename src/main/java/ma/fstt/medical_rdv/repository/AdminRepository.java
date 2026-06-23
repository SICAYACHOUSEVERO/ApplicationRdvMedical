package ma.fstt.medical_rdv.repository;

import ma.fstt.medical_rdv.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}