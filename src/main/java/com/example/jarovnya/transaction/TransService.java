package com.example.jarovnya.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransService {
    private final TransRepo transRepo;

    public Transaction getTransaction(Long id) {
        return transRepo.getReferenceById(id);
    }
}