package com.example.pidevcocomarket.controllers;

import com.example.pidevcocomarket.Repositories.ProduitRepository;
import com.example.pidevcocomarket.interfaces.IProduitService;
import com.example.pidevcocomarket.entities.ProductStatus;
import com.example.pidevcocomarket.entities.Produit;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/Produit")
@AllArgsConstructor
public class ProduitController {

    IProduitService produitService;
    ProduitRepository produitRepository;

   /* @PostMapping("/ajouterProduit")
    Produit ajouterProduit(@RequestBody Produit p) {
        return produitService.ajouterProduit(p);
    }*/

    @PutMapping("/modifierProduit")
    Produit modifierProduit(@RequestBody Produit p) {
        return produitService.modifierProduit(p);
    }

    @GetMapping("/afficherProduits")
    List<Produit> afficherProduit() {
        return produitService.afficherListeProduit();
    }

    @DeleteMapping("/supprimerProduit/{id}")
    void supprimerProduit(@PathVariable int id) {
        produitService.deleteProduit(id);
    }

    @GetMapping("afficherProduit/{id}")
    Produit retriveProduit(@PathVariable int id) {
        return produitService.retrieveProduit(id);
    }

    @PutMapping("/affectProduitToBoutique/{idProduit}/{idBoutique}")
    void affecterProduitBoutique(Integer idProduit,Integer idBoutique)
    {
        produitService.ProduitAffectBoutique(idProduit, idBoutique);
    }

    @GetMapping("/add-producttt")
    public String ShowAddProductForm(Model model){
        List<String> basicColors=produitService.getBasicColors();
        model.addAttribute("basicColors",basicColors);
        model.addAttribute("produit",new Produit());
        return "produit";

    }
    @PostMapping("/add-producttt")
    public String submitAddProductForm(@ModelAttribute Produit produit){
        //enregistrer le produit dans la BD
        return "redirect:/produit";
    }
 /*  @PostMapping("/add")
    public String addProduct(@ModelAttribute Produit product, @RequestParam("image") MultipartFile file) {
        try {
            product.setImage(file.getBytes());
            produitService.ajouterProduit(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/produit";
    }*/



    //@PostMapping(consumes = { "multipart/form-data" },produces = "application/octet-stream")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produit> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") float price,
            @RequestParam("color") String color,
            @RequestParam("status") ProductStatus status,
            @RequestParam("image") MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        Produit product = new Produit();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setColor(color);
        product.setStatus(status);


        if (!contentType.endsWith("jpeg") && !contentType.endsWith("png")) {
            throw new IllegalArgumentException("Only JPEG and PNG files are allowed.");
        }
        produitService.ajouterProduit(product, file);
        produitRepository.save(product);

        return ResponseEntity.ok().body(product);
    }



}



