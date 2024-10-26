package tn.esprit.tpfoyer.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUniversite;

    String nomUniversite;

    String adresse;

    Long idFoyer;

}
