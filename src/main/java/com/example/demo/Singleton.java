package com.example.demo;


import org.apache.commons.lang3.SerializationUtils;


public enum Singleton  {

   INSTANCE;


    public void  getInstance(){
        System.out.println("...............");
    }

    public static void main(String[] args) {
        Singleton.INSTANCE.getInstance();

    }
}
