package com.example.notification.model;

public class Data {

    private String subject,teacher, time, realtime, temp, moisture;
    private int roomnumber ;

    public Data(){

    }

    public Data(String realtime, String subject, String teacher, String time, int roomnumber, String temp, String moisture){
        this.realtime = realtime;
        this.subject = subject;
        this.teacher = teacher;
        this.time = time;
        this.roomnumber = roomnumber;
        this.temp = temp;
        this.moisture = moisture;
    }

    public String getRealTime(){
        return realtime;
    }

    public String getSubject(){
        return subject;
    }

    public String getTeacher(){
        return teacher;
    }

    public String getTime(){
        return time;
    }

    public int getRoomNumber(){
        return roomnumber;
    }

    public String getTemp() {return temp;}

    public String getMoisture() {return moisture;}
}
