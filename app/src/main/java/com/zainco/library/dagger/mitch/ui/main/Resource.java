package com.zainco.library.dagger.mitch.ui.main;

import androidx.annotation.Nullable;


import org.jetbrains.annotations.NotNull;

/*
 *  a Wrapper class for the Posts/Profile api requests
 * */
public class Resource<T> {
    @NotNull
    public final Status status;
    @Nullable public final T data;
    @Nullable public final String message;

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@NotNull String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }


    public enum Status {
        SUCCESS, ERROR, LOADING
    }
}
