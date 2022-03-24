package com.number;


import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * @author Grigory Yakovlev
 */
public class SortNumbers {
    public static void main(String[] args) {
        String fileName = "/mnt/3696B2E796B2A731/job/efin/efin/configurations.properties";
        int oldNumber;

        try {
            File file = new File(fileName);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем объект FileWriter для объекта File
            FileWriter writer = new FileWriter("src/main/resources/configurations.properties", false);
            StringBuffer buffer = new StringBuffer();
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку

            String line = reader.readLine();
            while (line != null) {
                int pointIndex = line.indexOf(".") + 1;
                String num = line.substring(pointIndex, pointIndex + 3);
                oldNumber = Integer.parseInt(num);
                buffer.append(line);
                buffer.append(System.lineSeparator());
                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
            writer.write(buffer.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNumber(String str){
        String regex = "^efin.";
        Pattern pattern = Pattern.compile(regex);
        return str;
    }
}
