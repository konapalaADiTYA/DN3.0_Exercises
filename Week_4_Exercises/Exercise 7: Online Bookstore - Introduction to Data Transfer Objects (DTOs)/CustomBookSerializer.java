import java.io.IOException;

import com.example.BookstoreAPI.dto.BookDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomBookSerializer extends JsonSerializer<BookDTO> {
    @Override
    public void serialize(BookDTO bookDTO, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    	 gen.writeStartObject(); 

         gen.writeNumberField("id", bookDTO.getId()); 
         gen.writeStringField("title", bookDTO.getTitle().toUpperCase());
         gen.writeStringField("author", bookDTO.getAuthor());
         gen.writeNumberField("price", bookDTO.getPrice());
         gen.writeStringField("isbn", bookDTO.getIsbn()); 

         gen.writeEndObject();
    }
}
