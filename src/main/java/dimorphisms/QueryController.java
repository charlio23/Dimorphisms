package dimorphisms;

import java.util.ArrayList;
import java.util.List;

/* TODO
Check Project SW structure!!
 */

public class QueryController {

    private List<String> queries;
    private MaterialProperties materialProperties;

    public QueryController(String name) {
        queries = new ArrayList<>();
        materialProperties = new MaterialProperties(name);
    }

    public QueryController(MaterialProperties materialProperties, List<String> queries) {
        this.materialProperties = materialProperties;
        this.queries = queries;
    }

    public String makeQueryVaporSth(String query, VaporSth eqCurve) {
        switch (query) {
            case Utils.QUERY_LIQUID_VAPOR:
                queries.add(query);
                addLiquidVapor(eqCurve);
                return Utils.QUERY_SUCCESS;
            case Utils.QUERY_VAPOR_SOLID1:
                queries.add(query);
                addVaporSolid1(eqCurve);
                return Utils.QUERY_SUCCESS;
            case Utils.QUERY_VAPOR_SOLID2:
                queries.add(query);
                addVaporSolid2(eqCurve);
                return Utils.QUERY_SUCCESS;
            default:
                return Utils.QUERY_ERROR_UNEXPECTED;
        }

    }

    public String makeQueryOther(String query, float temp, float dpdt) {
        switch (query) {
            case Utils.QUERY_LIQUID_SOLID1:
                if (addLiquidSolid1(temp,dpdt)) {
                    queries.add(query);
                    return Utils.QUERY_SUCCESS;
                }
                return Utils.QUERY_ERROR_LIQUID_SOLID1;

            case Utils.QUERY_LIQUID_SOLID2:
                if (addLiquidSolid2(temp,dpdt)) {
                    queries.add(query);
                    return Utils.QUERY_SUCCESS;
                }
                return Utils.QUERY_ERROR_LIQUID_SOLID2;

            case Utils.QUERY_SOLID1_SOLID2:
                if (addSolid1Solid2(temp,dpdt)) {
                    queries.add(query);
                    return Utils.QUERY_SUCCESS;
                }
                return Utils.QUERY_ERROR_SOLID1_SOLID2;

            default:
                return Utils.QUERY_ERROR_UNEXPECTED;


        }
    }

    private void addLiquidVapor(VaporSth eqCurve) {
        materialProperties.setVaporLiquid(eqCurve.getA(),eqCurve.getB(),eqCurve.getC(),eqCurve.isLog());
        checkIfWeCanAdd();
        /* TODO
        Add to graphic
         */
    }

    private void addVaporSolid1(VaporSth eqCurve) {
        materialProperties.setVaporSolid1(eqCurve.getA(),eqCurve.getB(),eqCurve.getC(),eqCurve.isLog());
        checkIfWeCanAdd();
        /* TODO
        Add to graphic
         */
    }

    private void addVaporSolid2(VaporSth eqCurve) {
        materialProperties.setVaporSolid2(eqCurve.getA(),eqCurve.getB(),eqCurve.getC(),eqCurve.isLog());
        checkIfWeCanAdd();
        /* TODO
        Add to graphic
         */
    }

    private boolean addLiquidSolid1(float temp, float dpdt) {
        if (queries.contains(Utils.QUERY_LIQUID_VAPOR) ||
                queries.contains(Utils.QUERY_VAPOR_SOLID1)   ) {
            materialProperties.setLiquidSolid1(dpdt);
            materialProperties.setTempLV1(temp);
        } else return false;
        /* TODO
        Add to graphic
         */
        checkIfWeCanAdd();
        return true;
    }

    private boolean addLiquidSolid2(float temp, float dpdt) {
        if (queries.contains(Utils.QUERY_LIQUID_VAPOR) ||
                queries.contains(Utils.QUERY_VAPOR_SOLID2)   ) {
            materialProperties.setLiquidSolid2(dpdt);
            materialProperties.setTempLV2(temp);
        } else return false;
        /* TODO
        Add to graphic
         */
        checkIfWeCanAdd();
        return true;
    }

    private boolean addSolid1Solid2(float temp, float dpdt) {
        if (queries.contains(Utils.QUERY_VAPOR_SOLID1) ||
                queries.contains(Utils.QUERY_VAPOR_SOLID2)) {
            materialProperties.setLiquidSolid2(dpdt);
            materialProperties.setTempLV2(temp);
        } else return false;
        /* TODO
        Add to graphic
         */
        checkIfWeCanAdd();
        return true;
    }

    private void checkIfWeCanAdd(){
        /* TODO
        Implement checkIfWeCanAdd
         */
        /*
        String lastQuery = queries.get(queries.size()-1);
        switch (lastQuery) {
            case Utils.QUERY_LIQUID_VAPOR:
                if (queries.contains(Utils.QUERY_VAPOR_SOLID1)) {

                } else if (queries.contains())
            case Utils.QUERY_VAPOR_SOLID1:

            case Utils.QUERY_VAPOR_SOLID2:


            case Utils.QUERY_LIQUID_SOLID1:

            case Utils.QUERY_LIQUID_SOLID2:

            case Utils.QUERY_SOLID1_SOLID2:
        }
        */
    }



}
