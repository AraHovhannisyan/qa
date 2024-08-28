package am.fillandgo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

/**
 * The PrettySqlAppender class is a Log4j2 appender that formats and prints SQL statements with their corresponding parameters in a more readable format.
 * <p>
 * This appender should be used with the Log4j2 framework, and it is designed to be used in applications that use JDBC to execute SQL statements.
 * </p>
 * <p>
 * The PrettySqlAppender class extends the AbstractAppender class provided by Log4j2. It overrides the append method to format and print the SQL statements and their parameters.
 * </p>
 * <p>
 * The PrettySqlAppender class is a plugin, which means it can be configured in the Log4j2 configuration file.
 * The name attribute is required, and it specifies the name of the appender.
 * The filter element can be used to apply filters to the log events before they are processed by the appender.
 * </p>
 * <h3>Example usage:</h3>
 * <pre>{@code
 * <Configuration>
 *   ...
 *   <Appenders>
 *     <PrettySqlAppender name="PrettySqlAppender">
 *       <Filter type="..." />
 *     </PrettySqlAppender>
 *   </Appenders>
 *   ...
 * </Configuration>
 * }</pre>
 */
@Plugin(
        name = "PrettySqlAppender",
        category = Core.CATEGORY_NAME,
        elementType = Appender.ELEMENT_TYPE)
public class PrettySqlAppender extends AbstractAppender {
    private static final String SQL_PREFIX = "==>  Preparing: ";
    private static final String PARAMETER_PREFIX = "==> Parameters: ";

    private String prevLogger = null;
    private String prevSql = null;

    protected PrettySqlAppender(String name, Filter filter) {
        super(name, filter, null);
    }


    /**
     * Creates a new instance of PrettySqlAppender.
     * @param name   the name of the appender
     * @param filter the filter to be applied to log events
     * @return a new instance of PrettySqlAppender
     */
    @PluginFactory
    public static PrettySqlAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter) {
        return new PrettySqlAppender(name, filter);
    }

    /**
     * Appends a log event to this appender.
     * @param event the log event to append
     */
    @Override
    public void append(LogEvent event) {
        String loggerName = event.getLoggerName();
        if (loggerName.startsWith("am.fillandgo") && !StringUtils.contains(loggerName, "RestAuditDao")) {
            if (StringUtils.startsWith(event.getMessage().getFormattedMessage(), SQL_PREFIX)) {
                prevSql = StringUtils.substringAfter(event.getMessage().getFormattedMessage(), SQL_PREFIX);
                prevLogger = loggerName;
            } else if (StringUtils.startsWith(event.getMessage().getFormattedMessage(), PARAMETER_PREFIX)
                    && StringUtils.equals(prevLogger, loggerName)) {
                String parameters = StringUtils.substringAfter(event.getMessage().getFormattedMessage(), PARAMETER_PREFIX);
                showPrettySql(parameters);
                prevLogger = null;
            } else {
                prevLogger = null;
            }
        }
    }

    /**
     * Displays a formatted SQL with its corresponding parameters.
     * @param parameters the parameters to be displayed
     */
    private void showPrettySql(String parameters) {
        String[] params = StringUtils.splitByWholeSeparator(parameters, ", ");
        for (int i = 0; i < params.length; i++) {
            String value = StringUtils.substringBefore(params[i], "(");
            if (StringUtils.endsWithAny(params[i], "(String)", "(Date)", "(Time)")) {
                value = "'" + value + "'";
            }
            params[i] = value;
        }

        String[] sql = StringUtils.split(prevSql, '?');

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (String s : sql) {
            sb.append(s);
            if (params.length > idx) {
                sb.append(params[idx]);
                idx++;
            }
        }
        System.out.println("-------------------------------------- Pretty sql ----------------------------");
        System.out.println(prevLogger);
        System.out.println(sb);
        System.out.println("------------------------------------------------------------------------------");
    }
}