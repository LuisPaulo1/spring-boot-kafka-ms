package com.portal.api.controller;

import com.portal.api.dto.CarPostDTO;
import com.portal.api.message.KafkaProducerMessage;
import com.portal.api.service.CarPostStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarPostController {

	private final Logger LOG = LoggerFactory.getLogger(CarPostController.class);

	@Autowired
	private CarPostStoreService carPostStoreService;

	@Autowired
	private KafkaProducerMessage kafkaProducerMessage;

	@PostMapping("/post")
	public ResponseEntity<Void> postCarForSale(@RequestBody CarPostDTO carPostDTO) {
		LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Post information: {}", carPostDTO);
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
