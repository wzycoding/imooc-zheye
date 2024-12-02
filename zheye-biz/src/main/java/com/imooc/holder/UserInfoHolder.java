package com.imooc.holder;

import com.imooc.entity.User;

/**
 * @author wzy
 * @date 2024/12/2 15:28
 */
public class UserInfoHolder {
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void set(User user) {
        userThreadLocal.set(user);
    }

    public static void remove() {
        userThreadLocal.remove();
    }

    public static User getUserInfo() {
        return userThreadLocal.get();
    }
}
