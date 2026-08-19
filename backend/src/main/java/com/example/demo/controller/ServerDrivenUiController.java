package com.example.demo.controller;

import com.example.demo.service.ServerDrivenUiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ui")
@CrossOrigin(originPatterns = {"http://localhost:4200", "https://*.app.github.dev"})
@RequiredArgsConstructor
public class ServerDrivenUiController {

    private final ServerDrivenUiService serverDrivenUiService;

    @GetMapping("/contracts")
    public ServerDrivenUiService.UiScreen getContractsScreen() {
        return serverDrivenUiService.getContractsScreen();
    }
}
