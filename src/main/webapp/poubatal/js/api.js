function apiAddressesList(accessToken) {
    var addressesList;
    $.ajax({
        url: 'http://poubatal.appspot.com/api/address',
        type: 'GET',
        dataType: 'json',
        data: {oauth: accessToken},
        success: function(response) {
            console.log(response);
        }
    });
    return addressesList;
}

function apiNotificationsList(accessToken) {
    var addressesList;
    $.ajax({
        url: 'http://poubatal.appspot.com/api/notification',
        type: 'GET',
        dataType: 'json',
        data: {oauth: accessToken},
        success: function(response) {
            console.log(response);
        }
    });
    return addressesList;
}

function apiAddressesListLocal(accessToken) {
    var addressesList;
    $.ajax({
        url: 'http://localhost:8080/api/address',
        type: 'GET',
        dataType: 'json',
        data: {oauth: accessToken},
        success: function(response) {
            console.log(response);
        }
    });
    return addressesList;
}

function apiNotificationsListLocal(accessToken) {
    var addressesList;
    $.ajax({
        url: 'http://localhost:8080/api/notification',
        type: 'GET',
        dataType: 'json',
        data: {oauth: accessToken},
        success: function(response) {
            console.log(response);
        }
    });
    return addressesList;
}