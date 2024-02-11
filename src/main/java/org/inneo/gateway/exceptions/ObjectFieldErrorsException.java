package org.inneo.gateway.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectFieldErrorsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private List<FieldErrorItem> fieldErrors;

    public ObjectFieldErrorsException(String msg, List<FieldErrorItem> fieldErrors) {
        super( msg );
        this.fieldErrors = fieldErrors;
    }

    public ObjectFieldErrorsException(String msg, List<FieldErrorItem> fieldErrors, Throwable ceuse ) {
        super( msg, ceuse );
        this.fieldErrors = fieldErrors;
    }

}
