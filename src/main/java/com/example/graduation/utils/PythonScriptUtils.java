package com.example.graduation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonScriptUtils {
    public static List<String> run(String[] arg) throws IOException {

        Process process = Runtime.getRuntime().exec(arg);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> res = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            res.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return res;
    }
}
