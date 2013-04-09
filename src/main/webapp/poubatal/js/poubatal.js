//var url = "http://localhost:8080";
var url = "http://poubatal.appspot.com";

// ACCOUNT
$('#connect').click(function() {
    handleAuthClick();
});
$('#delete-account').click(function() {
    var authResult = gapi.auth.getToken();
    apiAccount(authResult.access_token);
});

// DIRECTORY
$('#directory').click(function() {
    apiDirectory();
});

// NOTIFICATION
$('#notificationsList').click(function() {
    var authResult = gapi.auth.getToken();
    apiNotification('GET', authResult.access_token);
});
$('#add-notification').click(function() {
    var authResult = gapi.auth.getToken();
    var notification = $('#input-notification').val();
    var type = $('#select-notification').val();
    apiNotification('POST', authResult.access_token, notification, type);
});
$('#delete-notification').click(function() {
    var authResult = gapi.auth.getToken();
    var notification = $('#input-notification').val();
    var type = $('#select-notification').val();
    notification = type + "-" + notification;
    apiNotification('DELETE', authResult.access_token, notification);
});

// ADDRESS
$('#addressesList').click(function() {
    var authResult = gapi.auth.getToken();
    apiAddress('GET', authResult.access_token);
});
$('#add-address').click(function() {
    var authResult = gapi.auth.getToken();
    var address = $('#input-address').val();
    apiAddress('POST', authResult.access_token, address);
});
$('#delete-address').click(function() {
    var authResult = gapi.auth.getToken();
    var address = $('#input-address').val();
    apiAddress('DELETE', authResult.access_token, address);
});