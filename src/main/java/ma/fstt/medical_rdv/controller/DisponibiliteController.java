package ma.fstt.medical_rdv.controller;

import ma.fstt.medical_rdv.entity.Disponibilite;
import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.service.DisponibiliteService;
import ma.fstt.medical_rdv.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/disponibilites")
@CrossOrigin(origins = "http://localhost:5173")
public class DisponibiliteController {

    @Autowired
    private DisponibiliteService disponibiliteService;

    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public List<Disponibilite> getAll() {
        return disponibiliteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return disponibiliteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<?> getByMedecin(@PathVariable Long medecinId) {
        return medecinService.findById(medecinId)
                .map(medecin -> ResponseEntity.ok(disponibiliteService.verifierDisponibilite(medecin)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Disponibilite ajouter(@RequestBody Disponibilite disponibilite) {
        return disponibiliteService.ajouterDisponibilite(disponibilite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id, @RequestBody Disponibilite disponibilite) {
        disponibilite.setId(id);
        Disponibilite updated = disponibiliteService.modifierDisponibilite(disponibilite);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/estDisponible")
    public ResponseEntity<?> estDisponible(@PathVariable Long id) {
        boolean disponible = disponibiliteService.estDisponible(id);
        return ResponseEntity.ok(Map.of("disponible", disponible));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        disponibiliteService.supprimerDisponibilite(id);
        return ResponseEntity.ok(Map.of("message", "Disponibilité supprimée."));
    }
}