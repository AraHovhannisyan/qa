package am.fillandgo.util.generators;

import java.util.UUID;

/**
 * The RandomStringGeneratorUtil class is a utility class that provides methods for generating random strings.
 */
public class RandomStringGeneratorUtil {

    private RandomStringGeneratorUtil() {}

    /**
     * Generates a random UUID (Universally Unique Identifier) as a string.
     * The UUID is generated using the standard UUID algorithm and formatted as a string without dashes.
     * @return A random UUID as a string.
     */
    public static String uuId() {
        return generateRandomStringByUUID();
    }

    /**
     * Generates a raw UUID (Universally Unique Identifier) as a string.
     * The UUID is generated using the standard UUID algorithm without any formatting.
     * @return A raw UUID as a string.
     */
    public static String rawUUID() {
        return generateRandomStringByRawUUID();
    }

    /**
     * Generates a random string by using UUID (Universally Unique Identifier).
     * The UUID is generated using the standard UUID algorithm and formatted as a string without dashes.
     * @return A random string generated from UUID.
     */
    private static String generateRandomStringByUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generates a raw UUID (Universally Unique Identifier) as a string.
     * The UUID is generated using the standard UUID algorithm without any formatting.
     * @return A raw UUID as a string.
     */
    private static String generateRandomStringByRawUUID() {
        return UUID.randomUUID().toString();
    }

}