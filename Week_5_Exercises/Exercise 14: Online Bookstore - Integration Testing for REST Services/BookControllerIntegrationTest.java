import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.repository.BookRepository;

@SpringBootTest
@AutoConfiguration
@Transactional
public class BookControllerIntegrationTest<MockMvc> {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        // Clear the database before each test
        bookRepository.deleteAll();
        
        // Add a sample book to the database
        Book book = new Book(null, "Sample Book", "Author", 19.99, "1234567890");
        bookRepository.save(book);
    }

    @Test
    public void testGetBookById() throws Exception {
        // Fetch the sample book by ID
        Book book = bookRepository.findAll().get(0);

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value("Sample Book"));
    }

    @Test
    public void testCreateBook() throws Exception {
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New Book\",\"author\":\"New Author\",\"price\":29.99,\"isbn\":\"0987654321\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Book"));

        // Verify the book was created in the database
        Book book = bookRepository.findByIsbn("0987654321");
        assert book != null;
    }

    @Test
    public void testUpdateBook() throws Exception {
        // Fetch the sample book by ID
        Book book = bookRepository.findAll().get(0);

        mockMvc.perform(put("/books/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Book\",\"author\":\"Updated Author\",\"price\":39.99,\"isbn\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));

        // Verify the book was updated in the database
        Book updatedBook = bookRepository.findById(book.getId()).orElse(null);
        assert updatedBook != null;
        assert updatedBook.getTitle().equals("Updated Book");
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Fetch the sample book by ID
        Book book = bookRepository.findAll().get(0);

        mockMvc.perform(delete("/books/" + book.getId()))
                .andExpect(status().isNoContent());

        // Verify the book was deleted from the database
        Book deletedBook = bookRepository.findById(book.getId()).orElse(null);
        assert deletedBook == null;
    }
}
