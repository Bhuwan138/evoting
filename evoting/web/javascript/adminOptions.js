
function redirectAdministratorPage(){
    swal("Admin!","Redirecting to Admin Action Page!", "success").then(value=>{
        window.location = "adminActions.jsp";
    });
}

function redirectVotingPage(){
    swal("Admin!","Redirecting to Voting Controller Page!", "success").then(value=>{
        window.location = "VotingControllerServlet";
    });
}

function manageUser(){
    swal("Admin!","Redirecting to User Management Page!", "success").then(value=>{
        window.location = "manageUser.jsp";
    });
}

function manageCandidate(){
    swal("Admin!","Redirecting to Candidate Management Page!", "success").then(value=>{
        window.location = "manageCandidate.jsp";
    });
}


function showAddCandidateForm(){
    removeCandidateForm();
    var newDiv = document.createElement("div");
    newDiv.setAttribute("id","candidateForm");
    newDiv.innerHTML = `<h3>Add new Candidate</h3>`;
    newDiv.innerHTML =  newDiv.innerHTML + `
    <form method="post" enctype="multipart/form-data" id="fileUploadForm">
    <div class="form-group">
      <label for="cid">Candidate Id </label>
      <input type="text" class="form-control" id="cid" >
    </div>
    <div class="form-group">
      <label for="uid">User Id</label>
      <input type="text" class="form-control" id="uid" onkeypress="return getDetails(event)" placeholder="**-**-**-*****">
    </div>
    <div class="form-group">
      <label for="cName">Candidate Name</label>
      <input type="text" class="form-control" name="cName" id="cName"  placeholder="Ram Dhakal">
    </div>
    <div class="form-group">
        <label for="city">City</label>
        <select name="city" id="city" class="form-control">
        </select>
    </div>
    <div class="form-group">
        <label for="party">Party</label>
        <input type="text" class="form-control" id="party" " placeholder="party name">
    </div>

    <div class="custom-file form-group">
        <input type="file" class="custom-file-input" id="files" name="files">
        <label  class="custom-file-label" for="files">Select Image...</label>
    </div>
    <div class="form-group" >
        <button class="btn btn-primary"  onclick="addCandidateDetails()" id="addCandidate" type="button">Submit form</button>
        <button class="btn btn-danger" type="reset">Clear form</button>
    </div>
  </form>
    `;

    newDiv.innerHTML = newDiv.innerHTML + `<br> <span id="addResponse"></span>`;

    var addCandidate = $("#result")[0];
    addCandidate.appendChild(newDiv);

    let data = {
        id : "getId"
    };

    $.post("AddCandidateControllerServlet", data,
        function (responseText, textStatus, jqXHR) {
            $("#cid").val(responseText);
            $("#cid").prop("disabled",true);
        }
    );
}

function clearText(){
    $("#addResponse").html("");
}

function removeCandidateForm(){
    let contDiv = document.getElementById("result");
    let formDiv = document.getElementById("candidateForm");
    if(formDiv!== null){
        $("#candidateForm").fadeOut("3500");
        contDiv.removeChild(formDiv);
    }
}

function getDetails(e){
//    console.log("From getDetails");
   if(e.keyCode === 13){
        data = { 
            uid : $("#uid").val()  
        };

        $.post("AddCandidateControllerServlet", data,
        function (responseText) {
            let details = JSON.parse(responseText);
            let city = details.city;
            let userName  = details.userName;
            if(userName === "wrong"){
                swal("Wrong Citizenship Np!","Citizenship Number is invalid!","error");
            }
            else{
                $("#cName").val(userName);
                $("#city").empty();
                $("#city").append(city);

                // just to diable the text box of candidate name so that candidate name cannot be changed by us mistakely
                $("cName").prop("disabled",true);
            }
        }
    );
   }
}


function addCandidateDetails(){
    let form = $("#fileUploadForm")[0];
    let data = new FormData(form);
    data.append("candidateId",$("#cid").val());
    data.append("candidateName",$("#cName").val());
    data.append("cities",$("#city").val());
    data.append("party",$("#party").val());
    data.append("userId",$("#uid").val());

    $.ajax({
        type: "POST",
        url: "AddNewCandidateControllerServlet",
        data: data,
        enctype: "multipart/form-data",
        processData: false,  //It means data processing is done in server 
        contentType: false,
        cache: false,
        timeout : 600000,  //certing time period to wait to send data 
        success: function (data) {
            str = data + "...";
            swal("Admin!",str,"success").then((value)=>{
                showAddCandidateForm();
            });
        },
        error : function (e) {  
            swal("Admin!",e,"error");
        }
    });
}


function showCandidate(){
    removeCandidateForm();
    var newDiv = document.createElement("div");
    newDiv.setAttribute("id","candidateForm");
    newDiv.innerHTML = `<h3>Show Candidate</h3>`;
    newDiv.innerHTML =  newDiv.innerHTML + `
     <div style="color:#fff;font-weight:bold;"> Candidate Id</div>
     <div> <select id="cid"> </select> </div>
    `;

    newDiv.innerHTML = newDiv.innerHTML + `<br> <span id="addResponse"></span>`;

    var addCandidate = $("#result")[0];
    addCandidate.appendChild(newDiv);

    let data = {
        data : "cid"
    };

    $.post("ShowCandidateControllerServlet", data,
        function (responseText) {
            let candidateIdList = JSON.parse(responseText);
            $("#cid").append(candidateIdList.cid);
        }
    );

    $("#cid").change(()=>{
        let selectedCID = $("#cid").val();
        console.log(selectedCID)
        if(selectedCID===""){
            swal("No Selection!","Please, Select an ID","error");
            return;
        }

        let data = {
            data : selectedCID
        };
        $.post("ShowCandidateControllerServlet", data,
            function (responseText) {
                clearText();
                let details = JSON.parse(responseText);
                $("#addResponse").append(details.subDetails);
            }
        );

    });
}


function electionResult(){
    $.post("ElectionResultControllerServlet", null,
        function (responseText) {
            swal("Result Fetched","Success","success");
            $("#result").html(responseText.trim());
        }
    );
}

