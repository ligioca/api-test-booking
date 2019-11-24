package models;

import java.util.List;

public class BookingResult {

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    private List<Booking> bookings;

}
