package ma.fstt.medical_rdv.controller;

import ma.fstt.medical_rdv.entity.Consultation;
import ma.fstt.medical_rdv.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin(origins = "http://localhost:5173")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public List<Consultation> getAll() {
        return consultationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return consultationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Consultation creer(@RequestBody Consultation consultation) {
        return consultationService.enregistrerDiagnostic(consultation);
    }

    @PutMapping("/{id}/prescription")
    public ResponseEntity<?> ajouterPrescription(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            Consultation consultation = consultationService.ajouterPrescription(id, body.get("prescription"));
            return ResponseEntity.ok(consultation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/notes")
    public ResponseEntity<?> ajouterNotes(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            Consultation consultation = consultationService.ajouterNotes(id, body.get("notes"));
            return ResponseEntity.ok(consultation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id, @RequestBody Consultation consultation) {
        consultation.setId(id);
        Consultation updated = consultationService.modifierConsultation(consultation);
        return ResponseEntity.ok(updated);
    }
}