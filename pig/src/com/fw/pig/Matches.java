package com.fw.pig;

import java.io.IOException;

import org.apache.pig.FilterFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;

public class Matches extends FilterFunc {
	@Override
	public Boolean exec(Tuple input) throws IOException {
		if (input == null || input.size() < 2) {
			return false;
		}

		try {
			String value = (String) input.get(0);
			String regex = (String) input.get(1);

			if (value == null) {
				return false;
			} else {
				return value.matches(regex);
			}
		} catch (ExecException ee) {
			throw ee;
		}
	}
}