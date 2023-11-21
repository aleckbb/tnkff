package edu.project3.Parser;

import edu.project3.model.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;

public interface Parser {
    List<LogRecord> parse(String regOrURL, OffsetDateTime fromDate, OffsetDateTime toDate);
}
