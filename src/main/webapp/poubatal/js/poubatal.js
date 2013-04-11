(function() {
    $('#address-selector .loading-indicator').fadeIn(200);
    $('#input-address').attr('disabled', 'disabled');
    $.ajax({
        url: '/api/directory',
        type: 'GET',
        dataType: 'json',
        complete: function() {
            $('#address-selector .loading-indicator').fadeOut(600);
        },
        success: function(response) {
            $('#input-address').removeAttr('disabled');
            $('#input-address').typeahead({
                name: 'directory',
                valueKey: 'streetName',
                local: response.data,
                template: [
                    '<p class="repo-street-name">{{streetName}}</p>',
                    '<p class="repo-number-description">{{numberDescription}}</p>'
                ].join(''),
                engine: Hogan
            });
            $('#input-address').bind('typeahead:selected', function(event, address) {
                var html = '<div class="alert alert-info">';
                html += '<div class="div-icon-add">';
                html += '<a class="icon-add"><i class="icon-plus-sign"></i></a>';
                html += '</div>';
                html += '<h3>' + address.streetName + '</h3>';
                if (address.numberDescription) {
                    html += '<p><em>' + address.numberDescription + '</em></p>';
                }
                html += '<ul><li><strong>Jours bleus : </strong>';
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
                html += '</li></ul></div>';
                $('#address-results').html(html);
                console.log(connected);
                if (connected) {
                    bindAddressSelector();
                } else {
                    unbindAddressSelector();
                }                
                $('#address-results').slideDown(400);
                $('#address-results .icon-add').click(function() {
                    $('#address-results').slideUp(400);
                    var authResult = gapi.auth.getToken();
                    if (!authResult) {
                        checkAuth();
                    } else {
                        apiAddAddress(authResult.access_token, address.id);
                    }
                });
            });
        }
    });
})();

// ACCOUNT
$('#button-connect').click(function() {
    handleAuthClick();
});

function bindElements() {
    $('.element').hover(function() {
        $(this).find('.icon-delete').show();
    }, function() {
        $(this).find('.icon-delete').hide();
    });
    $('.element .icon-delete i').hover(function() {
        $(this).addClass('icon-white');
    }, function() {
        $(this).removeClass('icon-white');
    });
    $('#addresses .element .icon-delete').click(function() {
        var authResult = gapi.auth.getToken();
        if (!authResult) {
            checkAuth();
        } else {
            var address = $(this).parents('.element').attr('id');
            apiRemoveAddress(authResult.access_token, address);
        }
    });
    $('#notifications .element .icon-delete').click(function() {
        var authResult = gapi.auth.getToken();
        if (!authResult) {
            checkAuth();
        } else {
            var notification = $(this).parents('.element').attr('id');
            apiRemoveNotification(authResult.access_token, notification);
        }
    });
}

function bindAddressSelector() {
    $('#address-results .alert').hover(function() {
        $(this).find('.icon-add').show();
    }, function() {
        $(this).find('.icon-add').hide();
    });
    $('#address-results .icon-add i').hover(function() {
        $(this).addClass('icon-white');
    }, function() {
        $(this).removeClass('icon-white');
    });
}
function unbindAddressSelector() {
    $('#address-results .alert').unbind();
    $('#address-results .icon-add i').unbind();
}

$('#add-notification').click(function() {
    var authResult = gapi.auth.getToken();
    if (!authResult) {
        checkAuth();
    } else {
        var type = $('#select-notification').val();
        var email = $('#input-notification').val();
        $('#input-notification').val('');
        email = $.trim(email);
        if (email) {
            apiAddNotification(authResult.access_token, type, email);
        }
    }
});

$('#delete-account').click(function() {
    var authResult = gapi.auth.getToken();
    if (!authResult) {
        checkAuth();
    } else {
        apiDeleteAccount(authResult.access_token);
    }
});