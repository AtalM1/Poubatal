// ACCOUNT
function apiAccount(method, accessToken) {
    method = method.toUpperCase();
    var fullUrl;
    if (method === 'DELETE') {
        method = 'POST';
        fullUrl = url + '/api/delete-user';
    } else {
        fullUrl = url + '/api/user';
    }
    $.ajax({
        url: fullUrl,
        type: method,
        dataType: 'json',
        data: {oauth: accessToken},
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
    var fullUrl;
    if (method === 'DELETE') {
        method = 'POST';
        fullUrl = url + '/api/delete-notification';
    } else {
        fullUrl = url + '/api/notification';
    }
    $.ajax({
        url: fullUrl,
        type: method,
        dataType: 'json',
        data: {oauth: accessToken, notificationId: notification},
        success: function(response) {
            console.log(response);
        }
    });
}

// ADDRESS
function apiAddress(method, accessToken, address) {
    method = method.toUpperCase();
    var fullUrl;
    if (method === 'DELETE') {
        method = 'POST';
        fullUrl = url + '/api/delete-address';
    } else {
        fullUrl = url + '/api/address';
    }
    $.ajax({
        url: fullUrl,
        type: method,
        dataType: 'json',
        data: {oauth: accessToken, addressId: address},
        success: function(response) {
            console.log(response);
        }
    });
}