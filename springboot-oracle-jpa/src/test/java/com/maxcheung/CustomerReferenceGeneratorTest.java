package com.maxcheung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerReferenceGeneratorTest {

	@InjectMocks
	private CustomerReferenceGeneratorImpl instance;

	@Mock
	private DataFieldMaxValueIncrementer incrementer;

	@Mock
	private DataSource dataSource;

	private Long expected;
	private Long actual;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws CheckDigitException {
		Map<Long, Long> map = new HashMap<Long, Long>();
		map.put(7992739871L, 79927398713L);
		map.put(4992739871L, 49927398716L);
		map.put(1000031L, 10000313L);
		for (Map.Entry<Long, Long> entry : map.entrySet()) {
			expected = entry.getValue();
			when(incrementer.nextLongValue()).thenReturn(entry.getKey());
			actual = instance.generateCRN();
			assertEquals(expected, actual);
		}
	}

	@Test
	public void testInvalid() throws CheckDigitException {
		Map<Long, Long> map = new HashMap<Long, Long>();
		map.put(4992739871L, 49927398717L);
		for (Map.Entry<Long, Long> entry : map.entrySet()) {
			expected = entry.getValue();
			when(incrementer.nextLongValue()).thenReturn(entry.getKey());
			actual = instance.generateCRN();
			assertTrue(expected.compareTo(actual) != 0);
		}
	}

}
