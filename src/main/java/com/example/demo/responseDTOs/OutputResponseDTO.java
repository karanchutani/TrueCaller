package com.example.demo.responseDTOs;

public class OutputResponseDTO {
    private String message;
    private Object object;

    public OutputResponseDTO(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public OutputResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "OutputResponseDTO{" +
                "message='" + message + '\'' +
                ", object=" + object +
                '}';
    }
}
