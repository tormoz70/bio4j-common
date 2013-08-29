package bio4j.transport.packets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bio4j.common.types.Params;
import bio4j.common.utils.JsonUtl;

/** 
 * 
 * @author ayrat
 *
 */
public class AjaxRequest implements Cloneable {

    /** ѕараметры запроса. 
    * ƒанные параметры передаютс€ на сервер в виде параметров Http-запроса
    * ѕри этом параметры, которые €вл€ютс€ служебными параметрами
    * протокола сюда не попадают (при дисереализации),
    * только параметры, которые добавлены к запросу вручную
    */
    private Params params;
	public Params getParams() { return params; }
	public void setParams(Params params) { this.params = params; }
	
	/** Ћогин в виде username/password */
    private String login;
	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }
	
	/** ћожет быть передано несколько запросов Bio */
	private List<BioRequest> bioRequests;
	public List<BioRequest> getBioRequest() { 
		if(this.bioRequests == null) 
			this.bioRequests = new ArrayList<BioRequest>(); 
		return this.bioRequests; 
	}
	
    /** ƒесериализует объект из json-строки */
    public static AjaxRequest Decode(String jsonString) {
      return JsonUtl.decode(AjaxRequest.class, jsonString);
    }

	@Override
    public AjaxRequest clone() throws CloneNotSupportedException {
		AjaxRequest result = new AjaxRequest();  
		result.setParams((this.getParams() != null) ? this.getParams().clone() : null);
		return result;  
    }

}
