var redirect;
redirect = {
    urlHost: ["http://127.0.0.1:8060"],
    accessTokenData: {
        accessToken: [],
        refreshToken: [],
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
        that.passwordAccessToken();
        that.checkToken();
        that.refreshToken();
    },
    passwordAccessToken: function () {
        var that = this;
        $("#passwordAccessToken").on("click", function () {
            var data = {
                'username': 'admin',
                'password': '123456',
                'grant_type': 'password',
                'scope': 'all',
                'client-id': 'client1',
                'client-secret': 'secret'
            };
            var requestUrl = that.urlHost + '/oauth/token';
            $.ajax({
                url: requestUrl,
                data: data,
                type: "POST",
                dataType: "JSON",
                contentType: "application/x-www-form-urlencoded",
                headers: {
                    'Authorization': 'Basic Y2xpZW50MTpzZWNyZXQ=',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    if (!that.isEmpty(data)) {
                        that.accessTokenData.accessToken = data.access_token;
                        that.accessTokenData.refreshToken = data.refresh_token;
                        that.accessTokenData.errorDescription = data.error_description;
                        $("#showAccessToken").html("accessToken------:" + that.accessTokenData.accessToken);
                        $("#showRefreshToken").html("refreshToken------:" + that.accessTokenData.refreshToken);
                    } else {
                        console.log(data);
                    }
                },
                error: function (data) {
                    if (!that.isEmpty(data)) {
                        $("#showErrorMessage").html("showErrorMessage------:" + that.accessTokenData.errorDescription);
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
                    'Authorization': 'Basic Y2xpZW50MTpzZWNyZXQ=',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    if (!that.isEmpty(data)) {
                        that.checkTokenData.userName = data.user_name;
                        that.checkTokenData.authorities = data.authorities;
                        that.checkTokenData.clientId = data.client_id;
                        that.checkTokenData.scope = data.scope;
                        that.checkTokenData.errorDescription = data.error_description;
                        $("#showUserName").html("userName------:" + that.checkTokenData.userName);
                    } else {
                        console.log(data);
                    }
                },
                error: function (data) {
                    if (!that.isEmpty(data)) {
                        $("#showErrorMessage").html("showErrorMessage------:" + that.accessTokenData.errorDescription);
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
                'client-id': 'client1',
                'client-secret': 'secret',
                'refresh_token': that.accessTokenData.refreshToken,
                'grant_type': 'refresh_token'
            };
            var requestUrl = that.urlHost + '/oauth/token';
            $.ajax({
                url: requestUrl,
                data: data,
                type: "POST",
                async: false,
                dataType: "JSON",
                contentType: "application/x-www-form-urlencoded",
                headers: {
                    'Authorization': 'Basic Y2xpZW50MTpzZWNyZXQ=',
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    if (!that.isEmpty(data)) {
                        that.accessTokenData.accessToken = data.access_token;
                        that.accessTokenData.refreshToken = data.refresh_token;
                        $("#showRefreshAccessToken").html("accessToken------:" + that.accessTokenData.accessToken);
                        $("#showRefreshRefreshToken").html("refreshToken------:" + that.accessTokenData.refreshToken);
                    } else {
                        console.log(data);
                    }
                },
                error: function (data) {
                    if (!that.isEmpty(data)) {
                        $("#showErrorMessage").html("showErrorMessage------:" + that.accessTokenData.errorDescription);
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
