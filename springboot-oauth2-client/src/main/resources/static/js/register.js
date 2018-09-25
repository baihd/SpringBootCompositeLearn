var redirect;
redirect = {
    urlHost: ["http://127.0.0.1:8070/ui"],
    redirectData: {
        clientId: 'client',
        scope: 'all',
        redirectURI: 'http://127.0.0.1:8070/ui/redirect',
        responseType: 'code'
    },
    init: function () {
        var that = this;
        that.register();
    },
    register: function () {
        var that = this;
        $("#register").on("click", function () {
            var requestUrl = that.urlHost + '/user/registerUser';
            var indexUrl = that.urlHost + '/index';
            $.ajax({
                url: requestUrl,
                data: $("#form-register").serialize(),
                async: false,
                type: "POST",
                dataType: "JSON",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                success: function (data) {
                    if (data.status === '1') {
                        window.location.href = indexUrl;
                        window.event.returnValue = false;
                    }
                },
                error: function (data) {
                    if (!that.isEmpty(data)) {
                        console.log(data);
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
