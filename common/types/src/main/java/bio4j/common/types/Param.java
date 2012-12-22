package bio4j.common.types;

import bio4j.common.utils.StringUtl;

public class Param implements Cloneable {
	private Params owner;
	private String name;

	private Object value;
	private Object innerObject;
	private Class<?> type;
	private int size;
	private Direction direction;

	public Param(Params owner) {
		this.owner = owner;
	}

	public Param(Params owner, String name, Object value) {
		this(owner);
		this.name = name;
		this.value = value;
	}

	public Param(Params owner, String name, Object value, Class<?> type, Direction direction) {
		this(owner, name, value);
		this.type = type;
		this.direction = direction;
	}

	public Param(Params owner, String name, Object value, Class<?> type, int size, Direction direction) {
		this(owner, name, value, type, direction);
		this.size = size;
	}

	public Params getOwner() {
		return this.owner;
	}

	protected Param export(Params destOwner) {
		Param rslt = null;
		try {
			rslt = (Param) this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		rslt.owner = destOwner;
		return rslt;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return this.value;
	}

	public String getValueAsString() {
		return (this.value == null) ? null : this.value.toString();
	}

	public synchronized void setInnerObject(Object innerObject) {
		this.innerObject = innerObject;
	}

	public Object getInnerObject() {
		return this.innerObject;
	}

	public synchronized void setType(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return this.type;
	}

	public synchronized void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public synchronized void removeFromOwner() {
		if (this.getOwner() != null)
			this.getOwner().remove(this);
	}

	public String toString() {
		String innrObjStr = (this.getInnerObject() == null) ? null : "o:" + this.getInnerObject().toString();
		String objsStr = null;
		if (StringUtl.isNullOrEmpty(innrObjStr))
			objsStr = StringUtl.appendStr(objsStr, innrObjStr, ";");
		if (!StringUtl.isNullOrEmpty(objsStr))
			objsStr = "(" + objsStr + ")";
		String valStr = this.getValue() + objsStr;
		return String.format("(%s=[%s]; tp:%s; sz:%d; dr:%s)", this.getName(), valStr, this.getType(), this.getSize(), this.getDirection());
	}
}
