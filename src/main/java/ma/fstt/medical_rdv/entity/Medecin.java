package ma.fstt.medical_rdv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("MEDECIN")
public class Medecin extends User {

    private String specialite;

    private String cabinet;

    @JsonIgnoreProperties({"patient", "medecin"})
    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVousList;

    @JsonIgnoreProperties({"medecin", "rendezVous"})
    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    private List<Disponibilite> disponibiliteList;

    // Getters et Setters

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public List<RendezVous> getRendezVousList() {
        return rendezVousList;
    }

    public void setRendezVousList(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    public List<Disponibilite> getDisponibiliteList() {
        return disponibiliteList;
    }

    public void setDisponibiliteList(List<Disponibilite> disponibiliteList) {
        this.disponibiliteList = disponibiliteList;
    }
}