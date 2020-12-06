package com.mmpr.PedalivDaliBackend.payload;

import lombok.Data;

@Data
public class LoginSignupRequest {
    private String login;

    private String email;

    private String password;
}
