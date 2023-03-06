package com.example.pidevcocomarket.services;

import com.example.pidevcocomarket.Repositories.BoutiqueRepository;
import com.example.pidevcocomarket.Repositories.ProduitRepository;
import com.example.pidevcocomarket.entities.Boutique;
import com.example.pidevcocomarket.entities.Produit;

import com.example.pidevcocomarket.interfaces.IProduitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor

public class ProduitService implements IProduitService {
    ProduitRepository produitRepository;
    BoutiqueRepository boutiqueRepository;
    @Override
    public Produit ajouterProduit(Produit produit, MultipartFile file) throws IOException {
        //set l'image du produit to bytes
       // produit.setImage(file.getBytes());
        return produitRepository.save(produit);
    }

    @Override
    public Produit modifierProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> afficherListeProduit() {
        return produitRepository.findAll();
    }

    @Override
    public void deleteProduit(int id) {
        produitRepository.deleteById(id);

    }

    @Override
    public Produit retrieveProduit(int id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public void ProduitAffectBoutique(Integer idProduit, Integer idBoutique) {
        Produit produit =produitRepository.findById(idProduit).orElse(null);
        Boutique boutique=boutiqueRepository.findById(idBoutique).orElse(null);
        produit.setBoutiques((Set<Boutique>) boutique);
        produitRepository.save(produit);

    }
    @Override
    public List<String> getBasicColors(){
        List<String> basicColors=new ArrayList<>();
        basicColors.add("#FF0000");//rouge
        basicColors.add("#00FF00");//vert
        basicColors.add("#0000FF");//bleu
        basicColors.add("#FFFF00");//jaune
        basicColors.add("#FFA500");//orange
        basicColors.add("#800080");//violet
        return basicColors;


    }
}
