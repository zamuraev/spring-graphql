package com.zamuraev.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.*;
import java.util.*;

public class ResourceNotFound extends RuntimeException implements GraphQLError {

    private String message;

    public ResourceNotFound(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<Object> getPath() {
        return null;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return null;
    }
}
