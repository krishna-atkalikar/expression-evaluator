package com.evaluator.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * @author shrikrushna on 2020-05-19
 */
public class DateCalculator {

	private static final Set<LocalDate> holidays = new HashSet<>();

	static {
		holidays.add(LocalDate.parse("01-05-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}

	public static BiFunction<Object, Object, Object> addBusinessDaysToDate() {
		return (l, r) -> {
			LocalDate left = (LocalDate) l;
			int daysToAdd = ((Double) r).intValue();
			if (daysToAdd < 1) {
				return left;
			}
			LocalDate result = left;
			int addedDays = 0;
			while (addedDays < daysToAdd) {
				result = result.plusDays(1);
				if (!(isWeekend(result) || isHoliday(result))) {
					++addedDays;
				}
			}
			return result;
		};
	}

	private static boolean isHoliday(LocalDate result) {
		return holidays.contains(result);
	}

	private static boolean isWeekend(LocalDate result) {
		return result.getDayOfWeek() == DayOfWeek.SATURDAY ||
				result.getDayOfWeek() == DayOfWeek.SUNDAY;
	}
}
