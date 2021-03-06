package com.tinamica.tunnelc.publisherdata.exceptions;

import java.io.Serializable;

public class TCEDCPublisherDataServiceError implements Serializable {

	private static final long serialVersionUID = 5225774210819213016L;
	private String component;
	private String code;
	private Throwable rootCause;
	private String message;

	public TCEDCPublisherDataServiceError() {

	}

	public TCEDCPublisherDataServiceError(String component, String code, Throwable rootCause, String message) {
		this.component = component;
		this.code = code;
		this.rootCause = rootCause;
		this.message = message;
	}

	public TCEDCPublisherDataServiceError(String component, String code, String message, Throwable rootCause) {
		this.component = component;
		this.code = code;
		this.rootCause = rootCause;
		this.message = message;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public void setRootCause(Throwable rootCause) {
		this.rootCause = rootCause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((component == null) ? 0 : component.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((rootCause == null) ? 0 : rootCause.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TCEDCPublisherDataServiceError other = (TCEDCPublisherDataServiceError) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (component == null) {
			if (other.component != null)
				return false;
		} else if (!component.equals(other.component))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (rootCause == null) {
			if (other.rootCause != null)
				return false;
		} else if (!rootCause.equals(other.rootCause))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TCEDCPublisherIncidenceServiceError [component=" + component + ", code=" + code + ", rootCause=" + rootCause
				+ ", message=" + message + "]";
	}

}
