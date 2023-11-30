package com.metazz.metazzspace.common.util;

import com.metazz.metazzspace.model.entity.User;

public class UserUtil {

    private static final ThreadLocal<User> USER = new ThreadLocal<>();

    public static void setUser(User user) {
        USER.set(user);
    }

    public static User getUser() {
        return USER.get();
    }

    public static void removeUser() {
        USER.remove();
    }

}
