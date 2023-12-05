package com.ggomezr.bookingsystem;

import com.ggomezr.bookingsystem.application.service.ClientService;
import com.ggomezr.bookingsystem.application.service.ClientServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingSystemApplication.class, args);
	}

}
