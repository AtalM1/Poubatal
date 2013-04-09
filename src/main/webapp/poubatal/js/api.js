// ACCOUNT
function apiAccount(accessToken) {
    $.ajax({
        url: url + '/api/account',
        type: 'POST',
        dataType: 'json',
        data: {oauth: accessToken, method: "delete"},
        success: function(response) {
            console.log(response);
        }
    });
}

// DIRECTORY
function apiDirectory() {
    $.ajax({
        url: url + '/api/directory',
        type: "GET",
        dataType: 'json',
        success: function(response) {
            console.log(response);
        }
    });
}

// NOTIFICATION
function apiNotification(method, accessToken, notification, notificationType) {
    method = method.toUpperCase();
    var type;
    var data;
    if (method === "DELETE") {
        type = "POST";
        data = {oauth: accessToken, notification: notification, method: method};
    } else {
        type = method;
        if (method === "GET") {
            data = {oauth: accessToken};
        } else {
            data = {oauth: accessToken, type: notificationType, email: notification};
        }
    }
    $.ajax({
        url: url + '/api/notification',
        type: type,
        dataType: 'json',
        data: data,
        success: function(response) {
            console.log(response);
        }
    });
}

// ADDRESS
function apiAddress(method, accessToken, address) {
    method = method.toUpperCase();
    var type;
    var data;
    if (method === "DELETE") {
        type = "POST";
        data = {oauth: accessToken, address: address, method: method};
    } else {
        type = method;
        if (method === "GET") {
            data = {oauth: accessToken};
        } else {
            data = {oauth: accessToken, address: address};
        }
    }
    $.ajax({
        url: url + '/api/address',
        type: type,
        dataType: 'json',
        data: data,
        success: function(response) {
            console.log(response);
        }
    });
}