package org.example;

import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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


    public String invokeMethodUnary (String nameClass, String nameMethod,String type,String param) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = Class.forName(nameClass);
        Object result = null;
        int parameter = 0;
        double paramD =0.0;
        if(type.equals("int")){
            parameter = Integer.parseInt(param);
            Class<?>[] types = {Class.forName("java.lang.Integer")};
            Method m = c.getMethod(nameMethod,types);
            result =m.invoke(null,parameter);
        }
        else if (type.equals("double")){
            paramD = Double.parseDouble(param);
            Method m = c.getMethod(nameMethod, Class.forName("java.lang.Double"));
            result =m.invoke(null,paramD);

        }
        else{
            Method m = c.getMethod(nameMethod, Class.forName("java.lang.String"));
            result =m.invoke(null,param);
        }
        return result.toString();

    }





}
