package ar.com.jmc.webplatform.base.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DateTimeJsonSerializer extends JsonSerializer<Date> {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void serialize(Date date, JsonGenerator json, SerializerProvider provider) throws IOException, JsonProcessingException {
		json.writeString(dateFormat.format(date));
	}

}
