package com.example.bookstore.service;

import com.example.bookstore.entity.Address;

import java.util.List;

public interface AddressService {
    Address save(long id,Address address);

    List<Address> getUserAddresses(long id);

    Address update(long id,Address address);

    Address delete(long id);
}
