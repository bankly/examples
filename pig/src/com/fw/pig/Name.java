package com.fw.pig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pig.EvalFunc;
import org.apache.pig.FuncSpec;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.apache.pig.impl.logicalLayer.FrontendException;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.apache.pig.impl.logicalLayer.schema.Schema.FieldSchema;

import com.fw.pig.util.SchemaUtils;

public class Name extends EvalFunc<Tuple> {
	private TupleFactory tupleFactory = TupleFactory.getInstance();

	@Override
	public Tuple exec(Tuple input) throws IOException {
		if (input == null || input.size() < 1) {
			System.out.println("illegal argument");
			return null;
		}

		try {
			String name = DataType.toString(input.get(0));
			if(name == null || name.isEmpty()) {
				return null;
			}
			
			String[] names = name.split(" ");

			Tuple tuple = tupleFactory.newTuple(2);
			tuple.set(0, names[0]);
			tuple.set(1, names[1]);

			return tuple;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Schema outputSchema(Schema input) {
		Schema schema = new Schema();
		schema.add(new FieldSchema("firstName", DataType.CHARARRAY));
		schema.add(new FieldSchema("lastName", DataType.CHARARRAY));

		try {
			return new Schema(new Schema.FieldSchema("name", schema, DataType.TUPLE));
		} catch (FrontendException e) {
			return null;
		}
	}

	@Override
	public List<FuncSpec> getArgToFuncMapping() throws FrontendException {
		List<FuncSpec> funcList = new ArrayList<FuncSpec>();

		funcList.add(SchemaUtils.getFuncSpecByTypes(this, DataType.CHARARRAY));

		return funcList;
	}
}