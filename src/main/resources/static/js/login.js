function checkPhone() {
    var phone =document.querySelector(".phonedk").value;
    fetch('http://localhost:8080/api/users/exists/'+phone,{
        method: 'GET'
    })
        .then(res=> res.text())
        .then(text=>{
            if(text=="true")
                document.querySelector(".message_phone").innerHTML = "số điện thoại đã được sử dụng" ;
            else
                document.querySelector(".message_phone").innerHTML = "" ;
        }) ;

}
function checkPw(){
    const cfPassword =document.querySelector(".cf_password");
    const password = document.querySelector(".password");
    cfPassword.onchange
    if(cfPassword.value!=password.value)
        document.querySelector(".message_pw").innerHTML = "xác thực sai"
    if(cfPassword.value==password.value)
        document.querySelector(".message_pw").innerHTML = ""
}
const loginText = document.querySelector(".title-text .login");
const loginForm = document.querySelector("form.login");
const loginBtn = document.querySelector("label.login");
const signupBtn = document.querySelector("label.signup");
const signupLink = document.querySelector("form .signup-link a");
signupBtn.onclick = (()=>{
    loginForm.style.marginLeft = "-50%";
    loginText.style.marginLeft = "-50%";
});
loginBtn.onclick = (()=>{
    loginForm.style.marginLeft = "0%";
    loginText.style.marginLeft = "0%";
});
signupLink.onclick = (()=>{
    signupBtn.click();
    return false;
});
