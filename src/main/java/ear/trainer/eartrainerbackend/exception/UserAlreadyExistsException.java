package ear.trainer.eartrainerbackend.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("User with email '" + email + "' already exists");
    }

    public UserAlreadyExistsException(String email, String message) {
        super(message);
    }
}
