package com.example.pidevcocomarket.controllers;


import com.example.pidevcocomarket.entities.Tender;
import com.example.pidevcocomarket.interfaces.ITenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tender")
public class TenderController {
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
        return tenderService.addTender(tender);
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
