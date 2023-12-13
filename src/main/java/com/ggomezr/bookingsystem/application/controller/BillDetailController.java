package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.BillDetailService;
import com.ggomezr.bookingsystem.domain.dto.BillDetailDto;
import com.ggomezr.bookingsystem.domain.entity.BillDetail;
import com.ggomezr.bookingsystem.domain.exceptions.BillDetailNotFoundException;
import com.ggomezr.bookingsystem.domain.exceptions.ReservationNotFoundException;
import com.ggomezr.bookingsystem.domain.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bill-detail")
public record BillDetailController(BillDetailService billDetailService, ReservationRepository reservationRepository) {

    @GetMapping("/bill-details")
    public List<BillDetail> getAllBillDetails(){
        return billDetailService.getAllBillDetails();
    }

    @GetMapping("/bill-details/{id}")
    public Optional<BillDetail> getBillDetailById(@PathVariable Integer id) throws BillDetailNotFoundException {
        return billDetailService.getBillDetailById(id);
    }

    @PostMapping("/bill-details")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBillDetail(@RequestBody BillDetailDto billDetailDto) throws ReservationNotFoundException {
        billDetailService.createBillDetail(billDetailDto);
    }

    @PutMapping("/bill-details/{id}")
    public void updateBillDetail(@PathVariable Integer id, @RequestBody BillDetailDto billDetailDto) throws BillDetailNotFoundException, ReservationNotFoundException {
        billDetailService.updateBillDetail(id, billDetailDto);
    }

    @DeleteMapping("/bill-details/{id}")
    public void deleteBillDetail(@PathVariable Integer id){
        billDetailService.deleteBillDetail(id);
    }
}
