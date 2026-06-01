package com.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Invoice;
import com.service.InvoiceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	private final InvoiceService invoiceService;

	public InvoiceController(InvoiceService invoiceService) {

		this.invoiceService = invoiceService;

	}


	@PostMapping("/buy")
	public Invoice comprar(@RequestParam String nameProduct, @RequestParam int qtdProductBuyed) {
		Invoice invoice = new Invoice();

		return invoiceService.addItem(nameProduct, qtdProductBuyed, invoice);
		
	}

}
