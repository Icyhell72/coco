package com.example.pidevcocomarket.controllers;

import com.example.pidevcocomarket.entities.Tender;
import com.example.pidevcocomarket.interfaces.ITenderService;
import com.example.pidevcocomarket.repositories.TenderRepository;
import com.example.pidevcocomarket.services.ProviderMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tender")
public class TenderController {

    private final TenderRepository tenderRepository;
    private final ProviderMailService providerMailService;

    @Autowired
    public TenderController(TenderRepository tenderRepository, ProviderMailService providerMailService) {
        this.tenderRepository = tenderRepository;
        this.providerMailService = providerMailService;
    }

    @Autowired
    private ITenderService tenderService;

    @GetMapping("/{id}")
    public Optional<Tender> getTenderById(@PathVariable int id) {
        return tenderService.getTenderById(id);
    }

    @GetMapping("/all")
    public List<Tender> getAllTenders() {
        return tenderService.getAllTenders();
    }

    @PostMapping("/add")
    public Tender addTender(@RequestBody Tender tender) {
        Tender addedTender = tenderService.addTender(tender);
        providerMailService.sendTenderStartedEmail(addedTender);
        return addedTender;
    }

    @PutMapping("/update")
    public Tender updateTender(@RequestBody Tender tender) {
        return tenderService.updateTender(tender);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTender(@PathVariable int id) {
        tenderService.deleteTender(id);
    }
}
