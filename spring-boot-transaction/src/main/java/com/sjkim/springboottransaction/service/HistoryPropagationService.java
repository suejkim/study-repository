package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.History;
import com.sjkim.springboottransaction.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryPropagationService {

    private final HistoryRepository historyRepository;

    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Transactional(propagation = Propagation.NEVER)
    public History saveHistoryPropagationNever(History history) {
        return historyRepository.save(history);
    }

    // @Transactional(propagation = Propagation.REQUIRED)
    public History saveHistoryPropagationRequired(History history) {
        history = historyRepository.save(history);
        throw new RuntimeException("RuntimeException발생. rollback");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public History saveHistoryPropagationRequiresNew(History history) {
        history = historyRepository.save(history);
        throw new RuntimeException("RuntimeException발생. rollback");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public History saveHistoryPropagationMandatory(History history) {
        return historyRepository.save(history);
    }

    @Transactional(propagation = Propagation.NESTED)
    public History saveHistoryPropagationNestOccureException(History history) {
        history = historyRepository.save(history);
        throw new RuntimeException("RuntimeException발생. rollback");
    }

    @Transactional(propagation = Propagation.NESTED)
    public History saveHistoryPropagationNest(History history) {
        return historyRepository.save(history);
    }
}
