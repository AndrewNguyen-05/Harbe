package com.harbe.authservice.service;

import com.harbe.authservice.dto.model.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(long userId, AddressDto addressDto);
    AddressDto getAddressById(long id);
    public List<AddressDto> getAddressByUserId(long userId);
    AddressDto updateAddress(long userId, AddressDto addressDto, long id);
    String deleteAddress(long id);
}
