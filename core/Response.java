package core;

public class Response<T> {
    private final boolean success;
    private final T payload;
    private final String message;

    private Response(boolean success, T payload, String message) {
        this.success = success; this.payload = payload; this.message = message;
    }

    public static <T> Response<T> ok(T payload) { return new Response<>(true, payload, null); }
    public static <T> Response<T> fail(String message) { return new Response<>(false, null, message); }

    public boolean isSuccess() { return success; }
    public T getPayload() { return payload; }
    public String getMessage() { return message; }
}
