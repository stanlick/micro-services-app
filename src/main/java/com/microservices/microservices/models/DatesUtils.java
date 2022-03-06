package com.microservices.microservices.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.springframework.stereotype.Component;

public class DatesUtils {
    

    public static LocalDate now(){
        return LocalDate.now();
    }

    public static LocalDate getNextFriday(LocalDate d) {
        return d.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
      }

      public static LocalDate getPreviousFriday(LocalDate d) {
        return d.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
      }

      public static LocalDate getPreviousFriday(LocalDate d, int whichPrevious) {
        //LocalDate previous = d.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        return d.minusDays(7*whichPrevious);
      }

      public static LocalDate getSomePreviousOrSameDayOfWeek(DayOfWeek dayOfWeek) {
        return LocalDate.now()
                 .with(TemporalAdjusters.previousOrSame(dayOfWeek));
      }

      public static LocalDate getSomeNextOrSameDayOfWeek(DayOfWeek dayOfWeek) {
        return LocalDate.now()
                 .with(TemporalAdjusters.nextOrSame(dayOfWeek)).plusDays(7);
      }

      public static LocalDate getSomePreviousFriday(LocalDate d, int whichNext) {
        LocalDate next = d.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        return next.minusDays(7*whichNext);
      }

}
