package com.example.officialreserveassets.model;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserves implements Cloneable{
    public Calendar dt;
    public double value;

    public String toString(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return "Reserves [date:" + date.format(dt.getTime()) + " value: " + value+ " ]";
    }
    @Override
    public Reserves clone() throws CloneNotSupportedException{

        Calendar calendar = (Calendar) this.dt.clone();
        return new Reserves(calendar, value);
    }
}
