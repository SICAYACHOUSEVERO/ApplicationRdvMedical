package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.User;
import ma.fstt.medical_rdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    // Ajouter ou modifier un utilisateur
    public User save(User user) {
        return userRepository.save(user);
    }

    // Rechercher un utilisateur par ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Afficher tous les utilisateurs
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Supprimer un utilisateur
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}