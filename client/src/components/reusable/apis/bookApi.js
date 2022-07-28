const headers = new Headers();
headers.append("Authorization",`Bearer ${localStorage.getItem("token")}`)

export default {
    bookByUserId : async (userId) => {
        const response = await fetch(`http://localhost:8080/api/books/${userId}`, {
            headers: headers
        });
        return await response.json();
    },

    getAllBooks : async () => {
        const response = await fetch(`http://localhost:8080/api/books`,{
            headers : headers
        });
        return await response.json();
    },

    getAllBooksWithPagination : async (page, pageSize) => {
        return await fetch(`http://localhost:8080/api/books/${page}/${pageSize}`, {
            headers : headers
        });
    },
    getAllBooksWithUserIdAndPagination : async (userId, page, pageSize) => {
        return await fetch(`http://localhost:8080/api/books/${userId}/${page}/${pageSize}`, {
            headers : headers
        });
    },
    addBook : async (userId, book, file) => {
        const formData = new FormData();
        formData.append("body", JSON.stringify(book));
        formData.append("file", file);

        return await fetch(`http://localhost:8080/api/books/${userId}/create`, {
            method: "POST",
            headers : headers,
            body: formData
        });
    },

    getAvailableBooks : async () => {
        return fetch("http://localhost:8080/api/booksForRent", {
            headers: headers
        });
    },

    getAvailableBooksWithPagination : async (page, pageSize ) => {
        return fetch(`http://localhost:8080/api/booksForRent/${page}/${pageSize}`, {
            headers : headers
        });
    },

    getBookByBookId : async (bookId) => {
        return fetch (`http://localhost:8080/api/books/bookData/${bookId}`,
            {
                headers: headers
            });
    },
    searchBook : async (query, page, pageSize) => {
            return fetch(`http://localhost:8080/api/books/search/${page}/${pageSize}?query=${query}`, {
                headers : headers
            });

    }
}