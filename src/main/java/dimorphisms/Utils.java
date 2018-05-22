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

    public static final String QUERY_LIQUID_VAPOR = "liquid-vapor";

    public static final String QUERY_VAPOR_SOLID1 = "vapor-solid1";

    public static final String QUERY_VAPOR_SOLID2 = "vapor-solid2";

    public static final String QUERY_LIQUID_SOLID1 = "liquid-solid1";

    public static final String QUERY_LIQUID_SOLID2 = "liquid-solid2";

    public static final String QUERY_SOLID1_SOLID2 = "solid1-solid2";

    public static final String QUERY_CURVE_LIQUID_SOLID1 = "curve-liquid-solid1";

    public static final String QUERY_CURVE_LIQUID_SOLID2 = "curve-liquid-solid2";

    public static final String QUERY_CURVE_SOLID1_SOLID2 = "curve-solid1-solid2";

    public static final String QUERY_TLV1 = "tlv1";

    public static final String QUERY_TLV2 = "tlv2";

    public static final String QUERY_TV12 = "tV12";

    public static final String QUERY_TL12 = "tL12";

    public static final String QUERY_PLV1 = "plv1";

    public static final String QUERY_PLV2 = "plv2";

    public static final String QUERY_PV12 = "pV12";

    public static final String QUERY_PL12 = "pL12";

    public static final String QUERY_ERROR_LIQUID_VAPOR = "Error when adding Liquid-Vapor";

    public static final String QUERY_ERROR_VAPOR_SOLID1 = "Error when adding Vapor-Solid1";

    public static final String QUERY_ERROR_VAPOR_SOLID2 = "Error when adding Vapor-Solid2";

    public static final String QUERY_ERROR_LIQUID_SOLID1 = "Error when adding Liquid-Solid1";

    public static final String QUERY_ERROR_LIQUID_SOLID2 = "Error when adding Liquid-Solid2";

    public static final String QUERY_ERROR_SOLID1_SOLID2 = "Error when adding Solid1-Solid2";

    public static final String QUERY_ERROR_TLV1 = "Error when adding TLV1";

    public static final String QUERY_ERROR_TLV2 = "Error when adding TLV2";

    public static final String QUERY_ERROR_TL12 = "Error when adding TL12";

    public static final String QUERY_ERROR_TV12 = "Error when adding TV12";

    public static final String QUERY_ERROR_UNEXPECTED = "Unexpected Error";

    public static final String QUERY_ERROR_CONFLICT = "Conflict Error";

    public static final String QUERY_SUCCESS = "success";

    public static final boolean ISLOG_DEFAULT_VALUE = false;

    public static final double TEMPERATURE_ORIGIN = 50;

    public static final double TEMPERATURE_FINAL = 2000;

    public static final double TEMPERATURE_STEP = 1;

    public static final int TEMPERATURE_SIZE = (int) ((TEMPERATURE_FINAL-TEMPERATURE_ORIGIN)/TEMPERATURE_STEP);

    public static final int DATA_SIZE = 300;

    public static final int STABLE = 0;

    public static final int META_STABLE = 1;

    public static final int SUPER_META_STABLE = 2;

    public static final Logger logger = Logger.getAnonymousLogger();

    public static final String TOPOLOGY_TEXT_1 = "The topology of this material is enantiotropic. As we can see, Vapor-Solid1-Solid2 and Liquid-Solid1-Solid2 triple points are stable.";

    public static final String TOPOLOGY_TEXT_2 = "The topology of this material is overall enantiotropic. As we can see, Vapor-Solid1-Solid2 triple point is stable whereas Liquid-Solid1-Solid2 is meta-stable.";

    public static final String TOPOLOGY_TEXT_3 = "The topology of this material is monotropic. As we can see, Vapor-Solid1-Solid2 triple point is meta-stable whereas Liquid-Solid1-Solid2 is stable.";

    public static final String TOPOLOGY_TEXT_4 = "The topology of this material is overall monotropic. As we can see, Vapor-Solid1-Solid2 and Liquid-Solid1-Solid2 triple points are meta-stable and as a result, the whole Solid2 phase is meta-stable.";

    public static final String TOPOLOGY_TEXT = "In the following image we can see a qualitative description of the stability of the material.";

    public static final String TOPOLOGY_ADITIONAL_INFO = "The phases I and II may be in different positions with respect to the real stable diagram.";

    public static final String ACTIVATE_STABLE_DIAGRAM = "stable-activate";
}
