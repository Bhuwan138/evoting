let userId,password;

function connectUser(){
    userId = $("#userName").val();
    password = $("#password").val();
    if(validateEmptyString()){
        let data = {
            userId : userId,
            password : password 
        };

        let xhr = $.post("LoginControllerServlet", data,
            function (responseText, textStatus, jqXHR) {
                console.log(responseText);
                if(responseText.trim() === "error"){
                    swal("Access Denied!","Please enter id/password correctly","error");
                }else if(responseText.trim().indexOf("jsessionid") !== -1){
                    swal("Success!","Login Successful!","success").then(value =>{
                        window.location = responseText.trim();
                    });
                }else{
                    swal("Access Denied!","Some Problem Occurred" + responseText,"error");
                }
            });

        xhr.fail(() =>{
            swal("Error!","Problem in server communication : "+ xhr.statusText,"error");
        });
    }

}

// validating the empty string
function validateEmptyString(){
    if(userId === "" || password === "" ){
        swal("Error!","All field are mandatory","error");
        return false;
    }
    return true;
}