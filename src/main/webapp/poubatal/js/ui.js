(function() {
    $('#address-selector .loading-indicator').show(400);
    $.ajax({
        url: '/api/directory',
        type: 'GET',
        dataType: 'json',
        complete: function() {            
            $('#address-selector .loading-indicator').hide(400);
        },
        success: function(response) {
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
                console.log("selected");
                console.log(address);
            });
        }
    });
})();

// ACCOUNT
$('#button-connect').click(function() {
    handleAuthClick();
});