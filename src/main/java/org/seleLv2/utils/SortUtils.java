package org.seleLv2.utils;

import org.seleLv2.common.enums.DataTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortUtils {

    public static boolean isSortedASC(List<String> actual){
        DataTypes type = DataUtils.detectType(actual);
        switch(type){

            case NUMBER:
            case CURRENCY:
                List<Double> numList = DataUtils.parseToDouble(actual, type == DataTypes.CURRENCY);
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
        DataTypes type = DataUtils.detectType(actual);
        switch(type){

            case NUMBER:
            case CURRENCY:
                List<Double> numList = DataUtils.parseToDouble(actual, type == DataTypes.CURRENCY);
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
