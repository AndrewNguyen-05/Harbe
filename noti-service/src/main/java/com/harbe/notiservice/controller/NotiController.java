package com.harbe.notiservice.controller;

import com.harbe.notiservice.dto.response.NotiResponseDto;
import com.harbe.notiservice.utils.AppConstants;
import com.harbe.notiservice.dto.model.NotiDto;
import com.harbe.notiservice.dto.response.ObjectResponse;
import com.harbe.notiservice.service.NotiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotiController {
    private NotiService notiService;

    @GetMapping("/shock/{productId}")
    public ResponseEntity<NotiDto> shockNoti(@PathVariable(name = "productId") long productId) {
        return new ResponseEntity<>(this.notiService.getNotiWithProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/{notiId}")
    public ResponseEntity<NotiDto> getNoti(@PathVariable(name = "notiId") long notiId) {
        return new ResponseEntity<>(this.notiService.getNotificationById(notiId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ObjectResponse<NotiDto>> getAllNotifications(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return new ResponseEntity<>(this.notiService.getAllNotifications(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NotiDto> createNotification(@RequestBody NotiResponseDto notiDto) {
        return new ResponseEntity<>(this.notiService.createNotification(notiDto), HttpStatus.CREATED);
    }

    @PutMapping("/{notiId}")
    public ResponseEntity<NotiDto> updateNotification(
            @PathVariable(name = "notiId") long notiId,
            @RequestBody NotiResponseDto notiDto) {
        return new ResponseEntity<>(this.notiService.updateNotification(notiId, notiDto), HttpStatus.OK);
    }

    @DeleteMapping("/{notiId}")
    public ResponseEntity<String> deleteNotification(@PathVariable(name = "notiId") long notiId) {
        this.notiService.deleteNotification(notiId);
        return new ResponseEntity<>("Delete notification successfully!", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ObjectResponse<NotiDto>> searchNoti(
            @RequestParam("title") String title,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(this.notiService.searchNoti(title, pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
}
