package Log;
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatterLog extends java.util.logging.Formatter {
    public FormatterLog() { /* compiled code */ }

    public String format(java.util.logging.LogRecord record) { /* compiled code */
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder result = new StringBuilder();
        result.append(format.format(new Date()));
        result.append(" Level : ");
        result.append(record.getLevel());
        result.append(" / Message : ");
        result.append(record.getMessage());
        result.append(" / Méthode : ");
        result.append(record.getSourceMethodName());
        result.append(" / Classe : ");
        result.append(record.getSourceClassName());
        result.append("\n");
        return result.toString();
    }
}