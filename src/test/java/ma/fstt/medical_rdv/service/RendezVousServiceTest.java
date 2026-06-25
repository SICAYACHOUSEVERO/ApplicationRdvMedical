package ma.fstt.medical_rdv.service;

import ma.fstt.medical_rdv.entity.RendezVous;
import ma.fstt.medical_rdv.repository.RendezVousRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RendezVousServiceTest {

    @Mock
    private RendezVousRepository rendezVousRepository;

    @InjectMocks
    private RendezVousService rendezVousService;

    private RendezVous rendezVous;

    @BeforeEach
    void setUp() {
        rendezVous = new RendezVous();
        rendezVous.setId(1L);
        rendezVous.setStatut(false);
        rendezVous.setMotif("Consultation de routine");
    }

    @Test
    void creerRendezVous_devraitForcerStatutAFalse() {
        // Arrange
        rendezVous.setStatut(true); // on tente de forcer "confirmé" à la création
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rendezVous);

        // Act
        RendezVous resultat = rendezVousService.creerRendezVous(rendezVous);

        // Assert
        assertThat(resultat.isStatut()).isFalse();
        verify(rendezVousRepository, times(1)).save(rendezVous);
    }

    @Test
    void confirmerRendezVous_devraitMettreStatutATrue() {
        // Arrange
        when(rendezVousRepository.findById(1L)).thenReturn(Optional.of(rendezVous));
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rendezVous);

        // Act
        RendezVous resultat = rendezVousService.confirmerRendezVous(1L);

        // Assert
        assertThat(resultat.isStatut()).isTrue();
        verify(rendezVousRepository).save(rendezVous);
    }

    @Test
    void confirmerRendezVous_avecIdInexistant_devraitLeverException() {
        // Arrange
        when(rendezVousRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            rendezVousService.confirmerRendezVous(999L);
        });
    }

    @Test
    void refuserRendezVous_devraitMettreStatutAFalse() {
        // Arrange
        rendezVous.setStatut(true);
        when(rendezVousRepository.findById(1L)).thenReturn(Optional.of(rendezVous));
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rendezVous);

        // Act
        RendezVous resultat = rendezVousService.refuserRendezVous(1L);

        // Assert
        assertThat(resultat.isStatut()).isFalse();
    }

    @Test
    void annulerRendezVous_devraitAppelerDeleteById() {
        // Act
        rendezVousService.annulerRendezVous(1L);

        // Assert
        verify(rendezVousRepository, times(1)).deleteById(1L);
    }
}