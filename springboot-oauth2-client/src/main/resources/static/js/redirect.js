var redirect;
redirect = {
    urlHost: ["http://localhost:8081/oauth2"],
    accessTokenData: {
        accessToken: [],
        refreshToken:[],
        errorDescription: []
    },
    checkTokenData: {
        userName: [],
        authorities: [],
        clientId: [],
        scope: [],
        errorDescription: []
    },
    init: function () {
        var that = this;
        that.accessToken();
        that.checkToken();
        that.refreshToken();
    },
    accessToken: function () {
        var that = this;
        $("#accessToken").on("click", function () {
            var code = that.getQueryVariable("code");
            var data = {
                'client-id': 'client',
                'client-secret': 'secret',
                'scope': 'all',
                'redirect_uri': 'http://localhost:8082/ui/redirect',
                'code': code,
                'grant_type': 'authorization_code'
            };
            var requestUrl = that.urlHost + '/oauth/token';
            $.ajax({
                url: requestUrl,
                data: data,
                type: "POST",
                dataType: "JSON",
                contentType: "application/x-www-form-urlencoded",
                headers: {
                    'Authorization': 'Basic Y2xpZW50OnNlY3JldA==',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    if (!that.isEmpty(data)) {
                        that.accessTokenData.accessToken = data.access_token;
                        that.accessTokenData.refreshToken = data.refresh_token;
                        that.accessTokenData.errorDescription = data.error_description;
                    } else {
                        console.log(data);
                    }
                },
                error: function (data) {
                    if (!that.isEmpty(data)) {
                        if (!that.isEmpty(data.responseJSON.error_description)) {
                            that.accessTokenData.errorDescription = data.responseJSON.error_description;
                            console.log(data.responseJSON.error_description);
                        } else {
                            console.log(data);
                        }
                    }
                }
            });

        });
    },

    checkToken: function () {
        var that = this;
        $("#checkToken").on("click", function () {
            var data = {'token': that.accessTokenData.accessToken};
            var requestUrl = that.urlHost + '/oauth/check_token';
            $.ajax({
                url: requestUrl,
                data: data,
                type: "GET",
                dataType: "JSON",
                contentType: "application/x-www-form-urlencoded",
                headers: {
                    'Authorization': 'Basic Y2xpZW50OnNlY3JldA==',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    if (!that.isEmpty(data)) {
                        that.checkTokenData.userName = data.user_name;
                        that.checkTokenData.authorities = data.authorities;
                        that.checkTokenData.clientId = data.client_id;
                        that.checkTokenData.scope = data.scope;
                        that.checkTokenData.errorDescription = data.error_description;
                    } else {
                        console.log(data);
                    }
                },
                error: function (data) {
                    if (!that.isEmpty(data)) {
                        if (!that.isEmpty(data.responseJSON.error_description)) {
                            that.checkTokenData.errorDescription = data.responseJSON.error_description;
                            console.log(data.responseJSON.error_description);
                        } else {
                            console.log(data);
                        }
                    }
                }
            });
        });
    },

    refreshToken: function () {
        var that = this;
        $("#refreshToken").on("click", function () {
            var data = {
                // 'client-id': 'client',
                // 'client-secret': 'secret',
                'refreshtoken': that.accessTokenData.refreshToken,
                'grant_type': 'refresh_token'
            };
            var requestUrl = that.urlHost + '/oauth/token';
            $.ajax({
                url: requestUrl,
                data: data,
                type: "POST",
                dataType: "JSON",
                contentType: "application/x-www-form-urlencoded",
                headers: {
                    'Authorization': 'Basic Y2xpZW50OnNlY3JldA==',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    if (!that.isEmpty(data)) {
                        that.accessTokenData.accessToken = data.access_token;
                    } else {
                        console.log(data);
                    }
                },
                error: function (data) {
                    if (!that.isEmpty(data)) {
                        if (!that.isEmpty(data.responseJSON.error_description)) {
                            that.accessTokenData.errorDescription = data.responseJSON.error_description;
                            console.log(data.responseJSON.error_description);
                        } else {
                            console.log(data);
                        }
                    }
                }
            });
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
