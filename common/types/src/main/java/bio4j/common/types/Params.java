package bio4j.common.types;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import bio4j.common.utils.JsonUtl;
import bio4j.common.utils.StringUtl;

public class Params extends ArrayList<Param> {

    private static final long serialVersionUID = 1L;
	private static final String csDefaultDelimiter = "/";

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

	private Boolean alredyExists(String name, Boolean replaceIfExists) {
		Boolean result = false;
		Param exists = this.getParam(name);
		if (exists != null) {
			if (replaceIfExists) {
				exists.removeFromOwner();
				exists = null;
			} else
				result = true;
		}
		return result;
	}

	public synchronized Param add(Param item, Boolean replaceIfExists) {
		if (item != null) {
			if (!this.alredyExists(item.getName(), replaceIfExists))
				super.add(item);
		}
		return item;
	}

	public synchronized Param add(String name, Object value, Boolean replaceIfExists) {
		if (!StringUtl.isNullOrEmpty(name)) {
			if (!this.alredyExists(name, replaceIfExists)) {
				Param rslt = new Param(this, name, value);
				super.add(rslt);
				return rslt;
			}
		}
		return null;
	}

	public synchronized Param add(String name, Object value) {
		return this.add(name, value, false);
	}

	public synchronized Param add(String name, Object value, Object innerObject) {
		Param rslt = this.add(name, value, false);
		rslt.setInnerObject(innerObject);
		return rslt;
	}

	public synchronized Params merge(Params params, Boolean overwrite) {
		if ((params != null) && (params != this)) {
			for (Param pp : params)
				this.add(pp.export(this), overwrite);
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
				val = JsonUtl.encode(prm.getValue());
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
		return JsonUtl.encode(this);
	}

	public static Params decode(String jsonString) {
		return JsonUtl.decode(Params.class, jsonString);
	}

	public Boolean paramExists(String name) {
		return this.getParam(name) != null;
	}

	public Boolean paramExists(String name, Boolean ignoreCase) {
		return this.getParam(name, ignoreCase) != null;
	}

	public synchronized Params addList(String names, Object[] values, String delimiter) {
		String[] paramNames = StringUtl.split(names, delimiter);
		for (int i = 0; i < paramNames.length; i++)
			this.add(paramNames[i], (i < values.length) ? values[i] : null);
		return this;
	}

	public synchronized Params addList(String names, Object[] values) {
		return this.addList(names, values, csDefaultDelimiter);
	}

	public synchronized Params addList(String names, String values, String delimiter) {
		return this.addList(names, StringUtl.split(values, delimiter), delimiter);
	}

	public synchronized Params addList(String names, String values) {
		return addList(names, values, csDefaultDelimiter);
	}

	public synchronized Params setList(String names, Object[] values, String delimiter) {
		String[] strs = StringUtl.split(names, delimiter);
		for (int i = 0; i < strs.length; i++)
			if (i < values.length)
				this.setValue(strs[i], values[i]);
		return this;
	}

	public synchronized Params setList(String names, String values, String delimiter) {
		return setList(names, StringUtl.split(values, delimiter), delimiter);
	}

	public synchronized Params setList(String names, String values) {
		return setList(names, values, csDefaultDelimiter);
	}

	public synchronized Params removeList(String names, String delimiter) {
		String[] strs = StringUtl.split(names, delimiter);
		for (int i = 0; i < strs.length; i++)
			this.removeParam(strs[i]);
		return this;
	}

	public synchronized Params removeList(String names) {
		return removeList(names, csDefaultDelimiter);
	}

	@Override
    public Params clone() {
		try {
	    	return (Params) BeanUtils.cloneBean(this);
		} catch (Exception ex){
			Params result = new Params();
			result.add("ErrorOnCloneParamsMessage", ex.getMessage());
			return result;
		}
    }
	
}
