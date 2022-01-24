package com.example.officialreserveassets.service;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * class forms csv file with FILE_NAME name
 */
@Service
public class WriterService {
    private static final String FILE_NAME = "file.csv";
    private static final String[] HEADERS = {"Month", "Value", "Difference"};

    /**
     *
     * @param strings - data
     * @return .csv file
     */
    public File writeFile(List<String[]> strings) {
        {
            File file = new File(FILE_NAME);
            try {
                FileWriter outPutFile = new FileWriter(file);
                CSVWriter writer = new CSVWriter(outPutFile);

                writer.writeNext(HEADERS);
                writer.writeAll(strings);

                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return file;
        }

    }
}
