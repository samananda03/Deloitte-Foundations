const api_url = "https://randomuser.me/api/?results=3";
fetch(api_url)
    .then(response => response.json())
    .then(data => {
        const users = data.results;
        const userDetails = users.map(user => {
            return `Username: ${user.login.username}
            Date of Birth: ${new Date(user.dob.date).toLocaleDateString()}
        City: ${user.location.city}`;
        });
        console.log(userDetails.join("\n\n")); 
    })
    .catch(err => console.log("Error in fetching data:", err));
