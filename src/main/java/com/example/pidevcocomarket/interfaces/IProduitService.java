package com.example.pidevcocomarket.interfaces;

import com.example.pidevcocomarket.entities.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProduitService {
    public Produit ajouterProduit(Produit produit, MultipartFile file)throws IOException;
    public Produit modifierProduit(Produit produit);
    public List<Produit> afficherListeProduit();
    public void deleteProduit(int id);
    public Produit retrieveProduit(int id);
    public void ProduitAffectBoutique(Integer idProduit, Integer idBoutique);
    public List<String> getBasicColors();
}
