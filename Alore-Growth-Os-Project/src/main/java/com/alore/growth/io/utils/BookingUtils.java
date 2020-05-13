package com.alore.growth.io.utils;


import java.time.LocalDate;
import java.util.List;

import com.alore.growth.io.model.Booking;

public class BookingUtils {

    public static boolean isBookingAvailable(List<Booking> bookingList, LocalDate beginDate, LocalDate endDate) {
        boolean isAvailable = false;

        for(Booking booking : bookingList) {
            isAvailable = !areDatesOverlapping(booking.getBeginDate(), booking.getEndDate(), beginDate, endDate);
        }

        return isAvailable;
    }

    private static boolean areDatesOverlapping(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2,
                                               LocalDate endDate2) {
        return startDate1.isBefore(endDate2) && endDate1.isAfter(startDate2);
    }
}
