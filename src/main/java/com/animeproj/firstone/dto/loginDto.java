package com.animeproj.firstone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class loginDto {
    private String email;
    private String password;
}