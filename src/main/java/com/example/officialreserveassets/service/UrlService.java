package com.example.officialreserveassets.service;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * class help to manage urls
 */
@Service
public class UrlService {
    public static final String PATH = "https://bank.gov.ua/NBUStatService/v1/statdirectory/res?date=";
    public static final String PATH_END = "&json";

    /**
     *
     * @param calendar with date
     * @return url gor get request
     * @throws MalformedURLException
     */
    public URL getURLByDate(Calendar calendar) throws MalformedURLException {
        String url = PATH + calendar.get(Calendar.YEAR) +
                getMonth(calendar) +
                PATH_END;

        return new URL(url);
    }

    /**
     * @param calendar
     * @return string with number of month
     */
    public String getMonth(Calendar calendar){
       int month = calendar.get(Calendar.MONTH) + 1;
       if(month<=9)
           return "0" + month;
       else  return Integer.toString(month);
    }
}
