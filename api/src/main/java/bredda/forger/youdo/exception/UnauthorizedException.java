package bredda.forger.youdo.exception;


public class UnauthorizedException extends RuntimeException {

    public static final String MESSAGE = "User is not authorized to this resource";
    private static final long serialVersionUID = 7718828512143293558L;

    public UnauthorizedException() {
        super();
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
