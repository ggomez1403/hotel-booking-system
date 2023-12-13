package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.BillService;
import com.ggomezr.bookingsystem.domain.dto.BillDto;
import com.ggomezr.bookingsystem.domain.entity.Bill;
import com.ggomezr.bookingsystem.domain.exceptions.BillDetailNotFoundException;
import com.ggomezr.bookingsystem.domain.exceptions.BillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/bill")
public record BillController(BillService billService) {

    @GetMapping("/bills")
    public List<Bill> getAllBills(){
        return billService.getAllBills();
    }

    @GetMapping("/bills/{id}")
    public Optional<Bill> getBillById(@PathVariable Integer id) throws BillNotFoundException{
        return billService.getBillById(id);
    }

    @GetMapping
    public List<Bill> getBillsByIssuedDate(@RequestParam LocalDate issuedDate){
        return billService.getBillsByIssuedDate(issuedDate);
    }

    @PostMapping("/bills")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBill(@RequestBody BillDto billDto) throws BillDetailNotFoundException {
        billService.createBill(billDto);
    }

    @PutMapping("/bills/{id}")
    public void updateBill(@PathVariable Integer id, @RequestBody BillDto billDto) throws  BillNotFoundException{
        billService.updateBill(id, billDto);
    }

    @DeleteMapping("/bills/{id}")
    public void deleteBill(@PathVariable Integer id){
        billService.deleteBill(id);
    }
}
