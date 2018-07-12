package com.composite.utils;

import java.io.*;

public class DataConvertUtil {

    public static Object byteToObject(byte[] bytes) {
        Object object = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
            byteArrayInputStream.close();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
        }
        return object;
    }


    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException e) {
            System.out.println("translation" + e.getMessage());
        }
        return bytes;
    }


}
