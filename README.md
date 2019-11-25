# api-test-booking

# qa-test-hf

## Overall informartion

Automation test project to cover api tests:

    **getBookings**: Test that at least 2 existing bookings are returned in the response. 

    **getBooking**: Test that the data returned for an existing booking matches.

    **createBooking**: Test that bookings can be created.

#### Classes of test and test methods:

    BookingClientTest
        testGetBookingsShouldReturnAtLeastTwoExistingBookings()
        testGetBookingReturnCorrectInformation()
        testThatBookingsCanBeCreated()

## Execution and Technical information

This project uses Maven as build platform so the test can be executed using command line:

    mvn clean test

The code is in **Java** and **TestNG** for tests.

#### Issues:

1) https://automationintesting.online/booking/swagger-ui.html#/booking-controller/createBookingUsingPOST does not contain possible response 409(conflict) for this method. For the scenario bellow for example, this response is returned but not documented:
- A room cannot be booked more than once for a given date.

2) https://automationintesting.online/booking/swagger-ui.html#/booking-controller contains an extra "/" in the end of URL as you can see here `curl -X GET "https://automationintesting.online/booking/?roomid=1" -H "accept: */*"`
Should it be like this? 
