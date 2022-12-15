package com.moneim.api.services;

import com.moneim.api.entities.Journal;
import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.repositories.JournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class JournalServiceImplementation implements IJournalService {
    Logger logger = LoggerFactory.getLogger(JournalServiceImplementation.class);

    @Autowired
    JournalRepository journalRepository;


    @Override
    public ObjectResponse getAllJournals() {

        logger.info("Journal Service Impl  : Getting All Journals ");
        try {
            Long nbrOfJournals = journalRepository.count();
            if (nbrOfJournals == 0) {
                return new ObjectResponse("Empty list Journals",
                        null,
                        0);
            } else return new ObjectResponse("List Journaux found ",
                    journalRepository.findAll(),
                    1);
        } catch (Exception e) {
            return new ObjectResponse(" Erreur " + e.getMessage(),
                    null,
                    2);
        }
    }

    @Override
    public ObjectResponse getAllJournalByIdUser(String idUser) {

        logger.info("Journal Service Impl  : Getting All Journals By idUser " + idUser);

        try {
            List<Journal> listJournals = journalRepository.findByIdUser(idUser);

            if (listJournals.size() == 0) {
                return new ObjectResponse("Empty list Journals",
                        null,
                        0);
            } else return new ObjectResponse("List Journaux found ",
                    journalRepository.findByIdUser(idUser),
                    1);
        } catch (Exception e) {
            return new ObjectResponse(" Erreur " + e.getMessage(),
                    null,
                    2);
        }
    }

    @Override
    public ObjectResponse findAllJournalsByOperation(String operation) {
        logger.info("Journal Service Impl  : Getting All Journals By opeartion " + operation);

        try {
            List<Journal> listJournals = journalRepository.findByOperation(operation);

            if (listJournals.size() == 0) {
                return new ObjectResponse("Empty list Journals",
                        null,
                        0);
            } else return new ObjectResponse("List Journaux found ",
                    journalRepository.findByOperation(operation),
                    1);
        } catch (Exception e) {
            return new ObjectResponse(" Erreur " + e.getMessage(),
                    null,
                    2);
        }
    }

    @Override
    public ObjectResponse getAllForeachTypeOperation() {

        List<Journal> allJournals=journalRepository.findAll();
        Map<String,List<Journal>> byTypeOperation=allJournals.stream().collect(Collectors.groupingBy(Journal::getOperation));
        return new ObjectResponse("Journals By Operation",byTypeOperation,1);
    }
}
