package com.gpsiu.gamepc.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ROLE_USER, ROLE_ADMIN;

    @JsonCreator
    public static Role fromValue(String value) {
        switch (value) {
            case "ROLE_USER":
                return Role.ROLE_USER;
            case "ROLE_ADMIN":
                return Role.ROLE_ADMIN;
        }
        return null;
    }
}
