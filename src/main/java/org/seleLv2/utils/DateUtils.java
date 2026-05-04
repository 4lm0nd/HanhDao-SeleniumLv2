package org.seleLv2.utils;

import org.seleLv2.common.constant.Constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    public static String convertDateToString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(Constant.fullDatetime);
        LocalDateTime localDate = LocalDateTime.now();
        return dateFormat.format(localDate);
    }

    public static String formatDate(String input){
                   return LocalDate.parse(input,
                            DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH))
                    .format(DateTimeFormatter.ofPattern(Constant.shortDateUs));
        }

    }

