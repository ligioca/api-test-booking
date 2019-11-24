package service_client;

import helper.RetrieveUtil;
import models.Booking;
import models.BookingResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookingClient {

    public List<Booking> getBookings() throws IOException {
        HttpUriRequest request = new HttpGet( "https://automationintesting.online/booking/" );
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        BookingResult booking = RetrieveUtil.retrieveResourceFromResponse(
                response, BookingResult.class);

        return booking.getBookings();
    }

//    public Booking getBookingByRoomId() throws IOException {
//
//    }

    public Booking getBookingByRoomIdAndBookingId(int bookingId, int roomId) throws IOException {
        Booking booking = new Booking();
        HttpUriRequest request = new HttpGet("https://automationintesting.online/booking/?roomid=" + roomId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        BookingResult bookings = RetrieveUtil.retrieveResourceFromResponse(
                response, BookingResult.class);

        for (Booking i : bookings.getBookings()) {
            if (i.getBookingid() == bookingId) {
                booking = i;
            }
        }
        return booking; //and if it doesn' find the booking?
    }


    public Booking createBookingForSpecificRoom(int roomId) throws IOException {

        String payload = "{ \"bookingdates\": {" +
                "\"checkin\": \"2019-11-07\", " +
                "\"checkout\": \"2019-11-09\" }, " +
                "\"bookingid\": 7779," +
                "\"depositpaid\": true," +
                "\"email\": \"test@gmail.com\"," +
                "\"firstname\": \"666\"," +
                "\"lastname\": \"777\"," +
                "\"roomid\": " + roomId +
                "}";

        StringEntity entity = new StringEntity(
                payload,
                ContentType.APPLICATION_JSON);

        HttpPost request = new HttpPost("https://automationintesting.online/booking/");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Booking booking = RetrieveUtil.retrieveResourceFromResponse(
                response, Booking.class);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),201);

        return new Booking();
    }

}
