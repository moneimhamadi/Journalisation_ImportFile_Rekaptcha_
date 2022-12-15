package com.moneim.api.services;

import com.moneim.api.entities.ObjectResponse;
import org.springframework.stereotype.Service;

@Service
public interface IJournalService {

    ObjectResponse getAllJournals();

    ObjectResponse getAllJournalByIdUser(String idUser);

    ObjectResponse findAllJournalsByOperation(String operation);

    public ObjectResponse getAllForeachTypeOperation();
}
