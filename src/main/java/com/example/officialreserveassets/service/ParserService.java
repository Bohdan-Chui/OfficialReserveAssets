package com.example.officialreserveassets.service;

import com.example.officialreserveassets.model.Reserves;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * class implements parser logics
 * takes data and format them
 */
@Service
public class ParserService {
    private final UrlService urlService;

    public ParserService(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * format data and find difference
     * @param data
     * @return list of data
     */
    public List<String[]> getDataForCSV(Set<? extends Reserves> data){
      LinkedList <String[]> list = new LinkedList<>();
      SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd ");
      Reserves ob1, ob2;

      Iterator<? extends Reserves> iterator = data.iterator();
      ob1 = iterator.next();
      list.add(new String[]{date.format(ob1.dt.getTime()),Double.toString(ob1.value), " 0"});

      while (iterator.hasNext())
      {
          ob2 = iterator.next();
          list.add(new String[]{date.format(ob2.dt.getTime()),Double.toString(ob2.value), " " +
                  String.format(" %.2f", ob2.value - ob1.value)});
          ob1 = ob2;
      }
//        data.stream().findFirst(e->ob1 = e)
//         Reservesdata.stream().findFirst();
      return list;
    }

    public Set<? extends Reserves> getReservesSet(){
        Set<Reserves> data = new LinkedHashSet<>();
        Calendar calendar = new GregorianCalendar(2004, Calendar.JANUARY,1);
        Calendar now = Calendar.getInstance();
        try {
            while (now.compareTo(calendar) >= 0) {
                data.add(getReservesFromServer(calendar).clone());
                calendar.add(Calendar.MONTH, 1);
            }
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return data;

    }

    public Reserves getReservesFromServer(Calendar calendar){
        double value = 0;
        try {
            value =  getValueFromServerByUrl(urlService.getURLByDate(calendar));
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return new Reserves(calendar,value);
    }

    /**
     * make get-request and get value of reserve
     * @param url
     * @return value
     */
    public double getValueFromServerByUrl(URL url) {
        String inputLine;
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        }catch (IOException e ){
            e.printStackTrace();
        }

        String string = response.toString().replace("[", "").replace("]", "");
        JSONObject req = new JSONObject(string);

        return req.getDouble("value");
    }

}
