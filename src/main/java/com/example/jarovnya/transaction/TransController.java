package com.example.jarovnya.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransController {
    private final TransService transService;
}