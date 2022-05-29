package com.example.amcef.users.data;

import com.example.amcef.users.address.Address;
import com.example.amcef.users.Company;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

}
