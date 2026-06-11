package org.seleLv2.utils;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.DataTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DataUtils {

    public static String convertDateToString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(Constant.fullDatetime);
        LocalDateTime localDate = LocalDateTime.now();
        return dateFormat.format(localDate);
    }

    public static double convertToDouble(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(
                text.replace("$", "")
                        .replace(",","")
                        .trim()
        );
    }


    public static String normalize(String text) {
        return text
                .replace("× 1", "")
                .trim()
                .toLowerCase();
    }

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

}

