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

$('#addressesListLocal').click(function() {
    var authResult = gapi.auth.getToken();
    console.log(authResult);
    console.log(authResult.access_token);
    apiAddressesListLocal(authResult.access_token); 
});

$('#notificationsListLocal').click(function() {
    var authResult = gapi.auth.getToken();
    console.log(authResult);
    console.log(authResult.access_token);
    apiNotificationsListLocal(authResult.access_token); 
});

function connect() {
    console.log("connect");
    handleAuthClick();
}

function verify() {
    console.log("verify");
    makeApiCall();
}