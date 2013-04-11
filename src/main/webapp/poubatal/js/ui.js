(function() {
    $('#input-address').attr('disabled', 'disabled');
    $.ajax({
        url: '/api/directory',
        type: 'GET',
        dataType: 'json',
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
                console.log("selected");
                console.log(address);
            });
        }
    });
})();