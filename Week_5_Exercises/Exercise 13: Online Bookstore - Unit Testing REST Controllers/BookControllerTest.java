import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.BookstoreAPI.controller.BookController;
import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    public void setup() {
        sampleBook = new Book(1L, "Sample Book", "Author", 19.99, "1234567890");
    }

    @Test
    public void testGetBookById() throws Exception {
        Mockito.when(bookService.getBookById(1L)).thenReturn(sampleBook);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Sample Book"));
    }

    @Test
    public void testCreateBook() throws Exception {
        Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(sampleBook);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Sample Book\",\"author\":\"Author\",\"price\":19.99,\"isbn\":\"1234567890\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Sample Book"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Mockito.when(bookService.updateBook(Mockito.anyLong(), Mockito.any(Book.class))).thenReturn(sampleBook);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Book\",\"author\":\"Author\",\"price\":29.99,\"isbn\":\"0987654321\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }
}
