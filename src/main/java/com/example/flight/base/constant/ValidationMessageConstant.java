package com.example.flight.base.constant;

public class ValidationMessageConstant {
    public static final String AIRLINE_MANDATORY_MESSAGE = "IATA Code must be 2 characters";
    public static final String AIRPORT_MANDATORY_MESSAGE ="IATA Code must be 3 characters";
    // Normally we can get airport and airline name from class.getName() with dynamicly and then
    // concatenate with static message to send class validation annotation.
    public static final String AIRPORT_NAME_MANDATORY_MESSAGE = "Airport name is mandotary";
    public static final String AIRLINE_NAME_MANDATORY_MESSAGE = "airline name is mandotary";
}
