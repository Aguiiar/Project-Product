package com.service;

import org.springframework.stereotype.Service;

import com.entity.Invoice;
import com.entity.ItemProduct;
import com.entity.Product;
import com.repository.InvoiceRepository;
import com.repository.ItemProductRepository;
import com.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

	private final InvoiceRepository invoiceRepository;
	private final ProductRepository productRepository;
	private final ItemProductRepository itemProductRepository;

	public InvoiceService(InvoiceRepository invoiceRepository, ProductRepository productRepository,
			ItemProductRepository itemProductRepository) {

		this.invoiceRepository = invoiceRepository;
		this.productRepository = productRepository;
		this.itemProductRepository = itemProductRepository;
	}

	@Transactional
	public void save(Invoice invoice) {
		invoiceRepository.save(invoice);
	}

	


	public Invoice addItem(String productName, int qtdProductBuyed, Invoice invoice) {

		
		Invoice currentInvoice = invoiceRepository.findTopByOrderByIdDesc().orElseGet(() -> {
			Invoice newInvoice = new Invoice();
			newInvoice.setPrice(0.0);
			return invoiceRepository.save(newInvoice);

		});

		
	
		Product product = productRepository.findAll().stream().filter(p -> p.getName().equalsIgnoreCase(productName))
				.findFirst().orElseThrow(() -> new RuntimeException("Product not found"));

	
		if (qtdProductBuyed <= 0) {
			throw new RuntimeException("Quantity invalid");

		}


		if (product.getEstoque() < qtdProductBuyed) {
			throw new RuntimeException("stock invalid");
		}


		product.setEstoque(product.getEstoque() - qtdProductBuyed);
		productRepository.save(product);

	
		ItemProduct item = new ItemProduct();
		item.setInvoice(currentInvoice);
		item.setProduct(product);
		item.setQuantity(qtdProductBuyed);
		itemProductRepository.save(item);

	
		double totaItem = product.getPrice() * qtdProductBuyed;
		currentInvoice.setPrice(currentInvoice.getPrice() + totaItem);


	
		return invoiceRepository.save(currentInvoice);

	}
}
