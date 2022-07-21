export default {
    bookByUserId : async (userId) => {
        const response = await fetch(`http://localhost:8080/api/books/${userId}`, {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("token")}`
            }
        });
        return await response.json();
    },

    getAllBooks : async () => {
        const response = await fetch(`http://localhost:8080/api/books`,{
            headers : {
                "Authorization" : `Bearer ${localStorage.getItem("token")}`
            }
        });
        return await response.json();
    },

    getAllBooksWithPagination : async (page, item) => {
        return await fetch(`http://localhost:8080/api/books/${page}/${item}`, {
            headers : {
                "Authorization" : `Bearer ${localStorage.getItem("token")}`
            }
        });
    },
}