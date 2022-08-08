const headers = new Headers();
headers.append("Authorization",`Bearer ${localStorage.getItem("token")}`)

export default {
    register : async (values) =>{
        return fetch(`${process.env.REACT_APP_URL}/api/users/register`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(values)
        });
    },
    login : async (values) => {
       const res = await fetch(`${process.env.REACT_APP_URL}/api/users/login`, {
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
       return res.json();

    },
    verify : async (token) => {
        const res =  await fetch(`${process.env.REACT_APP_URL}/api/users/verify/${token}`);
        if(res && !res.ok){
            const errorMessage = await res.json()
            throw new Error(errorMessage.message);
        }
        return  res.json();

    },
    addOnWaitingList : async(userId, bookId) =>{
        const res = await fetch(`${process.env.REACT_APP_URL}/api/waiting/${userId}/${bookId}`,{
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