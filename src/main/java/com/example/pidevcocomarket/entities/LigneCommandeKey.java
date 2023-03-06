package com.example.pidevcocomarket.entities;


import javax.persistence.*;



@Embeddable
public class LigneCommandeKey implements java.io.Serializable{
    @Column(name = "commande_id")
    private Integer commandeId;
    @Column(name = "produit_id")
    private Integer produitId;
}
