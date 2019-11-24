package service_client;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.google.gson.Gson;
import helper.RetrieveUtil;
import models.Booking;
import models.BookingDates;
import models.AllBookings;
import models.BookingResultFromCreate;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BookingClient {

    public List<Booking> getBookings() throws IOException {
        HttpUriRequest request = new HttpGet( "https://automationintesting.online/booking/" );
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        AllBookings booking = RetrieveUtil.retrieveResourceFromResponse(
                response, AllBookings.class);

        return booking.getBookings();
    }

    public Booking getBookingByRoomIdAndBookingId(int bookingId, int roomId) throws IOException {
        Booking booking = new Booking();
        HttpUriRequest request = new HttpGet("https://automationintesting.online/booking?roomid=" + roomId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        AllBookings bookings = RetrieveUtil.retrieveResourceFromResponse(
                response, AllBookings.class);

        for (Booking i : bookings.getBookings()) {
            if (i.getBookingid() == bookingId) {
                booking = i;
            }
        }
        return booking; //and if it doesn' find the booking?
    }


    public Booking createBookingForSpecificRoom(int roomId) throws IOException {
        String checkin = "2019-12-07";
        String checkout = "2019-12-08";

        Gson gson = new Gson();
        BookingDates bookingDates = new BookingDates(checkin,checkout);
        Booking bookingPayload = new Booking(roomId,"FirstName","LastName",true, bookingDates);
        String json = gson.toJson(bookingPayload);

        StringEntity entity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON);

        HttpPost request = new HttpPost("https://automationintesting.online/booking/");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        BookingResultFromCreate booking = RetrieveUtil.retrieveResourceFromResponse(
                response, BookingResultFromCreate.class);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),201);

        return booking.getBooking();
    }

    public Booking createBookingForAnyRoom() throws IOException {
        Random random = new Random();
        String checkin = "2019-12-" + random.nextInt(29);
        String checkout = "2019-12-30";
        int roomId = random.nextInt(99);
        Gson gson = new Gson();
        BookingDates bookingDates = new BookingDates(checkin,checkout);
        Booking bookingPayload = new Booking(roomId,"FirstName","LastName",true, bookingDates);
        String json = gson.toJson(bookingPayload);

        StringEntity entity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON);

        HttpPost request = new HttpPost("https://automationintesting.online/booking/");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        BookingResultFromCreate booking = RetrieveUtil.retrieveResourceFromResponse(
                response, BookingResultFromCreate.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),201);

        return booking.getBooking();
    }

}
