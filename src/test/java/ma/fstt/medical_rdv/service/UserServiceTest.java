package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.Patient;
import ma.fstt.medical_rdv.entity.User;
import ma.fstt.medical_rdv.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setNom("Ahmed Benali");
        patient.setEmail("ahmed.benali@test.com");
        patient.setMotDePasse("test1234");
    }

    @Test
    void existsByEmail_quandEmailExiste_devraitRetournerTrue() {
        // Arrange
        when(userRepository.findByEmail("ahmed.benali@test.com"))
                .thenReturn(Optional.of(patient));

        // Act
        boolean resultat = userService.existsByEmail("ahmed.benali@test.com");

        // Assert
        assertThat(resultat).isTrue();
    }

    @Test
    void existsByEmail_quandEmailNexistePas_devraitRetournerFalse() {
        // Arrange
        when(userRepository.findByEmail("inconnu@test.com"))
                .thenReturn(Optional.empty());

        // Act
        boolean resultat = userService.existsByEmail("inconnu@test.com");

        // Assert
        assertThat(resultat).isFalse();
    }

    @Test
    void save_devraitRetournerUserSauvegarde() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(patient);

        // Act
        User resultat = userService.save(patient);

        // Assert
        assertThat(resultat.getNom()).isEqualTo("Ahmed Benali");
        verify(userRepository, times(1)).save(patient);
    }

    @Test
    void findByEmail_devraitRetournerUtilisateurCorrect() {
        // Arrange
        when(userRepository.findByEmail("ahmed.benali@test.com"))
                .thenReturn(Optional.of(patient));

        // Act
        Optional<User> resultat = userService.findByEmail("ahmed.benali@test.com");

        // Assert
        assertThat(resultat).isPresent();
        assertThat(resultat.get().getEmail()).isEqualTo("ahmed.benali@test.com");
    }
}