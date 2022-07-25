const headers = new Headers();
headers.append("Authorization",`Bearer ${localStorage.getItem("token")}`);
headers.append("Content-Type", "application/json");

export default {
    rentBook: async (userId, bookRefId, rentalPeriod) => {
        const response = await fetch(`http://localhost:8080/api/booksForRent/${userId}/${bookRefId}/`, {
            method: "POST",
            headers: {
                "Authorization" : `Bearer ${localStorage.getItem("token")}`,
                "Content-Type" : "application/json"
            },
            body: JSON.stringify(rentalPeriod)
        });
        return  response;
    }
}


