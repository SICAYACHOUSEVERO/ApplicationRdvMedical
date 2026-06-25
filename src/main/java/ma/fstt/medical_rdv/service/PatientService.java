
package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.Patient;
import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.repository.PatientRepository;
import ma.fstt.medical_rdv.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public RendezVous prendreRendezVous(RendezVous rendezVous) {
        rendezVous.setStatut(false); // en attente de confirmation par le médecin
        return rendezVousRepository.save(rendezVous);
    }

    public void annulerRendezVous(Long rendezVousId) {
    RendezVous rdv = rendezVousRepository.findById(rendezVousId)
            .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec id : " + rendezVousId));

    // Libérer la disponibilité associée avant de supprimer le rendez-vous
    if (rdv.getDisponibilite() != null) {
        rdv.setDisponibilite(null);
        rendezVousRepository.save(rdv);
    }

    rendezVousRepository.deleteById(rendezVousId);
}

    public List<RendezVous> consulterRendezVous(Patient patient) {
        return rendezVousRepository.findByPatient(patient);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}