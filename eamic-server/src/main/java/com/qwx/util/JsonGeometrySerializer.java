package com.qwx.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Geometry;

public class JsonGeometrySerializer extends JsonSerializer<Geometry>{

	@Override
	public void serialize(Geometry arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
        String arg = arg0.toString();
        arg1.writeString(arg);
		
	}

}
