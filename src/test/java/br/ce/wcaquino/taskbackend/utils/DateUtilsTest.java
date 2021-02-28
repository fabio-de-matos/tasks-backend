package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void devRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.of(2030, 04, 18);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void devRetornarFalseParaDatasPassadas() {
		LocalDate date = LocalDate.of(2010, 04, 18);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void devRetornarTrueParaDataPresente() {
		LocalDate date = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}

}
