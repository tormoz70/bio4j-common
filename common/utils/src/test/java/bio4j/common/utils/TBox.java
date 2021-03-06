package bio4j.common.utils;

import java.util.Date;

public class TBox {
	private String name;
	private Date created;
	private Double volume;
	private TPacket[] packets;
	private Exception ex;

	public TBox() {
	}

	public TBox(String name, Date created, Double volume) {
		this.setName(name);
		this.setCreated(created);
		this.setVolume(volume);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public TPacket[] getPackets() {
	    return packets;
    }

	public void setPackets(TPacket[] packets) {
	    this.packets = packets;
    }

	public Exception getEx() {
	    return ex;
    }

	public void setEx(Exception ex) {
	    this.ex = ex;
    }
}
