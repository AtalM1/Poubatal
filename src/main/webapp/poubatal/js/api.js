function apiAddressesList(accessToken) {
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