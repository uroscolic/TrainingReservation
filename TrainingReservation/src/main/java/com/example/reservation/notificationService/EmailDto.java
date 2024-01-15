package com.example.reservation.notificationService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private String type;
    private String email;
    private List<String> params;
}
