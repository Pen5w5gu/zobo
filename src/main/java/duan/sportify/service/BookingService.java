package duan.sportify.service;

import java.util.List;
import java.util.Map;


import duan.sportify.DTO.BookingDTO;
import duan.sportify.DTO.BookingDetailDTO;
import duan.sportify.entities.Authorized;
import duan.sportify.entities.Bookings;

import javax.servlet.http.HttpSession;


@SuppressWarnings("unused")
public interface BookingService {
    List<Bookings> findAll();

    Bookings create(Bookings bookings);

    Bookings update(Bookings bookings);

    void delete(Integer id);

    Bookings findById(Integer id);

    List<Object[]> getBookingInfoByUsername(String username);

    Object[] getBookingInfoByBookingDetail(String bookingid);

    int countBooking();

    boolean updateBookingStatus(Long id, String newStatus);

    List<BookingDTO> findBookingsForOwner(String username, HttpSession session);

    int countBookingsToday(String username);

    double sumRevenueToday(String username);

    Map<String, List<BookingDetailDTO>> getScheduleForOwner(String username) ;
}
