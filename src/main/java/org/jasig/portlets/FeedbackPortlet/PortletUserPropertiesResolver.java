package org.jasig.portlets.FeedbackPortlet;

import java.util.Map;

import javax.portlet.PortletRequest;

public class PortletUserPropertiesResolver {

	public UserProperties getProperties(PortletRequest request) {

		Map userinfo = (Map) request.getAttribute("javax.portlet.userinfo");
		UserProperties props = new UserProperties();
		if (roleKey != null)
			props.setUserrole((String) userinfo.get(roleKey));

		if (nameKey != null)
			props.setUsername((String) userinfo.get(nameKey));

		if (idKey != null)
			props.setUserid((String) userinfo.get(idKey));

		if (emailKey != null)
			props.setUseremail((String) userinfo.get(emailKey));

		return props;
	}

	private String nameKey;
	private String roleKey;
	private String idKey;
	private String emailKey;

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}

}
