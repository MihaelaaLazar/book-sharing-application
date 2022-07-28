const headers = new Headers();
headers.append("Authorization",`Bearer ${localStorage.getItem("token")}`)

export default {
    login : async (values) => {
       const res = await fetch('http://localhost:8080/api/users/login', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(values),
        });
       if(res && !res.ok){
           const errorMessage = await res.text()
           throw new Error(errorMessage);
       }
       return await res.json();

    },
    verify : async (token) => {
        const res = await fetch(`http://localhost:8080/api/users/verify/${token}`);
            return await res.json();
    },
    addOnWaitingList : async(userId, bookId) =>{
        const res = await fetch(`http://localhost:8080/api/waiting/${userId}/${bookId}`,{
            method: "POST",
            headers: headers,
            body : JSON.stringify({userId, bookId})
        });
        if(res && !res.ok){
            const errorMessage = await res.text();
            throw new Error(errorMessage);
        }
        return res;
    }
}