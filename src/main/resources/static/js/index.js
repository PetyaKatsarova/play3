const loginTenantForm = document.getElementById("loginTenantForm");
const loginLandlordForm = document.getElementById("loginLandlordForm");
const registrationForm = document.getElementById("registrationForm");
const registerSuccess = document.getElementById("register-success");
const loginTenantSuccess = document.getElementById("loginTenantSuccess");
const loginLandlordSuccess = document.getElementById("loginLandlordSuccess");

// --------------------------- toggle display of login or registration forms --------------------------------------------
document.getElementById("displayTenantLogin").addEventListener("click", (e) => {
    displayNone(registrationForm);
    displayNone(registerSuccess);
    displayNone(loginTenantSuccess);
    displayNone(loginLandlordSuccess);
    loginTenantForm.style.display = (loginTenantForm.style.display === "none" || loginTenantForm.style.display === "") ? "block" : "none";
});
document.getElementById("displayLandlordLogin").addEventListener("click", (e) => {
    displayNone(registrationForm);
    displayNone(registerSuccess);
    displayNone(loginTenantSuccess);
    displayNone(loginLandlordSuccess);
    loginLandlordForm.style.display = (loginLandlordForm.style.display === "none" || loginLandlordForm.style.display === "") ? "block" : "none";
});
document.getElementById("displayRegistration").addEventListener("click", (e) => {
    displayNone(loginTenantSuccess);
    displayNone(loginLandlordSuccess);
    displayNone(registerSuccess);
    registrationForm.style.display = (registrationForm.style.display === "none" || registrationForm.style.display === "") ? "block" : "none";
});

function displayNone(el) {
    if (el.style.display !== "none")
        el.style.display = "none";
}
// --------------------------------- REGISTER ---------------------------------------------------
registrationForm.addEventListener('submit', function (e) {
    e.preventDefault();  // Prevent the default form submission

    if (document.querySelector("#registrationPassword").value !== document.querySelector("#registrationPasswordConfirm").value) {
        alert("your pword and confirmedpwd dont match, fix it");
        return;
    }

    const formData = new FormData(e.target);
    const jsonData = {
        username: formData.get("registrationUsername"),
        email: formData.get("registrationEmail"),
        password: formData.get("registrationPassword")
    };

    console.log(JSON.stringify(jsonData));
    fetch('http://localhost:8080/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => response.text())
        .then(data => {
            // alert(data);
            if (data === "User was saved succesfully.") {
                // window.location.href = 'http://localhost:8080/login';
                registerSuccess.style.display = "block";
                registrationForm.style.display = "none";
            }
        })
        .catch(error => {
            alert('Error registering: ' + error);
        });
});

// ----------------------------- LOGIN TENANT--------------------------------------------------------------
loginTenantForm.addEventListener('submit', function (e) {
    e.preventDefault();

});

// ---------------------------- LOGIN AS LANDLORD -------------------------------------------------
loginLandlordForm.addEventListener("click", (e) => {

})

function commonLogin(name, Name, loginForm) {
    let loginEmail = document.getElementById(`login${Name}Email`).value;
    let loginPassword = document.getElementById(`login${Name}Email`).value;

    fetch(`http://localhost:8080/login/${name}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // 'Authorization': jwtToken
        },
        body: JSON.stringify({loginEmail: loginEmail, loginPassword: loginPassword})
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem("jwtToken", data.jwtToken);
                displayNone(loginForm);
                loginSuccess.style.display = "inline-block";
                setTimeout(()  =>  loginSuccess.style.display = "none", 4000);
                console.log(data)
            } else {
                alert("kuku, sth is wrong " + data.message);
            }
        })
        .catch(error => {
            alert('Error during login: ' + error);
        });
}