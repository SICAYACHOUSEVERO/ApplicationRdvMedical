package ma.fstt.medical_rdv.controller;

import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.entity.User;
import ma.fstt.medical_rdv.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/utilisateurs")
    public List<User> gererUtilisateurs() {
        return adminService.gererUtilisateur();
    }

    @DeleteMapping("/utilisateurs/{id}")
    public ResponseEntity<?> supprimerUtilisateur(@PathVariable Long id) {
        adminService.supprimerUtilisateur(id);
        return ResponseEntity.ok(Map.of("message", "Utilisateur supprimé."));
    }

    @GetMapping("/medecins")
    public List<Medecin> gererMedecins() {
        return adminService.gererMedecin();
    }

    @PostMapping("/medecins")
    public Medecin ajouterMedecin(@RequestBody Medecin medecin) {
        return adminService.ajouterMedecin(medecin);
    }

    @DeleteMapping("/medecins/{id}")
    public ResponseEntity<?> supprimerMedecin(@PathVariable Long id) {
        adminService.supprimerMedecin(id);
        return ResponseEntity.ok(Map.of("message", "Médecin supprimé."));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> consulterStat() {
        long total = adminService.consulterStat();
        return ResponseEntity.ok(Map.of("totalUtilisateurs", total));
    }
}