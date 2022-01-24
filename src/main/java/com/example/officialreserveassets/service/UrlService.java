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
        StringBuilder url = new StringBuilder(PATH);
        url.append(calendar.get(Calendar.YEAR));
        url.append(getMonth(calendar));
        url.append(PATH_END);

        return new URL(url.toString());
    }

    /**
     * @param calendar
     * @return string with number of month
     */
    public String getMonth(Calendar calendar){
       int month = calendar.get(Calendar.MONTH) + 1;
       if(month<=9)
           return new String("0" + month);
       else  return Integer.toString(month);
    }
}
