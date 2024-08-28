package am.fillandgo.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.*;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyBatisSqlInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisSqlInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // Get the original SQL query
        String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");

        // Get the parameter values
        Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");

        // Get parameters
        Object[] parameters = getParameters(parameterObject);

        Object obj = parameters[0];
        List<String> values = new ArrayList<>();

        if (obj != null) {
            Class<?> objClass = obj.getClass();
            Field[] fields = objClass.getDeclaredFields();

            for (Field field : fields) {
                if (!objClass.getName().equals("java.lang.String")) {
                    // Allow access to private fields
                    field.setAccessible(true);
                    try {
                        Object value = field.get(obj);
                        String s = formatParameter(value);
                        values.add(s); // Collect the formatted values
                    } catch (IllegalAccessException e) {
                        System.out.println("Could not access field " + field.getName());
                    }
                } else {
                    String s = formatParameter(obj);
                    values.add(s);
                    break;
                }
            }
        }

        String s = buildQuery(originalSql, values);
        logger.debug("""

                \s
                 ********** PRETTY_SQL **********\s
                {} \

                 ********** PRETTY_SQL **********\s
                """, s);
        return invocation.proceed();
    }

    private Object[] getParameters(Object parameterObject) {
        if (parameterObject instanceof Map) {
            // If parameters are in a map, handle them accordingly
            return ((Map<?, ?>) parameterObject).values().toArray();
        } else if (parameterObject instanceof Object[]) {
            // If parameters are in an array, use it directly
            return (Object[]) parameterObject;
        } else {
            // For other cases, treat as a single parameter
            return new Object[]{parameterObject};
        }
    }

    private String formatParameter(Object parameter) {
        if (parameter == null) {
            return "";
        } else if (parameter instanceof String) {
            // Escape single quotes in strings and wrap them in single quotes
            return "'" + ((String) parameter).replace("'", "''") + "'";
        } else if (parameter instanceof Number) {
            // Simply convert numbers to string (no quotes needed)
            return parameter.toString();
        } else if (parameter instanceof Date) {
            return "'" + parameter + "'";
        } else {
            // Handle other types as needed or convert to string
            return parameter.toString();
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // No properties needed for this interceptor
    }


    private String buildQuery(String oldQuery, List<String> params) {
        String[] placeholders = oldQuery.split("\\?");
        StringBuilder resultQuery = new StringBuilder();

        for (int i = 0; i < placeholders.length; i++) {
            resultQuery.append(placeholders[i]);
            if (i < params.size()) {
                String param = params.get(i);
                // Handle `null` string as SQL `NULL` keyword
                resultQuery.append(param != null ? param : "NULL");
            } else if (i < placeholders.length - 1) {
                resultQuery.append("NULL");
            }
        }

        return resultQuery.toString();
    }
}
