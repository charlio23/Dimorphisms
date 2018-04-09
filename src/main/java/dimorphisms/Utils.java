package dimorphisms;

import java.util.logging.Logger;

/**
 * The type Utils.
 */
public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * The constant FLOAT_REG_EXP.
     */
    public static final String FLOAT_REG_EXP = "([0-9]*[.])?[0-9]+";
    /**
     * The constant NAME_REG_EXP.
     */
    public static final String NAME_REG_EXP = "([A-Za-z0-9+_.-]{3}[A-Za-z0-9+_.-]*)";

    /**
     * The constant LIQUID_VAPOR_ERR.
     */
    public static final String LIQUID_VAPOR_ERR = "Liquid-Vapor input ERROR";
    /**
     * The constant SOLID_1_VAPOR_ERR.
     */
    public static final String SOLID_1_VAPOR_ERR = "Solid1-Vapor input ERROR";
    /**
     * The constant SOLID_2_VAPOR_ERR.
     */
    public static final String SOLID_2_VAPOR_ERR = "Solid2-Vapor input ERROR";
    /**
     * The constant LIQUID_SOLID_1_ERR.
     */
    public static final String LIQUID_SOLID_1_ERR = "Liquid-Solid1 input ERROR";
    /**
     * The constant LIQUID_SOLID_2_ERR.
     */
    public static final String LIQUID_SOLID_2_ERR = "Liquid-Solid2 input ERROR";
    /**
     * The constant SOLID_1_SOLID_2_ERR.
     */
    public static final String SOLID_1_SOLID_2_ERR = "Solid1-Solid2 input ERROR";

    public static final Logger logger = Logger.getAnonymousLogger();
}
