var clientId = '385110343883';
var scopes = ['https://www.googleapis.com/auth/userinfo.email',
    'https://www.googleapis.com/auth/userinfo.profile'];

function handleClientLoad() {
    setTimeout(checkAuth, 1); // L'exemple utilisait un setTimeout, donc je l'ai laissé
}

// Vérification automatique des droits de l'application
function checkAuth() {
    gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
}

function handleAuthResult(authResult) {
    if (authResult && !authResult.error) {
        console.log('success');
        $('#connect').hide();
        gapi.auth.setToken(authResult); // On stock le token dans l'application
        console.log('accessToken = ' + authResult.access_token);
        makeApiCall();
    } else {
        console.log('fail');
        $('#connect').removeAttr('disabled');
    }
}

// Vérification avec popup des droits de l'application
function handleAuthClick(event) {
    gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
    return false;
}

// Chargement de l'API Oauth2 et récupération de l'ID
function makeApiCall() {
    console.log('makeApiCall');
    gapi.client.load('oauth2', 'v2', function() {
        var request = gapi.client.oauth2.userinfo.get();
        request.execute(function(resp) {
            console.log(resp);
        });
    });
}