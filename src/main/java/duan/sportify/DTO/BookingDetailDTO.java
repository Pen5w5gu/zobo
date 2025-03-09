package duan.sportify.DTO;

import lombok.Data;

import java.time.LocalTime;
@Data
public class BookingDetailDTO {
        private int fieldId;
        private String fieldName;
        private int shiftId;
        private String shiftName;
        private LocalTime startTime;
        private LocalTime endTime;
        private String bookingStatus;

        public BookingDetailDTO(int fieldId, String fieldName, int shiftId, String shiftName, LocalTime startTime, LocalTime endTime, String bookingStatus) {
            this.fieldId = fieldId;
            this.fieldName = fieldName;
            this.shiftId = shiftId;
            this.shiftName = shiftName;
            this.startTime = startTime;
            this.endTime = endTime;
            this.bookingStatus = bookingStatus;
        }

    }
