package com.example.bookstore.service;

import com.example.bookstore.entity.Address;
import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.User;
import com.example.bookstore.exceptions.BookStoreException;
import com.example.bookstore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address save(long id,Address address) {
        User foundUser = addressRepository.findByIdUser(id).orElseThrow(()-> new BookStoreException("User not found", HttpStatus.BAD_REQUEST));
        // User foundUser = userService.findUserById(id);
        foundUser.addAddress(address);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getUserAddresses(long id) {
        User foundUser = addressRepository.findByIdUser(id).orElseThrow(()-> new BookStoreException("User not found", HttpStatus.BAD_REQUEST));
        return foundUser.getAdressess();
    }

    @Override
    public Address update(long id, Address address) {
        if(addressRepository.findById(id).isPresent()) {
            address.setId(id);
            address.setUsers(addressRepository.findById(id).get().getUsers());
            return addressRepository.save(address);

        }
        else {
            throw new BookStoreException("The address you are trying to update could not be found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Address delete(long id) {
        Address deletedAddress = addressRepository.findById(id).orElseThrow(()->new BookStoreException("Address not found",HttpStatus.BAD_REQUEST));
        addressRepository.delete(deletedAddress);
        return deletedAddress;
    }
}
