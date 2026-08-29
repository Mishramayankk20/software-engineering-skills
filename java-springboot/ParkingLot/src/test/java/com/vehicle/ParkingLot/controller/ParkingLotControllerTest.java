package com.vehicle.ParkingLot.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.ParkingLot.dto.ExitVehicleResponse;
import com.vehicle.ParkingLot.dto.ParkVehicleRequest;
import com.vehicle.ParkingLot.dto.ParkVehicleResponse;
import com.vehicle.ParkingLot.entity.ParkingFloor;
import com.vehicle.ParkingLot.entity.ParkingSpot;
import com.vehicle.ParkingLot.enums.SpotType;
import com.vehicle.ParkingLot.repository.ParkingSpotRepository;
import com.vehicle.ParkingLot.service.ParkingLotService;

@WebMvcTest(ParkingLotController.class)
class ParkingLotControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ParkingLotService parkingLotService;

	@MockitoBean
	private ParkingSpotRepository parkingSpotRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void parkVehicle_shouldReturnSuccess() throws Exception {

		ParkVehicleRequest request = new ParkVehicleRequest();

		ParkVehicleResponse response = new ParkVehicleResponse("TICKET123", "MP04AB1234", 1, 10,
				LocalDateTime.of(2026, 8, 26, 10, 30));

		when(parkingLotService.parkVehicle(any(ParkVehicleRequest.class))).thenReturn(response);

		mockMvc.perform(post("/api/v1/parking/park").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());

		verify(parkingLotService).parkVehicle(any(ParkVehicleRequest.class));
	}

	@Test
	void exitVehicle_shouldReturnSuccess() throws Exception {

		String ticketNumber = "TICKET123";

		ExitVehicleResponse response = new ExitVehicleResponse("TICKET123", "MP04AB1234",
				LocalDateTime.of(2026, 8, 26, 10, 30), LocalDateTime.of(2026, 8, 26, 14, 30), 4L, 80.0);

		when(parkingLotService.exitVehicle(ticketNumber)).thenReturn(response);

		mockMvc.perform(post("/api/v1/parking/exit/{ticketNumber}", ticketNumber)).andExpect(status().isOk());

		verify(parkingLotService).exitVehicle(ticketNumber);
	}

	@Test
	void getAvailableSpots_shouldReturnMatchingSpots() throws Exception {

		ParkingFloor floor = new ParkingFloor(1, null);

		ParkingSpot spot1 = new ParkingSpot(10, SpotType.CAR, floor);

		ParkingSpot spot2 = new ParkingSpot(20, SpotType.BIKE, floor);

		when(parkingSpotRepository.findByOccupiedFalse()).thenReturn(List.of(spot1, spot2));

		mockMvc.perform(get("/api/v1/parking/available-spots").param("type", "CAR")).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].spotNumber").value(10)).andExpect(jsonPath("$[0].spotType").value("CAR"));

		verify(parkingSpotRepository).findByOccupiedFalse();
	}
}