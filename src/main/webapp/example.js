const Content = {
    empty: "content_empty",
    form: "content_form",
    loggedIn: "content_logged_in"
}

var selectedRegButton = "doc";

window.addEventListener("load", (event) => {
    //page is loaded, we can now "get" elements
    console.log("page is fully loaded");

    displayContent(Content.empty);

    //add listeners
    document.getElementById("create_btn").addEventListener("click", create_btn_func);
    document.getElementById("drop_btn").addEventListener("click", drop_btn_func);
    document.getElementById("reg_doc_btn").addEventListener("click", reg_doc_btn_func);
    document.getElementById("reg_user_btn").addEventListener("click", reg_user_btn_func);
    document.getElementById("login_btn").addEventListener("click", login_btn_func);

    document.getElementById("reg_form").addEventListener("submit",
        (event) => {
            //prevent the default form submission behavior
            event.preventDefault();
            register();
        }
    );

    document.getElementById("login_form").addEventListener("submit",
        (event) => {
            //prevent the default form submission behavior
            event.preventDefault();
            login();
        }
    );

    document.getElementById("logout_btn").addEventListener("click", logout);
    document.getElementById("show_all_users_btn").addEventListener("click", loadUsers);
});

function displayContent(id) {
    document.getElementById(Content.empty).style.display = "none";
    document.getElementById(Content.form).style.display = "none";
    document.getElementById(Content.loggedIn).style.display = "none";

    document.getElementById(id).style.display = "flex";
}

function create_btn_func() {
    displayContent(Content.empty);

    const xhr = new XMLHttpRequest();
    xhr.onload = function () {
        const obj = JSON.parse(xhr.responseText);
        document.getElementById("footer_info_span").innerHTML = obj["msg"];
    };

    xhr.open("POST", "http://localhost:8080/example/api/database");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}

function drop_btn_func() {
    displayContent(Content.empty);

    const xhr = new XMLHttpRequest();
    xhr.onload = function () {
        const obj = JSON.parse(xhr.responseText);
        document.getElementById("footer_info_span").innerHTML = obj["msg"];
    };

    xhr.open("DELETE", "http://localhost:8080/example/api/database");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}

function reg_doc_btn_func() {
    selectedRegButton = "doc";

    displayContent(Content.form);
    changeSubmitButtonText("Confirm Doctor");
}

function reg_user_btn_func() {
    selectedRegButton = "user";

    displayContent(Content.form);
    changeSubmitButtonText("Confirm User");
}

function login_btn_func() {
    displayContent(Content.loggedIn);
}

function changeSubmitButtonText(text) {
    document.getElementById("reg_submit").value = text;
}


function register() {
    var jsonData = JSON.stringify(
        {
            username: document.getElementById("reg_username").value,
            email: document.getElementById("reg_email").value,
            password: document.getElementById("reg_pass").value
        }
    );

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        const obj = JSON.parse(xhr.responseText);
        document.getElementById("footer_info_span").innerHTML = obj["msg"];
    };

    if (selectedRegButton.includes("doc"))
        xhr.open("POST", "http://localhost:8080/example/api/doctor");
    else
        xhr.open("POST", "http://localhost:8080/example/api/user");

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(jsonData);

    //clear the values
    document.getElementById("reg_username").value = "";
    document.getElementById("reg_pass").value = "";
    document.getElementById("reg_email").value = "";
}

function login() {
    var jsonData = JSON.stringify(
        {
            username: document.getElementById("login_username").value,
            password: document.getElementById("login_pass").value
        }
    );

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        const obj = JSON.parse(xhr.responseText);
        if (xhr.readyState === 4 && xhr.status === 200)
            if (obj["type"].includes("doctor")) {
                document.getElementById("logged_in_container").style.display = "flex";
                document.getElementById("login_form").style.display = "none";
                document.getElementById("header").style.display = "none";
                document.getElementById("content_logged_in").style.height = "calc(100% - 50px)";
            } else {
                document.getElementById("logged_in_container").style.display = "flex";
                document.getElementById("login_form").style.display = "none";
                document.getElementById("header").style.display = "none";
                document.getElementById("content_logged_in").style.height = "calc(100% - 50px)";
                document.getElementById("main-bar").style.display = "none";
                document.getElementById("show_all_users_btn").disabled = true;
            }
        document.getElementById("footer_info_span").innerHTML = obj["msg"];
    };

    xhr.open("POST", "http://localhost:8080/example/api/login");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(jsonData);

    //clear the values
    document.getElementById("login_username").value = "";
    document.getElementById("login_pass").value = "";

}

function logout() {
    document.getElementById("content_logged_in").style.height = "calc(100% - 70px - 50px)";
    document.getElementById("logged_in_container").style.display = "none";
    document.getElementById("login_form").style.display = "flex";
    document.getElementById("main-bar").style.display = "flex";
    document.getElementById("show_all_users_btn").disabled = false;

    document.getElementById("header").style.display = "flex";

    displayContent(Content.empty);
}

function loadUsers() {
    const xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            const obj = JSON.parse(xhr.responseText); 
            document.getElementById("users_container").innerHTML = "";
            for (const iterator of obj) {
                document.getElementById("users_container").innerHTML = document.getElementById("users_container").innerHTML +
                    `<span class='user'>${iterator["username"]}</span>`;
            }
        } else if (xhr.status !== 200) {
            document.getElementById("footer_info_span").innerHTML = obj["msg"];
        }
    };

    xhr.open("GET", "http://localhost:8080/example/api/users");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}