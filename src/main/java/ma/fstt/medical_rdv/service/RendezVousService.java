package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.Patient;
import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    public RendezVous creerRendezVous(RendezVous rendezVous) {
    rendezVous.setStatut("EN_ATTENTE");
    return rendezVousRepository.save(rendezVous);
    }

    public RendezVous confirmerRendezVous(Long id) {
    RendezVous rdv = rendezVousRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec id : " + id));
    rdv.setStatut("CONFIRME");
    return rendezVousRepository.save(rdv);
    }

    public RendezVous refuserRendezVous(Long id) {
    RendezVous rdv = rendezVousRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec id : " + id));
    rdv.setStatut("REFUSE");
    return rendezVousRepository.save(rdv);
    }

    public void annulerRendezVous(Long id) {
        rendezVousRepository.deleteById(id);
    }

    public RendezVous modifierRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    public Optional<RendezVous> findById(Long id) {
        return rendezVousRepository.findById(id);
    }

    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }

    public List<RendezVous> findByPatient(Patient patient) {
        return rendezVousRepository.findByPatient(patient);
    }

    public List<RendezVous> findByMedecin(Medecin medecin) {
        return rendezVousRepository.findByMedecin(medecin);
    }
}