package com.example.twitter.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter@Setter
public class CaptchaResponsDto {
    private boolean success;
    @JsonAlias("error-codes")
    private Set<String> errorCodes;

    public boolean isSuccess() {
        return success;
    }
}
