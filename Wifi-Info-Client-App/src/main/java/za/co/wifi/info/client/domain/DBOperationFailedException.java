package za.co.wifi.info.client.domain;

import java.io.Serializable;

public class DBOperationFailedException extends Throwable implements Serializable {

    private static final long serialVersionUID = 1L;

    public DBOperationFailedException() {
        super();
    }

    public DBOperationFailedException(String message) {
        super(message);
    }

    public DBOperationFailedException(Exception ex) {
        super(ex);
    }

    public DBOperationFailedException(String message, Exception ex) {
        super(message, ex);
    }
}
