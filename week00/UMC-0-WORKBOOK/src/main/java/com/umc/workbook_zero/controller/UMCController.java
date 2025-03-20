package com.umc.workbook_zero.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
public class UMCController {

    @GetMapping("/umc")
    public String findUMC() {
        return "Good Luck!";
    }
}