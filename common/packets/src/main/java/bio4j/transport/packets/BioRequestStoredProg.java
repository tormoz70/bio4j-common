package bio4j.transport.packets;

public class BioRequestStoredProg extends BioRequestLongOp {

	/** ��� pipe �� �������� ���� ������ ��������� �� ����������� �������� ��������� */
    private String pipeName;
	public String getPipeName() { return pipeName; }
	public void setPipeName(String pipeName) { this.pipeName = pipeName; }
    
	/** UID ������ ������� �������������� ���������� ������� */
    private String sessionUID;
	public String getSessionUID() { return sessionUID; }
	public void setSessionUID(String sessionUID) { this.sessionUID = sessionUID; }

	@Override
    public BioRequestStoredProg clone() throws CloneNotSupportedException {
		BioRequestStoredProg result = (BioRequestStoredProg)super.clone();
		result.setPipeName(this.getPipeName());
		result.setSessionUID(this.getSessionUID());
		return result;
    }
	
}
