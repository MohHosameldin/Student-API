package com.example.studentsapi.exceptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.web.context.request.WebRequest;

public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
        errorAttributes.put("custom_timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        errorAttributes.remove("timestamp");
        return errorAttributes;
    }

}
