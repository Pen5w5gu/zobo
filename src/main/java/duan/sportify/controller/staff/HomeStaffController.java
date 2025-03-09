package duan.sportify.controller.staff;

import duan.sportify.DTO.BookingDTO;
import duan.sportify.DTO.BookingDetailDTO;
import duan.sportify.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller

public class HomeStaffController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/sportify/staff/dashboard")
    public String staff(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            int totalBookingsToday = bookingService.countBookingsToday(username);
            double totalRevenueToday = bookingService.sumRevenueToday(username);
            Map<String, List<BookingDetailDTO>> schedule = bookingService.getScheduleForOwner(username);

            model.addAttribute("totalBookingsToday", totalBookingsToday);
            model.addAttribute("totalRevenueToday", totalRevenueToday);
            model.addAttribute("schedule", schedule);
            model.addAttribute("title", "Dashboard");
        }
        return "staff/views/index";
    }


    @GetMapping("/sportify/staff/bookings")
    public String bookings(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            List<BookingDTO> bookings = bookingService.findBookingsForOwner(username, session);

            model.addAttribute("bookings", bookings);
            model.addAttribute("title", "Quản lý đặt sân");

        }
        return "staff/views/booking";
    }

}
