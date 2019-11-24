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

        Booking firstBooking = bookingClient.createBookingForSpecificRoom(1); //which room should I select?
        Booking secondBooking = bookingClient.getBookingByRoomIdAndBookingId(firstBooking.getBookingid(), firstBooking.getRoomid());

        Assert.assertSame( firstBooking, secondBooking );
    }





    @Test
    public void testSameRoomCanNotBeBookedMoreThanOnceForGivenDate() throws ClientProtocolException, IOException {
        String payload = "{ \"bookingdates\": {" +
                "\"checkin\": \"2019-11-07\", " +
                "\"checkout\": \"2019-11-09\" }, " +
                "\"bookingid\": 7779," +
                "\"depositpaid\": true," +
                "\"email\": \"test@gmail.com\"," +
                "\"firstname\": \"666\"," +
                "\"lastname\": \"777\"," +
                "\"roomid\": 28" +
                "}";

        StringEntity entity = new StringEntity(
                payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://automationintesting.online/booking/");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),201);

        payload = "{ \"bookingdates\": {" +
                "\"checkin\": \"2019-11-07\", " +
                "\"checkout\": \"2019-11-09\" }, " +
                "\"bookingid\": 7770," +
                "\"depositpaid\": true," +
                "\"email\": \"test1@gmail.com\"," +
                "\"firstname\": \"222\"," +
                "\"lastname\": \"223\"," +
                "\"roomid\": 28" +
                "}";

        entity = new StringEntity(
                payload,
                ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        response = httpClient.execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),409);

    }

    @Test
    public void testCheckOutDateMustBeGreaterThanTheCheckInDate() throws ClientProtocolException, IOException {
        String payload = "{ \"bookingdates\": {" +
                "\"checkin\": \"2019-12-09\", " +
                "\"checkout\": \"2019-12-08\" }, " +
                "\"bookingid\": 7779," +
                "\"depositpaid\": true," +
                "\"email\": \"test@gmail.com\"," +
                "\"firstname\": \"666\"," +
                "\"lastname\": \"777\"," +
                "\"roomid\": 04" +
                "}";

        StringEntity entity = new StringEntity(
                payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://automationintesting.online/booking/");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
    }


}
