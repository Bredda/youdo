package bredda.forger.youdo.exception;


public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 7718828512143293558L;
    private final String message;

    public UserAlreadyExistsException(String message) {
        super();
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }

}
