package ua.nure.tanasiuk.resource.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ua.nure.tanasiuk.resource.request.Token;

import java.io.IOException;

public class StringToTokenDeserializer extends JsonDeserializer<Token> {

    @Override
    public Token deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return new Token(jsonParser.getValueAsString());
    }

}
