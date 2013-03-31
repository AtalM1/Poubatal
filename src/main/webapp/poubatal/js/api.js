function apiAddressesList(accessToken) {
    $.ajax({
        url: url + '/api/address',
        type: 'GET',
        dataType: 'json',
        data: {oauth: accessToken},
        success: function(response) {
            console.log(response);
        }
    });
}

function apiNotificationsList(accessToken) {
    $.ajax({
        url: url + '/api/notification',
        type: 'GET',
        dataType: 'json',
        data: {oauth: accessToken},
        success: function(response) {
            console.log(response);
        }
    });
}

function apiDirectory(accessToken, address) {
    $.ajax({
        url: url + '/api/directory',
        type: 'GET',
        dataType: 'json',
        data: {oauth: accessToken, address: address},
        success: function(response) {
            console.log(response);
        }
    });
}

function apiAddAddress(accessToken, address) {
    $.ajax({
        url: url + '/api/address',
        type: 'POST',
        dataType: 'json',
        data: {oauth: accessToken, addressId: address},
        success: function(response) {
            console.log(response);
        }
    });
}