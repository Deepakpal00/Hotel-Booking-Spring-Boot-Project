package com.alore.growth.io.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alore.growth.io.exception.NoAvailableRoomsException;
import com.alore.growth.io.model.Booking;
import com.alore.growth.io.model.Hotel;
import com.alore.growth.io.model.User;
import com.alore.growth.io.repository.BookingRepository;
import com.alore.growth.io.service.BookingService;
import com.alore.growth.io.service.HotelService;
import com.alore.growth.io.utils.BookingUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Service
public class BookingServiceImpl implements BookingService {

    private Logger logger = Logger.getLogger(BookingServiceImpl.class.getName());

    @Autowired
    private HotelService hotelRoomService;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking bookHotelRoom(User user, int roomSize, LocalDate beginDate, LocalDate endDate) throws NoAvailableRoomsException {
        Hotel hotelRoom = getHotelRoomForBooking(roomSize, beginDate, endDate);
        Booking booking = new Booking();
        booking.setHotelRoom(hotelRoom);
        booking.setBeginDate(beginDate);
        booking.setEndDate(endDate);
        booking.setUser(user);
        return bookingRepository.save(booking);
    }

    @Override
    public Hotel getHotelRoomForBooking(int roomSize, LocalDate beginDate, LocalDate endDate) throws NoAvailableRoomsException {
        List<Hotel> hotelRoomList = hotelRoomService.getHotelRoomBySize(roomSize);
        logger.info(String.format("found %d rooms with size %d", hotelRoomList.size(), roomSize));
        if (!hotelRoomList.isEmpty()) {
            Map<Hotel, List<Booking>> bookingsForHotelRoom = new HashMap<>();

            for (Hotel hotelRoom : hotelRoomList) {
                List<Booking> futureBookingsForHotelRoom = checkIsRoomHasActiveBookings(hotelRoom, beginDate);
                if (futureBookingsForHotelRoom.isEmpty()) 
                	return hotelRoom;
                bookingsForHotelRoom.put(hotelRoom, futureBookingsForHotelRoom);
            }

            for (Map.Entry<Hotel, List<Booking>> entry : bookingsForHotelRoom.entrySet()) {
                if (BookingUtils.isBookingAvailable(entry.getValue(), beginDate, endDate)) return entry.getKey();
            }

        }
        throw new NoAvailableRoomsException();
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    private List<Booking> checkIsRoomHasActiveBookings(Hotel hotelRoom, LocalDate startDate) {
        return bookingRepository.findByHotelRoomAndEndDateGreaterThanOrderByEndDateAsc(hotelRoom, startDate);
    }
}
