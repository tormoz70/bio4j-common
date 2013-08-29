package bio4j.transport.packets;

public class BioRequestLongOp extends BioRequest {
	/** ��������� ��� ����������� ��������� �� ������� */
    private String title;
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	/** ������� ������� ���� �������� �� ������ 
	 * ��� ���������� ��������� ������ ��������� */
	private LongOpCommand cmd;
    public LongOpCommand getCmd() { return cmd; }
	public void setCmd(LongOpCommand cmd) { this.cmd = cmd; }

	@Override
    public BioRequestLongOp clone() throws CloneNotSupportedException {
		BioRequestLongOp result = (BioRequestLongOp)super.clone();
		result.setTitle(this.getTitle());
		result.setCmd(this.getCmd());
		return result;
    }
}
