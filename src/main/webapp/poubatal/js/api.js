function apiAddressesList(accessToken) {
    var addressesList;
    $.ajax({
        url: 'http://poubatal.appspot.com/api/addresses-list',
        type: 'POST',
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
        url: 'http://poubatal.appspot.com/api/notifications-list',
        type: 'POST',
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
        url: 'http://localhost:8080/api/addresses-list',
        type: 'POST',
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
        url: 'http://localhost:8080/api/notifications-list',
        type: 'POST',
        dataType: 'json',
        data: {oauth: accessToken},
        success: function(response) {
            console.log(response);
        }
    });
    return addressesList;
}