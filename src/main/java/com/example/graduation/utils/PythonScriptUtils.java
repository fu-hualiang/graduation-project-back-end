package com.example.graduation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonScriptUtils {
    public static List<String> run(String[] args) {

        try {
            List<String> resultList = new ArrayList<>();
            Process process = Runtime.getRuntime().exec(args);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"), 8192);
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "gbk"), 8192);

            String result;
            while ((result = bufferedReader.readLine()) != null) {
                resultList.add(result);
            }
            bufferedReader.close();
            process.waitFor();

            while ((result = stdError.readLine()) != null) {
                System.out.println(result);
            }

            return resultList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
