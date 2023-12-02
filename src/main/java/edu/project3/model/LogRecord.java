package edu.project3.model;

import java.time.OffsetDateTime;

public record LogRecord(String address,
                        String user,
                        OffsetDateTime date,
                        String request,
                        String resource,
                        String status,
                        Integer bodyBytes,
                        String httpReferer,
                        String httpUserAgent) {
}
