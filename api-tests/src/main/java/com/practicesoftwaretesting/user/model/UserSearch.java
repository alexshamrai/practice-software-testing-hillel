package com.practicesoftwaretesting.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearch {

    private int currentPage;
    private List<RegisterUserResponse> data;
    private int from;
    private int lastPage;
    private int perPage;
    private int to;
    private int total;
}
