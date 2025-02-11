package org.croanna.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.croanna.models.RequestLog;
import org.croanna.repositories.RequestLogRepository;

@ApplicationScoped
public class RequestLogService {

    @Inject
    RequestLogRepository repository;

    @Transactional
    public void createLog(RequestLog request) {
        repository.save(request);
    }
}
