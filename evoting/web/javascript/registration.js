let userName,ctznNo,email,mobileNo,password,confirmPassword,city,address;

function addUser(){
    userName = $("#username").val();
    console.log(userName);
    city = $("#city").val();
    console.log(city);
    password = $("#password").val();
    confirmPassword = $("#cpassword").val();
    email = $("#email").val();
    mobileNo = $("#mobile").val();
    ctznNo = $("#ctzn").val();
    console.log(ctznNo);
    address = $("#address").val();
    if(validateEmptyString()){
        if(password !== confirmPassword){
            swal("Error!","Please Enter same password","error");
            return;
        }else{
            if(!validateEmail()) return;
            let data = {
                userName : userName,
                userId : ctznNo,
                email : email,
                mobileNo : mobileNo,
                password : password,
                city : city,
                address : address
            };


            // sending data to server 
            let xhr = $.post("RegistrationControllerServlet", data,
                 (responseText, textStatus, jqXHR) => {
                    let string = responseText.trim();
                    if(string === "success"){
                        swal("Success!","Registration done successfully!","success");
                        setTimeout(()=>{
                            window.location = "login.html";
                        },3000);
                    }else if(string === "uap"){
                        swal("Error!","Sorry! UserId is already present, Try another","error");
                    }else{
                        swal("Error!","Registration Failed","error");
                    }
                });
            
            // handling the xhr error 
            xhr.fail((xhr) => {
                swal("Error!","Problem in server communication : "+ xhr.statusText,"error");
            });

        }
    }
    
}

// validating the empty string
function validateEmptyString(){
    if(userName === "" || ctznNo === "" || email === "" || mobileNo === "" || password === "" || confirmPassword === "" || city === "" || addUser === ""){
        swal("Error!","All field are mandatory","error");
        return false;
    }
    return true;
}

// validating email id 
function validateEmail(){
    let atdaratePosition = email.indexOf("@");
    let dotPosition = email.lastIndexOf(".");
    if(atdaratePosition < 1 || dotPosition < atdaratePosition+2 || dotPosition+2 > email.length){
        swal("Error!","Please enter a valid email " , "error");
        return false;
    }
    return true;
}