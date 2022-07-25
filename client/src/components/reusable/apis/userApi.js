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
       return await res.json();
    },
    verify : async (token) => {
        const res = await fetch(`http://localhost:8080/api/users/verify/${token}`);
            return await res.json();
    },
    addOnWaitingList : async(userId, bookId) =>{
        return  await fetch(`http://localhost:8080/api/waiting/${userId}/${bookId}`,{
            method: "POST",
            headers: headers,
            body : JSON.stringify({userId, bookId})
        });
    }
}