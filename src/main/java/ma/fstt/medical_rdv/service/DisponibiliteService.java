package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.Disponibilite;
import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.repository.DisponibiliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibiliteService {

    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    public Disponibilite ajouterDisponibilite(Disponibilite disponibilite) {
        return disponibiliteRepository.save(disponibilite);
    }

    public Disponibilite modifierDisponibilite(Disponibilite disponibilite) {
        return disponibiliteRepository.save(disponibilite);
    }

    public void supprimerDisponibilite(Long id) {
        disponibiliteRepository.deleteById(id);
    }

    public boolean estDisponible(Long id) {
        Optional<Disponibilite> dispo = disponibiliteRepository.findById(id);
        return dispo.isPresent() && dispo.get().getRendezVous() == null;
    }

    public List<Disponibilite> verifierDisponibilite(Medecin medecin) {
        return disponibiliteRepository.findByMedecin(medecin);
    }

    public Optional<Disponibilite> findById(Long id) {
        return disponibiliteRepository.findById(id);
    }

    public List<Disponibilite> findAll() {
        return disponibiliteRepository.findAll();
    }
}