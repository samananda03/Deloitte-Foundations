const frontendCourse = ["HTML","CSS","Bootstrap"]
const backendCourse = ["node.js","spring","django"]
const fullStack = [...frontendCourse,...backendCourse]
console.log(fullStack);

const user = {
    name:'saman',
    dob:'08/04/03',
    email:"saman@deloitte.com"
}
const rolebasedUser = {...user,role:'normalUser'}
console.log(rolebasedUser);
const myuser = {
    id:2,
    username: "saman03",
    email:'saman@gmail.com',
    password:'Admin123'
}