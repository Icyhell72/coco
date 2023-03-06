package com.example.pidevcocomarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor /*constructeur vide*/
@AllArgsConstructor /*constructeur avec tous les attributs*/
@ToString
@Builder
public class Produit implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    private String description;
    private float price;
    private String color;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Boutique> boutiques;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Stock> stocks;
    @ManyToOne
    @ToString.Exclude
    Categorie categorie;

}
