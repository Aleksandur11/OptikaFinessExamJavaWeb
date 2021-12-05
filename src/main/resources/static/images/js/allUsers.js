
const rolesCtr=document.getElementById("rolesContainer");


const allRoles=[];

const displayRoles=(roles) => {

    rolesCtr.innerHTML=roles.map(
        (r)=>{
            return asRole(r)
        }
    ).join(' ')

}

function asRole(r){
    let roleHtml=`<div class="vanko1 col-sm-3" id="rolesCOntainer-${r.userId}">`
    roleHtml+=`<div class="stanislava1">`
    roleHtml+=`<p>First Name:${r.firstName}</p>`
    roleHtml+=`<p>Last Name:${r.lastName}</p>`
    roleHtml+=`<p>${r.userRole}</p></div></div>`


    return roleHtml;
}

fetch('http://localhost:8080/api/all/users').then(response=>response.json())
    .then(data=>{
        for (let role of data) {
            allRoles.push(role)
        }
        displayRoles(allRoles)
    })
