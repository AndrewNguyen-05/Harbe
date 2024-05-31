package com.harbe.notiservice.controller;

import com.harbe.notiservice.dto.model.NotiDto;
import com.harbe.notiservice.service.NotiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/noti")
public class NotiController {
    private NotiService notiService;

    @GetMapping("/shock/{productId}")
    public ResponseEntity<NotiDto> shockNoti(@PathVariable(name = "productId") long productId) {
        return new ResponseEntity<>(this.notiService.getNotiWithProduct(productId), HttpStatus.OK);
    }
}
