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

    // Ajouter ou modifier un patient
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // Rechercher un patient par ID
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    // Afficher tous les patients
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    // Supprimer un patient
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    // Consulter les rendez-vous d'un patient
    public List<RendezVous> consulterMesRendezVous(Patient patient) {
        return rendezVousRepository.findByPatient(patient);
    }
}