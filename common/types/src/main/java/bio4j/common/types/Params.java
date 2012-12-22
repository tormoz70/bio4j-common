package bio4j.common.types;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import bio4j.common.utils.StringUtl;

public class Params extends ArrayList<Param> {
	private static final long serialVersionUID = 5012738097491734039L;

	public Param getParam(final String name, final Boolean ignoreCase, final Boolean createIfNotFound) {
		Param result = this.process(new DelegateCheck<Param>() {
			@Override
			public boolean callback(Param param) {
				return StringUtl.compareStrings(param.getName(), name, ignoreCase);
			}
		}).first();
		if ((result == null) && (createIfNotFound)) {
			result = new Param(this);
			this.add(result);
		}
		return result;
	}

	public Param getParam(String name, Boolean ignoreCase) {
		return getParam(name, ignoreCase, false);
	}

	public Param getParam(final String name) {
		return getParam(name, false);
	}

	public String getNamesList() {
		String rslt = null;
		for (Param param : this)
			rslt = StringUtl.appendStr(rslt, "\"" + param.getName() + "\"", ",");
		return rslt;
	}

	public String getValsList() {
		String rslt = null;
		for (Param param : this)
			rslt = StringUtl.appendStr(rslt, "\"" + param.getValueAsString() + "\"", ",");
		return rslt;
	}

	public Integer getIndexOf(String name) {
		return this.indexOf(this.getParam(name, true));
	}

	public Params process(DelegateCheck<Param> check) {
		Params result = new Params();
		if (check != null)
			for (Param param : this)
				if (check.callback(param))
					result.add(param);
		return result;
	}

	public Param first() {
		if (!this.isEmpty())
			return this.get(0);
		else
			return null;
	}

	public synchronized Param removeParam(Param param) {
		if (param.getOwner() == this)
			param.removeFromOwner();
		return param;
	}

	public synchronized Param removeParam(String name) {
		Param rslt = this.getParam(name);
		return this.removeParam(rslt);
	}

	private synchronized void checkExists(String name, Boolean replaceIfExists) throws ParamAlredyExistsException {
		Param exists = this.getParam(name);
		if (exists != null) {
			if (replaceIfExists) {
				exists.removeFromOwner();
				exists = null;
			} else
				throw new ParamAlredyExistsException(name);

		}
	}

	public synchronized Param add(Param item, Boolean replaceIfExists) throws ParamAlredyExistsException {
		if (item != null) {
			this.checkExists(item.getName(), replaceIfExists);
			super.add(item);
		}
		return item;
	}

	public synchronized Param add(String name, Object value, Boolean replaceIfExists) throws ParamAlredyExistsException {
		if (!StringUtl.isNullOrEmpty(name)) {
			this.checkExists(name, replaceIfExists);
			Param rslt = new Param(this, name, value);
			super.add(rslt);
			return rslt;
		}
		return null;
	}

	public synchronized Param add(String name, Object value) throws ParamAlredyExistsException {
		return this.add(name, value, false);
	}

	public synchronized Param add(String name, Object value, Object innerObject) throws ParamAlredyExistsException {
		Param rslt = this.add(name, value, false);
		rslt.setInnerObject(innerObject);
		return rslt;
	}

	public synchronized Params merge(Params params, Boolean overwrite) {
		if ((params != null) && (params != this)) {
			for (Param pp : params)
				try {
					this.add(pp.export(this), overwrite);
				} catch (ParamAlredyExistsException e) {
				}
		}
		return this;
	}

	public Object getInnerObjectByName(String name, Boolean ignoreCase) {
		Param param = this.getParam(name, ignoreCase);
		if (param != null)
			return param.getInnerObject();
		return null;
	}

	public String getValueAsStringByName(String name, Boolean ignoreCase) {
		Param param = this.getParam(name, ignoreCase);
		if (param != null)
			return param.getValueAsString();
		return null;
	}

	public Object getValueByName(String name, Boolean ignoreCase) {
		Param param = this.getParam(name, ignoreCase);
		if (param != null)
			return param.getValue();
		return null;
	}

	public Boolean paramExists(String name) {
		return this.getParam(name) != null;
	}

	public Boolean paramExists(String name, Boolean ignoreCase) {
		return this.getParam(name, ignoreCase) != null;
	}

	public synchronized Param setValue(String name, Object value) {
		Param param = this.getParam(name);
		if (param != null)
			param.setValue(value);
		return param;
	}

	public Map<String, String> toMap() {
		Map<String, String> rslt = new HashMap<String, String>();
		for (Param prm : this) {
			String val = null;
			if ((prm.getValue() != null) && (prm.getValue().getClass() == String.class))
				val = prm.getValueAsString();
			else {
				// TODO do serialize object with an json serializer
				// val = Newtonsoft.Json.JsonConvert.SerializeObject(prm.Value);
				val = prm.getValueAsString();
			}
			rslt.put(prm.getName(), val);
		}
		return rslt;
	}

	public String buildUrlParams() {
		String rslt = null;
		for (Param prm : this) {
			String paramStr = null;
			try {
				paramStr = prm.getName() + "=" + URLEncoder.encode(prm.getValueAsString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
			rslt = StringUtl.appendStr(rslt, paramStr, "&");
		}
		return rslt;
	}

	public String buildUrlParams(String baseURL) {
		String rslt = this.buildUrlParams();
		if (!StringUtl.isNullOrEmpty(baseURL))
			return (baseURL.indexOf("?") >= 0) ? baseURL + "&" + rslt : baseURL + "?" + rslt;
		else
			return rslt; 
	}

	public String encode() {
		// TODO Add implementation
		return null;
	}

    public static Params decode(String jsonString) {
		// TODO Add implementation
		return null;
    }

	public Boolean containsParam(String name, Object value) {
		// TODO Add implementation
		return false;
	}

	public Boolean containsParam(Param param) {
		// TODO Add implementation
		return false;
	}

	public Boolean containsParam(Params params) {
		// TODO Add implementation
		return false;
	}

	public synchronized void addList(String names, Object[] values, char delimiter) {
		// TODO Add implementation
	}

	public synchronized void addList(String names, Object[] values) {
		// TODO Add implementation
	}

	public synchronized void addList(String names, String values, char delimiter) {
		// TODO Add implementation
	}

	public synchronized void addList(String names, String values) {
		// TODO Add implementation
	}

	public synchronized void setList(String names, Object[] values, char delimiter) {
		// TODO Add implementation
	}

	public synchronized void setList(String names, String values, char delimiter) {
		// TODO Add implementation
	}

	public synchronized void setList(String names, String values) {
		// TODO Add implementation
	}

	public synchronized void removeList(String names, char delimiter) {
		// TODO Add implementation
	}

	public synchronized void removeList(String names) {
		// TODO Add implementation
	}

}
