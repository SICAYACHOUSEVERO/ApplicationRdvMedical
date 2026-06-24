package ma.fstt.medical_rdv.controller;

import ma.fstt.medical_rdv.dto.LoginRequest;
import ma.fstt.medical_rdv.dto.RegisterRequest;
import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.entity.Patient;
import ma.fstt.medical_rdv.entity.User;
import ma.fstt.medical_rdv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Cet email est déjà utilisé."));
        }

        User user;

        if ("MEDECIN".equalsIgnoreCase(request.getRole())) {
            Medecin medecin = new Medecin();
            medecin.setSpecialite(request.getSpecialite());
            medecin.setCabinet(request.getCabinet());
            user = medecin;
        } else {
            Patient patient = new Patient();
            if (request.getDateNaissance() != null && !request.getDateNaissance().isEmpty()) {
                patient.setDateNaissance(LocalDate.parse(request.getDateNaissance()));
            }
            patient.setAdresse(request.getAdresse());
            user = patient;
        }

        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setMotDePasse(request.getMotDePasse());
        user.setTelephone(request.getTelephone());

        User saved = userService.save(user);

        return ResponseEntity.ok(Map.of(
                "message", "Inscription réussie !",
                "id", saved.getId(),
                "email", saved.getEmail()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<User> userOpt = userService.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("message", "Email ou mot de passe incorrect."));
        }

        User user = userOpt.get();

        if (!user.getMotDePasse().equals(request.getMotDePasse())) {
            return ResponseEntity.status(401).body(Map.of("message", "Email ou mot de passe incorrect."));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Connexion réussie !",
                "id", user.getId(),
                "nom", user.getNom(),
                "email", user.getEmail(),
                "role", user.getClass().getSimpleName().toUpperCase()
        ));
    }
}

