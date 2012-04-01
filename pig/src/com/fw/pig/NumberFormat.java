package com.fw.pig;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.pig.EvalFunc;
import org.apache.pig.FuncSpec;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.logicalLayer.FrontendException;
import org.apache.pig.impl.logicalLayer.schema.Schema;

import com.fw.pig.util.SchemaUtils;

public class NumberFormat extends EvalFunc<String> {
    @Override
    public String exec(Tuple input) throws IOException {
        if (input == null || input.size() < 2) {
            log.warn("invalid number of arguments");
            return null;
        }

        try {
            Double number = DataType.toDouble(input.get(0));
            String pattern = (String) input.get(1);            
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(number);
        } catch (NumberFormatException nfe) {
            log.error("Failed to process input; error - " + nfe.getMessage());
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Schema outputSchema(Schema input) {
        return new Schema(new Schema.FieldSchema(null, DataType.CHARARRAY));
    }

    @Override
    public List<FuncSpec> getArgToFuncMapping() throws FrontendException {
        List<FuncSpec> funcList = new ArrayList<FuncSpec>();

        funcList.add(SchemaUtils.getFuncSpecByTypes(this, DataType.INTEGER, DataType.CHARARRAY));
        funcList.add(SchemaUtils.getFuncSpecByTypes(this, DataType.LONG, DataType.CHARARRAY));
        funcList.add(SchemaUtils.getFuncSpecByTypes(this, DataType.FLOAT, DataType.CHARARRAY));
        funcList.add(SchemaUtils.getFuncSpecByTypes(this, DataType.DOUBLE, DataType.CHARARRAY));

        return funcList;
    }
}
