const headers = new Headers();
headers.append("Authorization",`Bearer ${localStorage.getItem("token")}`);
headers.append("Content-Type", "application/json");

export default {
    rentBook: async (userId, bookRefId, rentalPeriod) => {
        const response = await fetch(`http://localhost:8080/api/booksForRent/${userId}/${bookRefId}/`, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(rentalPeriod)
        });
        return  response;
    },
    extendPeriod : async (rentedBookId, rentalPeriod) => {
        const response = await fetch(`http://localhost:8080/api/rentedBooks/${rentedBookId}`,{
            method: "PUT",
            headers: headers,
            body : JSON.stringify(rentalPeriod)
        });
        return response;
    }
}


