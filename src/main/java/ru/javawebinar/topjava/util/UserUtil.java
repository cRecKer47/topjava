package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "Admin", "topjava@emal.com", "12345", Role.ROLE_ADMIN),
            new User(null, "User", "topjava1@emal.com", "54321", Role.ROLE_USER),
            new User(null, "user2", "topjava2@emal.com", "45123", Role.ROLE_USER),
            new User(null, "SuperUser", "topjava3@emal.com", "52080", Role.ROLE_ADMIN, Role.ROLE_USER)
            );
}
