package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.Medecin;
import ma.fstt.medical_rdv.entity.User;
import ma.fstt.medical_rdv.repository.MedecinRepository;
import ma.fstt.medical_rdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    public List<User> gererUtilisateur() {
        return userRepository.findAll();
    }

    public void supprimerUtilisateur(Long id) {
        userRepository.deleteById(id);
    }

    public List<Medecin> gererMedecin() {
        return medecinRepository.findAll();
    }

    public Medecin ajouterMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public void supprimerMedecin(Long id) {
        medecinRepository.deleteById(id);
    }

    public long consulterStat() {
        return userRepository.count();
    }
}