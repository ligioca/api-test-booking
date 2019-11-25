package service_client;

import com.google.gson.Gson;
import helper.RetrieveUtil;
import models.Booking;
import models.AllBookings;
import models.CreateBookingResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class BookingClient {

    public List<Booking> getBookings() throws IOException {
        HttpUriRequest request = new HttpGet( "https://automationintesting.online/booking/" );
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        AllBookings booking = RetrieveUtil.retrieveResourceFromResponse(response, AllBookings.class);

        return booking.getBookings();
    }

    public Booking getBookingByBookingId(int bookingId) throws IOException {
        HttpUriRequest request = new HttpGet("https://automationintesting.online/booking/" + bookingId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        return RetrieveUtil.retrieveResourceFromResponse(response, Booking.class);
    }

    public Booking createBooking(Booking newBooking) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(newBooking);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

        HttpPost request = new HttpPost("https://automationintesting.online/booking/");
        request.setEntity(entity);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        CreateBookingResult booking = RetrieveUtil.retrieveResourceFromResponse(
                response, CreateBookingResult.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),201);

        return booking.getBooking();
    }
}
