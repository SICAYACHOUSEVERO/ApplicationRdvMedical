package ma.fstt.medical_rdv.controller;

import ma.fstt.medical_rdv.entity.Patient;
import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        return patientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/rendezvous")
    public ResponseEntity<?> getRendezVousByPatient(@PathVariable Long id) {
        return patientService.findById(id)
                .map(patient -> ResponseEntity.ok(patientService.consulterRendezVous(patient)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/rendezvous")
    public ResponseEntity<?> prendreRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        return patientService.findById(id)
                .map(patient -> {
                    rendezVous.setPatient(patient);
                    RendezVous saved = patientService.prendreRendezVous(rendezVous);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/rendezvous/{rendezVousId}")
    public ResponseEntity<?> annulerRendezVous(@PathVariable Long rendezVousId) {
        patientService.annulerRendezVous(rendezVousId);
        return ResponseEntity.ok(Map.of("message", "Rendez-vous annulé."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Patient supprimé."));
    }
}