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
