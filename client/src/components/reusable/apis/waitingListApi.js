const getFetchOptions = () => {
    const headers = new Headers();
    headers.append("Authorization", `Bearer ${localStorage.getItem("token")}`)
    return {
        headers
    }
}

export default {
    getInfoByUserId: async (userId) => {
        const options = getFetchOptions();
        const response = await fetch(`${process.env.REACT_APP_URL}/api/waiting/${userId}`, {
            ...options,
            method: "GET",
        });
        if(response && !response.ok){
            const errorMessage = await response.json()
            throw new Error(errorMessage.message);
        }
        return response.json();
    },
}


