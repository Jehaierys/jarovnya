package com.example.jarovnya.transaction;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransService {
    private final TransRepo transRepo;

    private static final Logger logger = LoggerFactory.getLogger(TransService.class);

    public Transaction getTransaction(Long id) {
        return transRepo.getReferenceById(id);
    }
}