// ACCOUNT
function apiDeleteAccount(oauth) {
    $.ajax({
        url: '/api/account',
        type: 'POST',
        dataType: 'json',
        data: {oauth: oauth, method: 'DELETE'},
        success: function(response) {
            $('#button-connect').button('reset');
            $('.connected').fadeOut(400, function() {
                $('.non-connected').fadeIn(600);
            });
        },
        error: function(jqXHR, status, error) {
            if (jqXHR.status === 200) {
                $('#address-results .div-icon-add').css('display', 'none');
                $('#button-connect').button('reset');
                $('.connected').fadeOut(400, function() {
                    $('.non-connected').fadeIn(600);
                });
            } else {
                console.log(jqXHR);
                console.log(status);
                console.log(error);
            }
        }
    });
}

//NOTIFICATION
function apiGetNotification(oauth) {
    $('#notifications-loader').fadeIn(200);
    $.ajax({
        url: '/api/notification',
        type: 'GET',
        dataType: 'json',
        data: {oauth: oauth},
        success: function(response) {
            var html = '';
            for (var index in response.data) {
                var notification = response.data[index];
                html += '<div id="' + notification.id + '" class="element address">';
                html += '<div class="div-icon-delete">';
                html += '<a class="icon-delete"><i class="icon-trash"></i></a></div>';
                html += '<div class="notification-infos">';
                html += '<h3>' + notification.email + ' ';
                if (notification.type === 'email') {
                    html += '<i class="icon-envelope"></i>';
                } else {
                    html += '<i class="icon-comment"></i>';
                }
                html += '</h3></div></div>';
            }
            $('#notifications .my-elements').fadeOut(200, function() {
                $('#notifications .my-elements').html(html);
                bindElements();
                $('#notifications .my-elements').fadeIn(400, function() {
                    $('#notifications-loader').fadeOut(200);
                });
            });
        },
        error: function(jqXHR, status, error) {
            console.log(jqXHR);
            console.log(status);
            console.log(error);
        }
    });
}
function apiAddNotification(oauth, type, email) {
    $.ajax({
        url: '/api/notification',
        type: 'POST',
        dataType: 'json',
        data: {oauth: oauth, type: type, email: email},
        success: function(response) {
            apiGetNotification(oauth);
        },
        error: function(jqXHR, status, error) {
            if (jqXHR.status === 200) {
                apiGetNotification(oauth);
            } else {
                console.log(jqXHR);
                console.log(status);
                console.log(error);
            }
        }
    });
}
function apiRemoveNotification(oauth, notification) {
    $.ajax({
        url: '/api/notification',
        type: 'POST',
        dataType: 'json',
        data: {method: 'DELETE', oauth: oauth, notification: notification},
        success: function(response) {
            apiGetNotification(oauth);
        },
        error: function(jqXHR, status, error) {
            if (jqXHR.status === 200) {
                apiGetNotification(oauth);
            } else {
                console.log(jqXHR);
                console.log(status);
                console.log(error);
            }
        }
    });
}

// ADDRESS
function apiGetAddress(oauth) {
    $('#addresses-loader').fadeIn(200);
    $.ajax({
        url: '/api/address',
        type: 'GET',
        dataType: 'json',
        data: {oauth: oauth},
        success: function(response) {
            var html = '';
            for (var index in response.data) {
                var address = response.data[index];
                html += '<div id="' + address.id + '" class="element address">';
                html += '<div class="div-icon-delete">';
                html += '<a class="icon-delete"><i class="icon-trash"></i></a></div>';
                html += '<div class="address-infos">';
                html += '<h3>' + address.streetName + '</h3>';
                if (address.numberDescription) {
                    html += '<p><em>' + address.numberDescription + '</em></p>';
                }
                html += '</div>';
                html += '<div class="address-days"><ul>';
                html += '<li><strong>Jours bleus : </strong>';
                if (address.blueBin.length > 0) {
                    if (address.blueBin[0]) {
                        html += address.blueBin.join(', ');
                    } else {
                        html += 'aucun';
                    }
                } else {
                    html += 'aucun';
                }
                html += '</li><li><strong>Jours jaunes : </strong>';
                if (address.yellowBin.length > 0) {
                    if (address.yellowBin[0]) {
                        html += address.yellowBin.join(', ');
                    } else {
                        html += 'aucun';
                    }
                } else {
                    html += 'aucun';
                }
                html += '</li></ul></div></div>';
            }
            $('#addresses .my-elements').fadeOut(200, function() {
                $('#addresses .my-elements').html(html);
                bindElements();
                $('#addresses .my-elements').fadeIn(400, function() {
                    $('#addresses-loader').fadeOut(200);
                });
            });

        },
        error: function(jqXHR, status, error) {
            console.log(jqXHR);
            console.log(status);
            console.log(error);
        }
    });
}
function apiAddAddress(oauth, address) {
    $.ajax({
        url: '/api/address',
        type: 'POST',
        dataType: 'json',
        data: {oauth: oauth, address: address},
        success: function(response) {
            apiGetAddress(oauth);
        },
        error: function(jqXHR, status, error) {
            if (jqXHR.status === 200) {
                apiGetAddress(oauth);
            } else {
                console.log(jqXHR);
                console.log(status);
                console.log(error);
            }
        }
    });
}
function apiRemoveAddress(oauth, address) {
    $.ajax({
        url: '/api/address',
        type: 'POST',
        dataType: 'json',
        data: {method: 'DELETE', oauth: oauth, address: address},
        success: function(response) {
            apiGetAddress(oauth);
        },
        error: function(jqXHR, status, error) {
            if (jqXHR.status === 200) {
                apiGetAddress(oauth);
            } else {
                console.log(jqXHR);
                console.log(status);
                console.log(error);
            }
        }
    });
}