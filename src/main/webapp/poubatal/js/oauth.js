var accessToken;

function load() {
    console.log('Google API loading...');
    gapi.client.load('oauth2', 'v2');
    //gapi.client.load('oauth2', 'v2', authorize);
}

function authorize() {
    // Step 3: get authorization to use private data
    gapi.auth.authorize({
        client_id: '385110343883.apps.googleusercontent.com',
        scope: [
            'https://www.googleapis.com/auth/userinfo.email',
            'https://www.googleapis.com/auth/userinfo.profile'
        ],
        immediate: false
    }, handle_token);
    return false;
}

function handle_token(auth_result) {
    console.log(auth_result);
    accessToken = auth_result;
}

function getUserInfo(token) {
    if (token && !token.error) {
        gapi.auth.setToken(token);
        var request = gapi.client.oauth2.userinfo.get();
        request.execute(handle_email);
    }
}

function handle_email(email_result) {
    console.log(email_result);
}