package com.evaluator.operator.date;

import com.evaluator.operator.Operator;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author shrikrushna on 2020-04-18
 */
public class DateDifference extends Operator {

    public Long dateDifference(Date left, Date right) {
        long l = left.getTime() - right.getTime();
        return TimeUnit.DAYS.toDays(l);
    }

    public Date add(Date date, Integer days) {
        return new DateTime(date).plusDays(days).toDate();
    }

    public Date subtract(Date date, Integer days) {
        return new DateTime(date).minusDays(days).toDate();
    }
}
