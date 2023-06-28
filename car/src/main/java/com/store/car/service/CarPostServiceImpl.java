package com.store.car.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.store.car.dto.CarPostDTO;
import com.store.car.entity.CarPostEntity;
import com.store.car.entity.OwnerPostEntity;
import com.store.car.repository.CarPostRepository;
import com.store.car.repository.OwnerPostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarPostServiceImpl implements  CarPostService {

    @Autowired
    private CarPostRepository carPostRepository;

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    @Override
    public void newPostDetails(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = new CarPostEntity();

        OwnerPostEntity ownerPostEntity = ownerPostRepository.findById(carPostDTO.getOwnerId())
            .orElseThrow(() -> new NoSuchElementException("No such owner found"));

        carPostEntity.setOwnerPost(ownerPostEntity);
        carPostEntity.setCreatedDate(String.valueOf(new Date()));
        carPostEntity.setContact(ownerPostEntity.getContactNumber());
        BeanUtils.copyProperties(carPostDTO, carPostEntity,"contact");

        carPostRepository.save(carPostEntity);
    }

    @Override
    public List<CarPostDTO> getCarSales() {
        List<CarPostEntity> listCarPostEntity = carPostRepository.findAll();
        return listCarPostEntity.stream().map(carPostEntity -> {
            CarPostDTO carPostDTO = new CarPostDTO();
            BeanUtils.copyProperties(carPostEntity, carPostDTO);
            return carPostDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void changeCarSale(CarPostDTO carPostDTO, Long postId) {
        CarPostEntity carPostEntityAtual = carPostRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("No such post found"));
        BeanUtils.copyProperties(carPostDTO, carPostEntityAtual);
        carPostRepository.save(carPostEntityAtual);
    }

    @Override
    public void removeCarSale(Long postId) {
        carPostRepository.deleteById(postId);
    }
}
