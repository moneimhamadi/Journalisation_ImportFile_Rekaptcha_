package com.moneim.api.entities;

public class ObjectResponse {
    private final String responseDescription;
    private final Object objectResponse;
    private final int codeResponse;


    public String getResponseDescription() {
        return responseDescription;
    }

    public Object getObjectResponse() {
        return objectResponse;
    }

    public int getCodeResponse() {
        return codeResponse;
    }

    public ObjectResponse(String responseDescription, Object objectResponse, int codeResponse) {
        this.responseDescription = responseDescription;
        this.objectResponse = objectResponse;
        this.codeResponse = codeResponse;
    }
}
