package util;

import com.endava.models.BookDto;
import com.endava.models.BooksForRentDto;
import com.endava.models.BooksRefDto;


import java.time.LocalDate;
import java.util.UUID;

public class CreateCustomBook {
    public static BooksForRentDto createBookForRent() {
        return new BooksForRentDto(UUID.randomUUID(),
                new BooksRefDto(UUID.randomUUID(),
                        new BookDto(
                                UUID.randomUUID(),
                                "Title",
                                "Author",
                                LocalDate.of(2020, 1, 1),
                                "Description",
                                "https://res.cloudinary.com/miha111/image/upload/v1659082185/cei5ky8y8a7uawgx5wjn.jpg"),
                        CreateCustomUser.johnDoe()));
    }
}
