package com.example.pidevcocomarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor /*constructeur vide*/
@AllArgsConstructor /*constructeur avec tous les attributs*/
@ToString
public class Commande implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private float amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToOne(mappedBy="commande")
    @ToString.Exclude
    private Livraison livraison;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="commande")
    @JsonIgnore
    @ToString.Exclude
    private Set<SAV> savs;
    @OneToMany(mappedBy="commande",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LigneCommande> lignesCommande;
    @Enumerated(EnumType.STRING)
    private TypePa type_pay;
    private boolean Pay_confirmed;
    private LocalDate datePay;
    private String codePay;

}
