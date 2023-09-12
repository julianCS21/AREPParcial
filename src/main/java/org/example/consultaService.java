package org.example;

import java.util.Arrays;

public class consultaService {



    public String getClass(String nameClass) throws ClassNotFoundException {
        Class c = Class.forName(nameClass);
        String response = Arrays.toString(c.getDeclaredMethods());
        response += Arrays.toString(c.getDeclaredFields());
        return response;

    }





}
