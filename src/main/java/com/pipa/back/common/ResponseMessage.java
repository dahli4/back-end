package com.pipa.back.common;

public interface ResponseMessage {
    String SUCCESS = "Sucess.";

    String VALIDATION_FAIL = "Validation failed.";
    String DUPLICATE_ID = "Duplicate Id.";

    String SIGN_IN_FAIL = "Login information mismatch.";
    String CERTIFICATION_FAIL = "certification failed.";

    String DATABASE_ERROR = "Database Error.";
}
