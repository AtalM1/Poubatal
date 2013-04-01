var url = "http://localhost:8080";

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

$('#notificationsList').click(function() {
    var authResult = gapi.auth.getToken();
    console.log(authResult);
    console.log(authResult.access_token);
    apiNotificationsList(authResult.access_token); 
});

$('#directory').click(function() {
    var authResult = gapi.auth.getToken();
    var address = $('#input-directory').val();
    console.log(authResult);
    console.log(authResult.access_token);
    apiDirectory(authResult.access_token, address); 
});

$('#add-address').click(function() {
    var authResult = gapi.auth.getToken();
    var address = $('#input-address').val();
    apiAddAddress(authResult.access_token, address); 
});

function connect() {
    console.log("connect");
    handleAuthClick();
}

function verify() {
    console.log("verify");
    makeApiCall();
}