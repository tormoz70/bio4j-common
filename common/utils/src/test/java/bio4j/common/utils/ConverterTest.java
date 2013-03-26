package bio4j.common.utils;

import org.junit.Assert;
import org.testng.annotations.Test;

public class ConverterTest {

	@Test
	public void ConvertString2Double1() {
		try {
			Double actual = Converter.toType("123.654", Double.class);
			Double expected = 123.654;
			Assert.assertEquals(expected, actual);
		} catch (ConverterValueException ex) {
			Assert.fail(ex.getMessage());
		}
	}
	@Test
	public void ConvertString2Double2() {
		try {
			Double actual = Converter.toType(" 123 123,654", Double.class);
			Double expected = 123123.654;
			Assert.assertEquals(expected, actual);
		} catch (ConverterValueException ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void ConvertString2Float() {
		try {
			Object actual = Converter.toType(" 123 123,654", Float.class);
			Float expected = new Float(123123.654);
			Assert.assertEquals(expected, actual);
		} catch (ConverterValueException ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void ConvertString2Integer() {
		try {
			Integer actual = Converter.toType("123", int.class);
			Integer expected = 123;
			Assert.assertEquals(expected, actual);
		} catch (ConverterValueException ex) {
			Assert.fail(ex.getMessage());
		}
	}

	private void checkAType(Number value){
		try {
			Assert.assertEquals(new Byte((byte)12), Converter.toType(value, Byte.class));
			Assert.assertEquals(new Short((short)12), Converter.toType(value, Short.class));
			Assert.assertEquals(new Integer((int)12), Converter.toType(value, Integer.class));
			Assert.assertEquals(new Long((long)12), Converter.toType(value, Long.class));
			Assert.assertEquals(new Float((float)12), Converter.toType(value, Float.class));
			Assert.assertEquals(new Double((double)12), Converter.toType(value, Double.class));
		} catch (ConverterValueException ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void ConvertNumber2Number() {
		checkAType((byte)12);
		checkAType((short)12);
		checkAType((int)12);
		checkAType((long)12);
		checkAType((float)12);
		checkAType((double)12);
	}
	
	@Test(enabled=false)
	public void typeIsNumber() {
		throw new RuntimeException("Test not implemented");
	}
}
