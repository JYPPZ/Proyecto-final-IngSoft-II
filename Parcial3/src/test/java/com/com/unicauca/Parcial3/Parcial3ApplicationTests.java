package com.com.unicauca.Parcial3;

import com.com.unicauca.Parcial3.domain.Repository.IStockRepository;
import com.com.unicauca.Parcial3.domain.Repository.IUserRepository;
import com.com.unicauca.Parcial3.domain.User;
import com.com.unicauca.Parcial3.domain.service.QueryService;
import com.com.unicauca.Parcial3.domain.service.StockService;
import com.com.unicauca.Parcial3.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Jose David Chilito Cometa, Valentina Fernandez Guerrero, Juan Esteban Yepez Rodriguez
 */

@SpringBootTest
class ApplicationTests {
	private MockMvc mockMvc;

	@InjectMocks
	private UserService userService1;
	@InjectMocks
	private StockService stockService1;
	@InjectMocks
	private QueryService queryService1;

	@Mock
	private IUserRepository userRepository;

	@Mock
	private IStockRepository stockRepository;

	@BeforeEach
	public void setup() throws Exception{
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userService1).build();
	}


	@Test
	void testRegisterUser() {
		userService1.registerUser(1);
		Mockito.verify(userRepository, Mockito.times(1)).addUser(Mockito.any(User.class));
	}

	@Test
	void testUserAlreadyExists() {
		Mockito.when(userRepository.findById(1)).thenReturn(new User(1));
		userService1.registerUser(1);
		Mockito.verify(userRepository, Mockito.times(0)).addUser(Mockito.any(User.class));
	}

	@Test
	void testGetStocks() {
		Mockito.when(userRepository.findById(1)).thenReturn(new User(1));
		queryService1.getStocks(1);
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyInt());
	}

	@Test
	void testGetStock() {
		Mockito.when(userRepository.findById(1)).thenReturn(new User(1));
		queryService1.getStock(1, 1);
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyInt());
	}

	@Test
	void testUpdateUpperThreshold() {
		Mockito.when(userRepository.findById(1)).thenReturn(new User(1));
		queryService1.updateUpperThreshold(1, 1, 1);
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyInt());
	}

	@Test
	void testUpdateLowerThreshold() {
		Mockito.when(userRepository.findById(1)).thenReturn(new User(1));
		queryService1.updateLowerThreshold(1, 1, 1);
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyInt());
	}

	@Test
	void testRemoveStock() {
		Mockito.when(userRepository.findById(1)).thenReturn(new User(1));
		queryService1.removeStock(1, 1);
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyInt());
	}

	@Test
	void testAddStock() {
		Mockito.when(userRepository.findById(1)).thenReturn(new User(1));
		queryService1.addStock(1, new com.com.unicauca.Parcial3.domain.Stock());
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyInt());
	}


}

