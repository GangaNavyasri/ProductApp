package com.capgemini.productapp.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exceptions.ProductNotFoundException;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;
import com.capgemini.productapp.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productRepository).build();

	}
	/*
	 * @Test public void testaddProduct() { Product product = new Product(123,
	 * "xyz", "yyy", 450);
	 * when(productRepository.save(product)).thenReturn(product);
	 * assertEquals(productServiceImpl.addProduct(product), product);
	 * System.out.println(product); }
	 */
/*
	@Test
	public void testfindProductById() throws ProductNotFoundException {
		Product product = new Product(123, "xyz", "yyy", 450);
		Optional<Product> product1 = Optional.of(product);
		when(productRepository.findById(123)).thenReturn(product1);
		assertEquals(productServiceImpl.findProductById(123), product);
		System.out.println(product);
	}*/

	@Test
	public void testUpdateProduct() throws ProductNotFoundException {
		Product product = new Product(123, "www", "yyy", 500);
		Product product2 = new Product(123, "xyz", "yyy", 450);
		when(productRepository.save(product)).thenReturn(product2);
		assertEquals(productServiceImpl.updateProduct(product), product2);
	}
	@Test
	public void testDeleteProduct() {
//		Product product = new Product(123, "www", "yyy", 500);
		productServiceImpl.deleteProduct(12345);
		verify(productRepository, times(1)).deleteById(12345);
}
}
