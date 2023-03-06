package com.example.pidevcocomarket.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor /*constructeur vide*/
@AllArgsConstructor /*constructeur avec tous les attributs*/
@ToString
@Builder
public class Catalog implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Double promotion;
    private String editionDate;
    private String link;
}
