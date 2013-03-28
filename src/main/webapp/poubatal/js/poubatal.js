$('#connect').click(function() {
    connect();
});
$('#disconnect').click(function() {
    disconnect();
});
$('#verify').click(function() {
    verify();
});

function connect() {
    console.log("connect");
    authorize();
}

function disconnect() {
    console.log("disconnect");
}

function verify() {
    console.log("verify");
    getUserInfo(accessToken);
}