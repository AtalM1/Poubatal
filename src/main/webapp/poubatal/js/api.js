// ACCOUNT
function apiAccount(method, accessToken) {
    method = method.toUpperCase();
    $.ajax({
        url: url + '/api/user',
        type: 'POST',
        dataType: 'json',
        data: {oauth: accessToken, method: method},
        success: function(response) {
            console.log(response);
        }
    });
}

// DIRECTORY
function apiDirectory(method, accessToken, address) {
    method = method.toUpperCase();    
    $.ajax({
        url: url + '/api/directory',
        type: method,
        dataType: 'json',
        data: {oauth: accessToken, address: address},
        success: function(response) {
            console.log(response);
        }
    });
}

// NOTIFICATION
function apiNotification(method, accessToken, notification) {
    method = method.toUpperCase();
    $.ajax({
        url: url + '/api/notification',
        type: 'POST',
        dataType: 'json',
        data: {oauth: accessToken, notificationId: notification, method: method},
        success: function(response) {
            console.log(response);
        }
    });
}

// ADDRESS
function apiAddress(method, accessToken, address) {
    method = method.toUpperCase();
    $.ajax({
        url: url + '/api/address',
        type: 'POST',
        dataType: 'json',
        data: {oauth: accessToken, addressId: address, method: method},
        success: function(response) {
            console.log(response);
        }
    });
}