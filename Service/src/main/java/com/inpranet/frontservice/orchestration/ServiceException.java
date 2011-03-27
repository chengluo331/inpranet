package com.inpranet.frontservice.orchestration;

/**
 * Exception pour gerer les erreurs d'identification au systeme
 * @author inpranet team
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception{

    private ErrorCode _errorCode;
    
    public enum ErrorCode {
    	BAD_TOKEN
    }
    
    /**
     * Constructor
     * @param code erreur
     */
    public ServiceException(ErrorCode err) {
        super("");
        _errorCode = err;
    }
    
    /**
     * Get error code
     * @return ErrorCode error code
     */
    public ErrorCode getErrorCode() {
        return _errorCode;
    }
}

