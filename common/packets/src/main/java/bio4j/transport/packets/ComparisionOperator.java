package bio4j.transport.packets;

public enum ComparisionOperator {
    Eq(0, "�����"),
    Lt(1, "������"),
    Le(2, "������ ��� �����"),
    Gt(3, "������"),
    Ge(4, "������ ��� �����"),
    Bgn(5, "���������� �"),
    End(6, "������������ ��"),
    In(7, "��������"),
    IsNull(8, "�����");
    
    private final int code;
    private final String description;
    private ComparisionOperator(int code, String description) {
    	this.code = code;
    	this.description = description;
    }
	public int getCode() {
	    return code;
    }
	public String getDescription() {
	    return description;
    }
}
