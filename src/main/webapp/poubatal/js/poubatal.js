$('#connect').click(function() {
    connect();
});

$('#verify').click(function() {
    verify();
});

$('#addressesList').click(function() {
    var authResult = gapi.auth.getToken();
    console.log(authResult);
    console.log(authResult.access_token);
    apiAddressesList(authResult.access_token); 
});

function connect() {
    console.log("connect");
    handleAuthClick();
}

function verify() {
    console.log("verify");
    makeApiCall();
}