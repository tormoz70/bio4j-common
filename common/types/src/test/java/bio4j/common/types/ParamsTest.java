package bio4j.common.types;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bio4j.common.types.Params;

public class ParamsTest {

	private final Params testParams = new Params();

	@BeforeClass
	private void setUp() {
		try {
	        testParams.add("param1", 11);
			testParams.add("param1", 111);
        } catch (Exception e) {
	        e.printStackTrace();
        }
	}

	@Test(enabled=false)
	public void addListStringObjectchar() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void addListStringObject() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void addListStringStringchar() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void addListStringString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void buildUrlParams() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void buildUrlParamsString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void containsStringObject() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void containsParam() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void containsParams() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void encode() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void first() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getIndexOf() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getInnerObjectByName() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getNamesList() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getParamStringBooleanBoolean() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getParamStringBoolean() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getParamString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getValsList() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getValueAsStringByName() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void getValueByName() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void merge() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void paramExistsString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void paramExistsStringBoolean() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void process() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void removeParam() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void removeString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void removeListStringchar() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void removeListString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void setListStringObjectchar() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void setListStringStringchar() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void setListStringString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void setValue() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled=false)
	public void toMap() {
		throw new RuntimeException("Test not implemented");
	}
}
