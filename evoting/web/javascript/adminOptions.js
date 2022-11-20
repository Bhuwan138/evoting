
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

