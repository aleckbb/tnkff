package edu.project3.Parser;

import edu.project3.model.LogRecord;
import java.util.List;

public interface Parser {
    List<LogRecord> parse(String regOrURL);
}
