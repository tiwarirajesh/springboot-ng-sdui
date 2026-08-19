package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServerDrivenUiService {

    public UiScreen getContractsScreen() {
        return new UiScreen(
                "contracts",
                "Contracts",
                List.of(
                        new UiComponent(
                                "page-header",
                                "header",
                                Map.of("title", "Contracts", "subtitle", "Manage customer contracts")),
                        new UiComponent(
                                "contract-table",
                                "table",
                                Map.of(
                                        "dataUrl", "/api/v1/contracts",
                                        "columns", List.of(
                                                Map.of("field", "contractId", "label", "Contract ID"),
                                                Map.of("field", "customerName", "label", "Customer"),
                                                Map.of("field", "title", "label", "Title"),
                                                Map.of("field", "status", "label", "Status"),
                                                Map.of("field", "amount", "label", "Amount")
                                        ))),
                        new UiComponent(
                                "create-contract",
                                "action",
                                Map.of("label", "Create contract", "action", "create-contract"))
                )
        );
    }

    public record UiScreen(String id, String title, List<UiComponent> components) {
    }

    public record UiComponent(String id, String type, Map<String, Object> properties) {
    }
}
