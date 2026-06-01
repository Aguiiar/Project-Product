package com.service;

import org.springframework.stereotype.Service;

import com.entity.ItemProduct;
import com.repository.ItemProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ItemProductService {

	private ItemProductRepository itemProductRepository;

	public ItemProductService(ItemProductRepository itemProductRepository) {

		this.itemProductRepository = itemProductRepository;
	}


	@Transactional
	public void save(ItemProduct item) {
		itemProductRepository.save(item);
	}

}
