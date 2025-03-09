package duan.sportify.controller.staff;

import duan.sportify.DTO.BookingDTO;
import duan.sportify.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sportify/api")
public class BookingAPIController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    @ResponseBody
    public List<BookingDTO> getBookings(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return bookingService.findBookingsForOwner(username, session);
        }
        return new ArrayList<>();
    }
    @PostMapping("/{id}/status")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newStatus = request.get("newStatus");

        // Check if status is valid
        if (!newStatus.equals("Đã Cọc") && !newStatus.equals("Hoàn Thành") && !newStatus.equals("Hủy Đặt")) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Trạng thái không hợp lệ!"));
        }

        // Fetch and update booking
        boolean updated = bookingService.updateBookingStatus(id, newStatus);
        if (updated) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật trạng thái thành công!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Không tìm thấy phiếu đặt sân!"));
        }
    }
}
