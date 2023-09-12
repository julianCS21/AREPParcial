package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class consultaService {



    public String getClass(String nameClass) throws ClassNotFoundException {
        Class c = Class.forName(nameClass);
        String response = Arrays.toString(c.getDeclaredMethods());
        response += Arrays.toString(c.getDeclaredFields());
        return response;

    }


    public String invokeMethod (String nameClass, String nameMethod) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = Class.forName(nameClass);
        Method m = c.getMethod(nameMethod);
        Object result =m.invoke(null);
        return result.toString();

    }





}
