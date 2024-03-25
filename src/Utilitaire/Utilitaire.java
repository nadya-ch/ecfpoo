package Utilitaire;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Utilitaire {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final Pattern PATTERN_MAIL=Pattern.compile("^(.+)@(.+)$");
}
