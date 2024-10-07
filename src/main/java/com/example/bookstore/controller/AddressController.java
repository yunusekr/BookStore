package com.example.bookstore.controller;

import com.example.bookstore.dto.AddressDto;
import com.example.bookstore.entity.Address;
import com.example.bookstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("user/{userid}/address")
public class AddressController{
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDto> getUserAddresses(@PathVariable long userid){
        return addressService.getUserAddresses(userid).stream().map(address -> new AddressDto(
                address.getId(),
                address.getCity(),
                address.getDistrict(),
                address.getBuildno(),
                address.getAptno())).collect(Collectors.toList());
    }

    @PostMapping
    public AddressDto save(@PathVariable long userid,@RequestBody Address address){
        Address foundAddress = addressService.save(userid,address);
        return new AddressDto(foundAddress.getId(),
                foundAddress.getCity(),
                foundAddress.getDistrict(),
                foundAddress.getBuildno(),
                foundAddress.getAptno());
    }

    @PutMapping("/{id}")
    public AddressDto update(@PathVariable long id,@RequestBody Address address){
        Address foundAddress = addressService.update(id,address);
        return new AddressDto(foundAddress.getId(),
                foundAddress.getCity(),
                foundAddress.getDistrict(),
                foundAddress.getBuildno(),
                foundAddress.getAptno());
    }

    @DeleteMapping("/{id}")
    public AddressDto delete(@PathVariable long id){
          Address foundAddress = addressService.delete(id);
        return new AddressDto(foundAddress.getId(),
                foundAddress.getCity(),
                foundAddress.getDistrict(),
                foundAddress.getBuildno(),
                foundAddress.getAptno());
    }

}
