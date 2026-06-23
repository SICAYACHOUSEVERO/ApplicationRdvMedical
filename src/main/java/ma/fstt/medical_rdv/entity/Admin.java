package ma.fstt.medical_rdv.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    // Pas d'attribut propre selon le diagramme UML
    // Les méthodes gererUtilisateur(), gererMedecin(), consulterStat()
    // seront implémentées dans AdminService / AdminController plus tard

}