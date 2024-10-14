package com.example.jarovnya.transaction;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransController {
    private final TransService transService;

    private static final Logger logger = LoggerFactory.getLogger(TransController.class);
}