package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.repository.MedecinRepository;
import ma.fstt.medical_rdv.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    public Medecin save(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public List<RendezVous> consulterPlanning(Medecin medecin) {
        return rendezVousRepository.findByMedecin(medecin);
    }

    public List<Medecin> findBySpecialite(String specialite) {
        return medecinRepository.findBySpecialite(specialite);
    }

    public Optional<Medecin> findById(Long id) {
        return medecinRepository.findById(id);
    }

    public List<Medecin> findAll() {
        return medecinRepository.findAll();
    }

    public void deleteById(Long id) {
        medecinRepository.deleteById(id);
    }
}