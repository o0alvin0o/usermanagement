package com.myth.service;

import com.myth.dto.UserCreationDTO;
import org.apache.commons.lang3.StringUtils;
import net.datafaker.Faker;

import java.util.Random;

public class DataGenerator {

    private static final String[] mailProvider = new String[] {"@yahoo.com", "@hotmail.com", "@gmail.com", "@outlook.com", "@fakemail.com"};

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static UserCreationDTO fakeUserCreation() {
        String username = faker.internet().username();
        String password = StringUtils.capitalize(username) + "@2024";
        String email = username + mailProvider[randomIndex(mailProvider.length)];
        return new UserCreationDTO(username, password, email);
    }

    public static int randomIndex(int arrayLength) {
        return random.nextInt(arrayLength);
    }
}
