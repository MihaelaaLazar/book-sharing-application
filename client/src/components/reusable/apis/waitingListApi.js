const headers = new Headers();
headers.append("Authorization",`Bearer ${localStorage.getItem("token")}`);
headers.append("Content-Type", "application/json");

export default {
    getInfoByUserId: async (userId) => {
        const response = await fetch(`http://localhost:8080/api/waiting/${userId}`, {
            method: "GET",
            headers: headers,
        });
        if(response && !response.ok){
            const errorMessage = await response.json()
            throw new Error(errorMessage.message);
        }
        return await response.json();
    },
}


