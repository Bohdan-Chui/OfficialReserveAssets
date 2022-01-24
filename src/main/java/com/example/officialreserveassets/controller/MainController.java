package com.example.officialreserveassets.controller;

import com.example.officialreserveassets.service.ParserService;
import com.example.officialreserveassets.service.WriterService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;

/**
 * main controller class
 *
 */
@Controller
@RequestMapping (value = "/file")
public class MainController {

    final ParserService service;

    final WriterService writerService;

    public MainController(ParserService service, WriterService writerService) {
        this.service = service;
        this.writerService = writerService;
    }


    @GetMapping(value = "/download", produces = "text/csv")
    public ResponseEntity generateReport() {
        try {
            File file = writerService.writeFile(service.getDataForCSV(service.getReservesSet()));
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + "reserves" + ".csv")
                    .contentLength(file.length())
                    .body(new FileSystemResource(file));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
