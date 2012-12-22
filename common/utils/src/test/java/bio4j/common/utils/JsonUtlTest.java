package bio4j.common.utils;

import java.text.DateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bio4j.common.utils.DateUtl;
import bio4j.common.utils.JsonUtl;

import flexjson.JSONSerializer;

public class JsonUtlTest {

	private final TBox testBox = new TBox();

	@BeforeClass
	private void setUp() {
		this.testBox.setName("Test-Box");
		this.testBox.setCreated(DateUtl.parse("2012.12.20-15:11:24", "yyyy.MM.dd-HH:mm:ss"));
		this.testBox.setVolume(123.05);
		this.testBox.setPackets(new TPacket[] { new TPacket() });
		this.testBox.getPackets()[0].setName("packet-0");
		this.testBox.getPackets()[0].setVolume(100.10);
		this.testBox.getPackets()[0].setApples(new TApple[] { new TApple(), new TApple() });
		this.testBox.getPackets()[0].getApples()[0].setName("apple-0-0");
		this.testBox.getPackets()[0].getApples()[0].setWheight(10.100);
		this.testBox.getPackets()[0].getApples()[1].setName("apple-0-1");
		this.testBox.getPackets()[0].getApples()[1].setWheight(10.200);
		this.testBox.setEx(new Exception("FTW TestException"));
	}

	@Test(enabled = true)
	public void a_encode() {
		String expected =
		 "{\"class\":\"bio.common.utils.TBox\",\"created\":\"2012.12.20-15:11:24\",\"name\":\"Test-Box\",\"packets\":[{\"apples\":[{\"class\":\"bio.common.utils.TApple\",\"name\":\"apple-0-0\",\"wheight\":10.1},{\"class\":\"bio.common.utils.TApple\",\"name\":\"apple-0-1\",\"wheight\":10.2}],\"class\":\"bio.common.utils.TPacket\",\"name\":\"packet-0\",\"volume\":100.1}],\"volume\":123.05}";
		String testJson = JsonUtl.encode(this.testBox);
		System.out.println(testJson);
		//Assert.assertEquals(expected, testJson);
//		Exception ex = new Exception("FTW");
//		String valueStr = new JSONSerializer().exclude("cause", "localizedMessage", "stackTraceDepth").include("stackTrace").serialize(ex);
//		System.out.println(valueStr);
	}

	@Test(enabled = false)
	public void b_decode() {
		String testJson = JsonUtl.encode(this.testBox);
		TBox restored = (TBox) JsonUtl.decode(testJson);
		System.out.println("restored: " + restored);
		Assert.assertEquals(this.testBox.getName(), restored.getName());
		Assert.assertEquals(this.testBox.getCreated(), restored.getCreated());
		Assert.assertEquals(this.testBox.getVolume(), restored.getVolume());
		Assert.assertEquals(this.testBox.getPackets()[0].getName(), restored.getPackets()[0].getName());
		Assert.assertEquals(this.testBox.getPackets()[0].getVolume(), restored.getPackets()[0].getVolume());
		Assert.assertEquals(this.testBox.getPackets()[0].getApples()[0].getName(), restored.getPackets()[0].getApples()[0].getName());
		Assert.assertEquals(this.testBox.getPackets()[0].getApples()[0].getWheight(), restored.getPackets()[0].getApples()[0].getWheight());
		Assert.assertEquals(this.testBox.getPackets()[0].getApples()[1].getName(), restored.getPackets()[0].getApples()[1].getName());
		Assert.assertEquals(this.testBox.getPackets()[0].getApples()[1].getWheight(), restored.getPackets()[0].getApples()[1].getWheight());
	}

}
