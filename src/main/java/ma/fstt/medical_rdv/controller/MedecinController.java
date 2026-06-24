package ma.fstt.medical_rdv.controller;

import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medecins")
@CrossOrigin(origins = "http://localhost:5173")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public List<Medecin> getAllMedecins() {
        return medecinService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedecinById(@PathVariable Long id) {
        return medecinService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialite/{specialite}")
    public List<Medecin> getMedecinsBySpecialite(@PathVariable String specialite) {
        return medecinService.findBySpecialite(specialite);
    }

    @GetMapping("/{id}/planning")
    public ResponseEntity<?> getPlanning(@PathVariable Long id) {
        return medecinService.findById(id)
                .map(medecin -> ResponseEntity.ok(medecinService.consulterPlanning(medecin)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Médecin supprimé."));
    }
}