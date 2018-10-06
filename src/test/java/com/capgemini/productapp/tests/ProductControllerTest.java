package com.capgemini.productapp.tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductControllerTest {
	@Mock
	private ProductService productService;
	@InjectMocks
	private ProductController productController;

	private MockMvc mockMvc;

	Product product;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

	}

	@Test
	public void testAddProduct() throws Exception {
		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(new Product(123, "xxx", "yyy", 234));
		
		String content = "{\r\n" + 
				"	\"productId\":123,\r\n" + 
				"	\"productName\":\"xxx\",\r\n" + 
				"	\"productCategory\":\"yyy\",\r\n" + 
				"	\"productPrice\":234\r\n" + 
				"}";
		
		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(123))
				.andExpect(jsonPath("$.productName").value("xxx"))
				.andExpect(jsonPath("$.productCategory").value("yyy"))
				.andExpect(jsonPath("$.productPrice").value(234))
				.andDo(print());

	}
	/*@Test
	public void testFindProductById()throws Exception {
		when(productService.findProductById(123)).thenReturn(new Product(123, "xxx", "yyy", 234));
		mockMvc.perform(get("/product/123").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(123))
				.andExpect(jsonPath("$.productName").value("xxx"))
				.andExpect(jsonPath("$.productCategory").value("yyy"))
				.andExpect(jsonPath("$.productPrice").value(234))
		        .andDo(print());
		
	}*/
/*	@Test
public void testUpdateProduct() throws Exception{
	when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(new Product(123, "xyz", "yyy", 450));
	when(productService.findProductById(123)).thenReturn(new Product(1234, "xxx", "yyy", 234));

	String content = "{\r\n" + 
			"	\"productId\":1234,\r\n" + 
			"	\"productName\":\"xyz\",\r\n" + 
			"	\"productCategory\":\"yyy\",\r\n" + 
			"	\"productPrice\":450\r\n" + 
			"}";
	
	mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON).content(content)
			.accept(MediaType.APPLICATION_JSON))
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.productId").exists())
	.andExpect(jsonPath("$.productName").exists())
	.andExpect(jsonPath("$.productCategory").exists())
	.andExpect(jsonPath("$.productPrice").exists())
	.andExpect(jsonPath("$.productId").value(123))
	.andExpect(jsonPath("$.productName").value("xyz"))
	.andExpect(jsonPath("$.productCategory").value("yyy"))
	.andExpect(jsonPath("$.productPrice").value(450))
	.andDo(print());


}*/
	/*@Test
	public void testDeleteProduct() throws Exception {
		when(productService.findProductById(123)).thenReturn(new Product(123, "xyz", "yyy", 450));
		mockMvc.perform(delete("/product/123").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
		
	}*/
}
