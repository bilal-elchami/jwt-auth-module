package com.live.bilalchami.jwtauthcomponent.security.model;

public class JwtToken {

    private String tokenValue;

    public JwtToken(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

}
