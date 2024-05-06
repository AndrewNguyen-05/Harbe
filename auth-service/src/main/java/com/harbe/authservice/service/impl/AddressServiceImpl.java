package com.harbe.authservice.service.impl;

import com.harbe.authservice.dto.mapper.AddressMapper;
import com.harbe.authservice.dto.model.AddressDto;
import com.harbe.authservice.entity.Address;
import com.harbe.authservice.entity.User;
import com.harbe.authservice.repository.AddressRepository;
import com.harbe.authservice.repository.UserRepository;
import com.harbe.authservice.service.AddressService;
import com.harbe.authservice.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;
    private UserRepository userRepository;

    @Override
    public AddressDto createAddress(long userId, AddressDto addressDto) {
        Address address = addressMapper.mapToEntity(addressDto);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));

        address.setUser(user);

        Address newAddress = addressRepository.save(address);
        return addressMapper.mapToDto(newAddress);
    }

    @Override
    public AddressDto getAddressById(long id) {
        Address address = addressRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Address", "id", id));
        return addressMapper.mapToDto(address);
    }

    @Override
    public List<AddressDto> getAddressByUserId(long userId){
        List<Address> addresses = addressRepository.findByUserId(userId);
        List<AddressDto> addressDtoList = addresses.stream()
                .map(address -> addressMapper.mapToDto(address))
                .toList();
        return addressDtoList;
    }

    @Override
    public AddressDto updateAddress(long userId, AddressDto addressDto, long id) {
        Address address = addressMapper.mapToEntity(addressDto);


        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));

        address.setUser(user);
        address.setId(id);

        Address resultAddress = addressRepository.save(address);
        return addressMapper.mapToDto(resultAddress);
    }

    @Override
    public String deleteAddress(long id) {
        addressRepository.deleteById(id);
        return null;
    }
}
