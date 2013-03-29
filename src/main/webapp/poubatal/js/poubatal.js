$('#connect').click(function() {
    connect();
});

$('#verify').click(function() {
    verify();
});

function connect() {
    console.log("connect");
    handleAuthClick();
}

function verify() {
    console.log("verify");
    makeApiCall();
}