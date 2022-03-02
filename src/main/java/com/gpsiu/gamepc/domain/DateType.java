package com.gpsiu.gamepc.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DateType {
    WEEKDAY, HOLIDAY;

    @JsonCreator
    public static DateType fromValue(String value) {
        switch (value) {
            case "WEEKDAY":
                return DateType.WEEKDAY;
            case "HOLIDAY":
                return DateType.HOLIDAY;
        }
        return null;
    }

}