package models;

import java.time.LocalDate;

public class BookingDates {

    public String checkin;
    public String checkout;

    public BookingDates(LocalDate checkin, LocalDate checkout) {
        this.checkin = checkin.toString();
        this.checkout = checkout.toString();
    }

    public BookingDates() {
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
