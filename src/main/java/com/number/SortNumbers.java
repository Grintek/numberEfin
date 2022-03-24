package com.number;


import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Grigory Yakovlev
 */
public class SortNumbers {
    static Integer oldValue = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введиет путь к файлу: ");
        String filePath = in.next();

        try {
            File file = new File(filePath);
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
                String num = getNumber(line);
                if(!num.equals("")) {
                    int numValue = Integer.parseInt(num);
                    if (oldValue == 0) {
                        oldValue = numValue;
                    } else {
                        line = calculateStr(numValue, oldValue, line);
                    }
                }

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

    public static String getNumber(String str) {
        String regex = "\\d{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        String number = "";
        while (match.find()) {
            int st = match.start();
            int en = match.end();
            number = str.substring(st, en);
        }
        return number;
    }

    public static String calculateStr(int newNum, int oldNum, String line){
        String pars;
        boolean isGood = oldNum + 1 == newNum;
        if(isGood){
            pars = transformInStringNumber(newNum);
            oldValue = newNum;
        }else{
            pars = transformInStringNumber(oldNum + 1);
            oldValue = oldNum + 1;
        }



        String regex = "\\d{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(line);
        match.find();
        String newLine = match.replaceFirst(pars);
        return newLine;
    }

    public static String transformInStringNumber(int value){
        String pars = String.valueOf(value);
        if(pars.length() < 2){
            pars = "00" + pars;
        }else if(pars.length() < 3){
            pars = "0" + pars;
        }
        return pars;
    }
}
