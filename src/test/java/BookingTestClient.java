import models.Booking;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import service_client.BookingClient;

import java.io.IOException;
import java.util.List;

public class BookingTestClient {

    @Test
    public void testGetBookingsShouldReturnAtLeastTwoExistingBookings() throws IOException {
        BookingClient bookingClient = new BookingClient();
        List<Booking> bookings = bookingClient.getBookings();

        Assert.assertTrue( bookings.size() >= 2 );
    }

    @Test
    public void testGetBookingReturnCorrectInformation() throws IOException {
        BookingClient bookingClient = new BookingClient();
        Booking firstBooking = bookingClient.createBookingForAnyRoom(); //which room should I select?
        Booking secondBooking = bookingClient.getBookingByRoomIdAndBookingId(firstBooking.getBookingid(), firstBooking.getRoomid());

        Assert.assertTrue(
                firstBooking.getBookingid() == secondBooking.getBookingid() &&
                firstBooking.getRoomid() == secondBooking.getRoomid() );
    }

    @Test
    public void testThatBookingsCanBeCreated() throws IOException {
        BookingClient bookingClient = new BookingClient();
        Booking firstBooking = bookingClient.createBookingForAnyRoom();

        Assert.assertTrue(bookingClient.getBookingByRoomIdAndBookingId(firstBooking.getBookingid(), firstBooking.getRoomid()) != null);
    }

}
