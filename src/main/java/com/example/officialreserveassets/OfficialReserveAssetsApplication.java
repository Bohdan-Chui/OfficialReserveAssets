package com.example.officialreserveassets;

import com.example.officialreserveassets.model.Reserves;
import com.example.officialreserveassets.service.ParserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class OfficialReserveAssetsApplication {

    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(OfficialReserveAssetsApplication.class, args);
    }


}
