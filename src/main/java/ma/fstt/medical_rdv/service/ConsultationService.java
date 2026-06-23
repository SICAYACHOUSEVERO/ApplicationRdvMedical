package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.Consultation;
import ma.fstt.medical_rdv.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public Consultation enregistrerDiagnostic(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public Consultation ajouterPrescription(Long id, String prescription) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation introuvable avec id : " + id));
        consultation.setPrescription(prescription);
        return consultationRepository.save(consultation);
    }

    public Consultation ajouterNotes(Long id, String notes) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation introuvable avec id : " + id));
        consultation.setNotes(notes);
        return consultationRepository.save(consultation);
    }

    public Consultation modifierConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public Optional<Consultation> findById(Long id) {
        return consultationRepository.findById(id);
    }

    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }
}