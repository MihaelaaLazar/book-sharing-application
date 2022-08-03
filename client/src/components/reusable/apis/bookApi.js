const getFetchOptions = () => {
    const headers = new Headers();
    headers.append("Authorization", `Bearer ${localStorage.getItem("token")}`)
    return {
        headers
    }
}
export default {
    getAllBooksWithPagination: async (page, pageSize) => {
        const options = getFetchOptions();
        return  fetch(`${process.env.REACT_APP_URL}/api/books/${page}/${pageSize}`, {...options});
    },
    getAllBooksWithUserIdAndPagination: async (userId, page, pageSize) => {
        const options = getFetchOptions();
        return fetch(`${process.env.REACT_APP_URL}/api/books/${userId}/${page}/${pageSize}`, {...options});
    },
    addBook: async (userId, book, file) => {
        const options = getFetchOptions();
        const formData = new FormData();
        formData.append("body", JSON.stringify(book));
        formData.append("file", file);

        return fetch(`${process.env.REACT_APP_URL}/api/books/${userId}/create`, {
            ...options,
            method: "POST",
            body: formData
        });
    },

    getAvailableBooksWithPagination: async (page, pageSize) => {
        const options = getFetchOptions();
        return fetch(`${process.env.REACT_APP_URL}/api/booksForRent/${page}/${pageSize}`, {...options});
    },

    getBookByBookId: async (bookId) => {
        const options = getFetchOptions();
        return fetch(`${process.env.REACT_APP_URL}/api/books/bookData/${bookId}`, {...options});
    },
    searchBook: async (query, page, pageSize) => {
        const options = getFetchOptions();
        return fetch(`${process.env.REACT_APP_URL}/api/books/search/${page}/${pageSize}?query=${query}`, {...options});

    },

}