var clientId = '385110343883';
var scopes = ['https://www.googleapis.com/auth/userinfo.email',
    'https://www.googleapis.com/auth/userinfo.profile'];
var connected = false;

function handleClientLoad() {
    setTimeout(checkAuth, 1); // L'exemple utilisait un setTimeout, donc je l'ai laissé
}

// Vérification automatique des droits de l'application
function checkAuth() {
    $('#button-connect').button('loading');
    gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
}

function handleAuthResult(authResult) {
    if (authResult && !authResult.error) {
        gapi.auth.setToken(authResult); // On stock le token dans l'application
        makeApiCall(function() {
            connected = true;
            $('.non-connected').fadeOut(400, function() {
                $('.connected').fadeIn(600);
            });
            apiGetAddress(authResult.access_token);
            apiGetNotification(authResult.access_token);
        });
    } else {
        connected = false;
        $('#button-connect').button('reset');
    }
}

// Vérification avec popup des droits de l'application
function handleAuthClick(event) {
    $('#button-connect').button('loading');
    gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
    return false;
}

// Chargement de l'API Oauth2 et récupération de l'ID
function makeApiCall(callback) {
    console.log('makeApiCall');
    gapi.client.load('oauth2', 'v2', function() {
        var request = gapi.client.oauth2.userinfo.get();
        request.execute(function(resp) {
            var email = resp.email;
            var picture = resp.picture.replace('photo.jpg', 's40-c-k/photo.jpg');
            $('#profil-email').html(email);
            $('#profil-image').attr('src', picture);
            callback();
        });
    });
}