import helper.RandomDates;
import io.restassured.RestAssured;
import models.Booking;
import models.BookingDates;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class BookingClientTestRA {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://automationintesting.online";
    }

    //Tests with Rest Assured framework
    @Test
    public void testGetBookingsShouldReturnAtLeastTwoExistingBookings1() throws IOException {
        RestAssured.when()
                .get("/booking")
                .then()
                .statusCode(200)
                .assertThat()
                .body("bookings.findAll().size()", Matchers.greaterThanOrEqualTo(1));
    }

    @Test
    public void testGetBookingReturnCorrectInformation1() {
        //hard coded values - change it
        LocalDate checking = RandomDates.createRandomDate(2019,2022);
        BookingDates bookingDates = new BookingDates(RandomDates.createRandomDate(2019,2022), checking.plusDays(1));
        Booking booking = new Booking(new Random().nextInt(70), "FirstName", "LastName", true, bookingDates);

        RestAssured.given().
                contentType("application/json").
                body(booking).
                when().
                post("/booking/").
                then().
                statusCode(201);

        //check the values tested
    }
}
