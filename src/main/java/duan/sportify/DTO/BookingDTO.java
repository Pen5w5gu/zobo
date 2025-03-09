package duan.sportify.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO {
    private int bookingid;
    private String username;
    private Date bookingdate;
    private Double bookingprice;
    private String bookingstatus;
    private String namefield;
    private String nameshift;
    private LocalTime starttime ;
    private LocalTime endtime ;
    private String firstname;
    private String lastname;
    private String phone;
    private String note;
    private Date playdate;

}
