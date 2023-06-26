package com.portal.api.controller;

import java.util.List;

import com.portal.api.dto.CarPostDTO;
import com.portal.api.message.KafkaProducerMessage;
import com.portal.api.service.CarPostStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/car")
public class CarPostController {

	@Autowired
	private CarPostStoreService carPostStoreService;

	@Autowired
	private KafkaProducerMessage kafkaProducerMessage;

	@PostMapping("/post")
	public ResponseEntity<Void> postCarForSale(@RequestBody CarPostDTO carPostDTO) {
		kafkaProducerMessage.sendMessage(carPostDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/posts")
	public ResponseEntity<List<CarPostDTO>> getCarSales() {
		return ResponseEntity.status(HttpStatus.FOUND).body(carPostStoreService.getCarForSales());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> changeCarSale(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id) {
		carPostStoreService.changeCarForSale(carPostDTO, id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCaForSale(@PathVariable("id") String id) {
		carPostStoreService.removeCarForSale(id);
		return ResponseEntity.ok().build();
	}
}
