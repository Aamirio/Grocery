package com.tech.mai.grocery.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

    public static final LocalDate yesterday = LocalDate.now().minus(Period.ofDays(1));
    public static final LocalDate seventhDayFromYesterday = LocalDate.now().plus(Period.ofDays(5));
    public static final LocalDate threeDaysAfterToday = LocalDate.now().plus(Period.ofDays(3));
    public static final LocalDate nextMonth = LocalDate.now().plus(Period.ofMonths(1));
    public static final LocalDate nextMonthEnd = nextMonth.withDayOfMonth(nextMonth.getMonth().length(nextMonth.isLeapYear()));

}
