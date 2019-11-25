import helper.RandomDates;
import models.Booking;
import models.BookingDates;
import org.testng.Assert;
import org.testng.annotations.Test;
import service_client.BookingClient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class BookingClientTest {

    @Test
    public void testGetBookingsShouldReturnAtLeastTwoExistingBookings() throws IOException {
        BookingClient bookingClient = new BookingClient();
        List<Booking> bookings = bookingClient.getBookings();

        Assert.assertTrue( bookings.size() >= 2 );
    }

    @Test
    public void testGetBookingReturnCorrectInformation() throws IOException {
        LocalDate checking = RandomDates.createRandomDate(2019,2020);
        BookingDates bookingDates = new BookingDates(checking, checking.plusDays(1));
        Booking booking = new Booking(new Random().nextInt(50), "FirstName", "LastName", true, bookingDates);

        BookingClient bookingClient = new BookingClient();
        Booking firstBooking = bookingClient.createBooking(booking);
        Booking secondBooking = bookingClient.getBookingByBookingId(firstBooking.getBookingid());

        Assert.assertEquals(firstBooking.getBookingid(),secondBooking.getBookingid());
        Assert.assertEquals(firstBooking.getRoomid(), secondBooking.getRoomid());
        Assert.assertEquals(firstBooking.getFirstname(), secondBooking.getFirstname());
        Assert.assertEquals(firstBooking.getLastname(), secondBooking.getLastname());
        Assert.assertEquals(firstBooking.getBookingdates().getCheckin(), secondBooking.getBookingdates().getCheckin());
        Assert.assertEquals(firstBooking.getBookingdates().getCheckout(), secondBooking.getBookingdates().getCheckout());

    }

    @Test
    public void testThatBookingsCanBeCreated() throws IOException {
        LocalDate checking = RandomDates.createRandomDate(2019,2020);
        BookingDates bookingDates = new BookingDates(checking, checking.plusDays(1));
        Booking booking = new Booking(new Random().nextInt(50), "FirstName", "LastName", true, bookingDates);

        BookingClient bookingClient = new BookingClient();
        Booking firstBooking = bookingClient.createBooking(booking);

        Assert.assertNotNull(bookingClient.getBookingByBookingId(firstBooking.getBookingid()));
    }

}
