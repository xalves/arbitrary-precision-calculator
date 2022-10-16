package com.example.restCalculator.adapters;

import com.example.restCalculator.domain.OperationMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface RequestProcessor {

    OperationMessage send(OperationMessage message) throws IOException;
}
