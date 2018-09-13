var redirect;
redirect = {
    urlHost: ["http://127.0.0.1:8081/oauth2"],
    redirectData: {
        clientId: 'client',
        scope: 'all',
        redirectURI: 'http://127.0.0.1:8070/ui/redirect',
        responseType: 'code'
    },
    init: function () {
        var that = this;
        that.login();
    },
    login: function () {
        var that = this;
        $("#login").on("click", function () {
            var requestUrl = that.urlHost
                + '/oauth/authorize'
                + '?'
                + 'response_type=' + that.redirectData.responseType
                + '&'
                + 'client_id=' + that.redirectData.clientId
                + '&'
                + 'redirect_uri=' + that.redirectData.redirectURI
                + '&'
                + 'scope=' + that.redirectData.scope;
            window.location.href = requestUrl;
        });
    },

    getQueryVariable: function (variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return (false);
    },

    isEmpty: function (obj) {
        for (var name in obj) {
            return false;
        }
        return true;
    }

};

$(document).ready(function () {
    redirect.init();
});
