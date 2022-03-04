package bredda.forger.youdo.payload.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {
    private final HttpStatus httpStatus;

    private final LocalDateTime timestamp;

    private final String message;

    private final String details;

    public ErrorResponse(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    public Map<String, Object> toMap() {
        final Map<String, Object> body = new HashMap<>();
        body.put("httpStatus", httpStatus);
        body.put("timestamp", timestamp.toString());
        body.put("message", message);
        body.put("details", details);
        return body;
    }

}
