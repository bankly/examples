package com.fw.pig.util;

import org.apache.pig.FuncSpec;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.apache.pig.impl.logicalLayer.schema.Schema.FieldSchema;

public final class SchemaUtils {
    public static Schema getSchemaByTypes(byte... types) {
        Schema s = new Schema();

        for(byte t : types) {
            FieldSchema fieldSchema = new Schema.FieldSchema(null, t);
            s.add(fieldSchema);
        }
        
        return s;
    }
    
    public static FuncSpec getFuncSpecByTypes(Class<?> cls, byte... types) {
        return new FuncSpec(cls.getName(), getSchemaByTypes(types));
    }
    
    public static FuncSpec getFuncSpecByTypes(Object obj, byte... types) {
        return new FuncSpec(obj.getClass().getName(), getSchemaByTypes(types));
    }
}