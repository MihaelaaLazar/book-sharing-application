const headers = new Headers();
headers.append("Authorization",`Bearer ${localStorage.getItem("token")}`);
headers.append("Content-Type", "application/json");

export default {
    rentBook: async (userId, bookRefId, rentalPeriod) => {
        return  fetch(`${process.env.REACT_APP_URL}/api/booksForRent/${userId}/${bookRefId}/`, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(rentalPeriod)
        });
    },
    extendPeriod : async (rentedBookId, rentalPeriod) => {
        return  fetch(`${process.env.REACT_APP_URL}/api/rentedBooks/${rentedBookId}`, {
            method: "PUT",
            headers: headers,
            body: JSON.stringify(rentalPeriod)
        });
    }
}


