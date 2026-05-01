package org.seleLv2.utils;

import org.seleLv2.common.enums.DataTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortUtils {
    public static List<Double> parseToDouble(List<String> data, boolean isCurrency) {
        return data.stream()
                .map(s -> {
                    String value = s.replace(",", "").trim();
                    if (isCurrency) {
                        value = value.replace("$", "");
                    }
                    return Double.parseDouble(value);
                })
                .collect(Collectors.toList());
    }

    public static DataTypes detectType(List<String> data){
        return data.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .findFirst()
                .map(sample -> {
                    if(sample.matches("^\\$?\\d{1,3}(,\\d{3})*(\\.\\d+)?$")){
                        return sample.contains("$")
                                ? DataTypes.CURRENCY
                                : DataTypes.NUMBER;
                    }

                    return DataTypes.STRING;
                })
                .orElse(DataTypes.STRING);
    }
    public static boolean isSortedASC(List<String> actual){
        DataTypes type = detectType(actual);
        switch(type){

            case NUMBER:
            case CURRENCY:
                List<Double> numList = parseToDouble(actual, type == DataTypes.CURRENCY);
                List<Double> sortedNum = new ArrayList<>(numList);
                Collections.sort(sortedNum);

                return numList.equals(sortedNum);

            default:
                List<String> sorted = actual.stream()
                        .map(String::trim)
                        .collect(Collectors.toList());

                List<String> sortedCopy = new ArrayList<>(sorted);
                Collections.sort(sortedCopy);

                return sorted.equals(sortedCopy);
        }
    }

    public static boolean isSortedDESC(List<String> actual){
        DataTypes type = detectType(actual);
        switch(type){

            case NUMBER:
            case CURRENCY:
                List<Double> numList = parseToDouble(actual, type == DataTypes.CURRENCY);
                List<Double> sortedNum = new ArrayList<>(numList);
                sortedNum.sort(Collections.reverseOrder());

                return numList.equals(sortedNum);

            default:
                List<String> sorted = actual.stream()
                        .map(String::trim)
                        .collect(Collectors.toList());

                List<String> sortedCopy = new ArrayList<>(sorted);
                sortedCopy.sort(Collections.reverseOrder());

                return sorted.equals(sortedCopy);
        }
    }
}
