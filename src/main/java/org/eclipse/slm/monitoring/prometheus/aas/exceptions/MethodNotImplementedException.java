package org.eclipse.slm.monitoring.prometheus.aas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class MethodNotImplementedException extends RuntimeException {

	public MethodNotImplementedException() {
		super("Method not implemented or supported for this submodel");
	}
}
