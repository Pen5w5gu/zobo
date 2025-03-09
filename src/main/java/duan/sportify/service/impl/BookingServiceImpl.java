package duan.sportify.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import duan.sportify.DTO.BookingDTO;
import duan.sportify.DTO.BookingDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import duan.sportify.dao.AuthorizedDAO;
import duan.sportify.dao.BookingDAO;

import duan.sportify.entities.Authorized;
import duan.sportify.entities.Bookings;
import duan.sportify.service.AuthorizedService;
import duan.sportify.service.BookingService;

import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
@Service
public class BookingServiceImpl implements BookingService{
	@Autowired
	BookingDAO bookingDAO;
	@Autowired

	@Override
	public List<Bookings> findAll() {
		// TODO Auto-generated method stub
		return bookingDAO.findAll();
	}

	@Override
	public Bookings create(Bookings bookings) {
		// TODO Auto-generated method stub
		return bookingDAO.save(bookings);
	}

	@Override
	public Bookings update(Bookings bookings) {
		// TODO Auto-generated method stub
		return bookingDAO.save(bookings);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		bookingDAO.deleteById(id);
	}

	@Override
	public Bookings findById(Integer id) {
		// TODO Auto-generated method stub
		return bookingDAO.findById(id).get();
	}

	

	@Override
	public List<Object[]> getBookingInfoByUsername(String username) {
		// TODO Auto-generated method stub
		return bookingDAO.getBookingInfoByUsername(username);
	}

	@Override
	public Object[] getBookingInfoByBookingDetail(String bookingid) {
		// TODO Auto-generated method stub
		return bookingDAO.getBookingInfoByBookingDetail(bookingid);
	}

	@Override
	public int countBooking() {
		// TODO Auto-generated method stub
		return bookingDAO.countBooking();
	}

	@Override
	public boolean updateBookingStatus(Long id, String newStatus) {
		Optional<Bookings> optionalBooking = bookingDAO.findById(Math.toIntExact(id));
		if (optionalBooking.isPresent()) {
			Bookings booking = optionalBooking.get();
			booking.setBookingstatus(newStatus);
			bookingDAO.save(booking);
			return true;
		}
		return false;
	}

	@Override
	public List<BookingDTO> findBookingsForOwner(String username, HttpSession session) {
		 String currentUsername = session.getAttribute("username").toString();
		return bookingDAO.findBookingsByFieldOwner(currentUsername);
	}

	@Override
	public int countBookingsToday(String username) {
		return 0;
	}

	@Override
	public double sumRevenueToday(String username) {
		return 0;
	}

	public Map<String, List<BookingDetailDTO>> getScheduleForOwner(String username) {

		LocalDate currentDate = LocalDate.now();
		Date convertedDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<BookingDetailDTO> slots = bookingDAO.getSlotStatusForOwner(username, convertedDate);
		return slots.stream().collect(Collectors.groupingBy(BookingDetailDTO::getShiftName));
	}



}
