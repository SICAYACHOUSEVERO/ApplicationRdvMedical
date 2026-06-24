package ma.fstt.medical_rdv.controller;

import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rendezvous")
@CrossOrigin(origins = "http://localhost:5173")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping
    public List<RendezVous> getAll() {
        return rendezVousService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return rendezVousService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/confirmer")
    public ResponseEntity<?> confirmer(@PathVariable Long id) {
        try {
            RendezVous rdv = rendezVousService.confirmerRendezVous(id);
            return ResponseEntity.ok(rdv);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<?> refuser(@PathVariable Long id) {
        try {
            RendezVous rdv = rendezVousService.refuserRendezVous(id);
            return ResponseEntity.ok(rdv);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        rendezVous.setId(id);
        RendezVous updated = rendezVousService.modifierRendezVous(rendezVous);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> annuler(@PathVariable Long id) {
        rendezVousService.annulerRendezVous(id);
        return ResponseEntity.ok(Map.of("message", "Rendez-vous annulé."));
    }
}