package com.moneim.api.controllers;

import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.repositories.JournalRepository;
import com.moneim.api.services.IJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    IJournalService journalService;

    @GetMapping("/getAllJournals")
    public ObjectResponse getAllJournals() {
        return journalService.getAllJournals();
    }

    @GetMapping("/findJournalsByIdUser/{idUser}")
    public ObjectResponse findJournalsByIdUser(@PathVariable String idUser) {
        return journalService.getAllJournalByIdUser(idUser);
    }

    @GetMapping("/findJournalsByOpeartion/{operation}")
    public ObjectResponse findJournalsByOpeartion(@PathVariable String operation) {
        return journalService.findAllJournalsByOperation(operation);
    }
}
