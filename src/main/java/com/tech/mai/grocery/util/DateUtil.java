package com.tech.mai.grocery.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

    public static final LocalDate YESTERDAY = LocalDate.now().minus(Period.ofDays(1));
    public static final LocalDate SEVENTH_DAY_FROM_YESTERDAY = LocalDate.now().plus(Period.ofDays(5));
    public static final LocalDate THREE_DAYS_AFTER_TODAY = LocalDate.now().plus(Period.ofDays(3));
    public static final LocalDate NEXT_MONTH = LocalDate.now().plus(Period.ofMonths(1));
    public static final LocalDate NEXT_MONTH_END = NEXT_MONTH.withDayOfMonth(NEXT_MONTH.getMonth().length(NEXT_MONTH.isLeapYear()));

}
