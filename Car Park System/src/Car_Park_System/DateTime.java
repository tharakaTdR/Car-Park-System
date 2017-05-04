package Car_Park_System;

import java.io.Serializable;

/**
 * Created by Tharaka on 11/16/2016.
 */
//creating manually date time class
public class DateTime implements Serializable {

    private int year;
    private int month;
    private int day;
    private int hour;
    private long mili;

    public DateTime() {
        setCurrentTime();
        this.mili = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day + ":" + hour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }


     //calculate current time

    private void setCurrentTime() {


        long millis = System.currentTimeMillis();
        long hour = (millis / (1000 * 60 * 60)) % 24;
        long days = (millis / (1000 * 60 * 60 * 24));
        int totalYears = (int) days / 365;
        int totalDays = (int) days % 365;
        int year = totalYears + 1970;
        int leapYears = 0;

        for (int i = 1970; i < year; i++) {

            if (checkLeapYear(i)) {
                leapYears++;
            }
        }

        int currentDay = (int) totalDays - leapYears;
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (checkLeapYear(year)) {
            monthDays[1] = 29;
        }
        int month = 0;
        for (int i = 0; i < monthDays.length; i++) {
            if (monthDays[i] <= currentDay) {
                currentDay -= monthDays[i];
            } else {
                month = i + 1;
                break;
            }
        }
        this.hour = (int) hour +5;
        this.day = currentDay + 1;
        this.month = month;
        this.year = year;

    }


    private boolean checkLeapYear(int year) {
        return ((year % 4 == 0 && !(year % 100 == 0)) || year % 400 == 0);
    }

    public long timeDifference() {
        return System.currentTimeMillis() - mili;
    }

    public void setMili() {
        this.mili = System.currentTimeMillis();
    }

}
