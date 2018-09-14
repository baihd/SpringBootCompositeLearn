var redirect;
redirect = {
    init: function () {
        var that = this;
        that.checkUrl();
        that.changeKaptchaImage();
    },

    checkUrl: function () {
        var that = this;
        var errorType = that.getQueryVariable("error");
        if (!that.isEmpty(errorType)) {
            if (errorType === "verifyCode") {
                $("#message").html("验证码错误");
            } else if (errorType === "Authentication") {
                $("#message").html("用户名或密码错误");
            }
        }
    },

    changeKaptchaImage: function () {
        var that = this;
        $("#changeKaptchaImage").on("click", function () {
            var _ctx = $("meta[name='ctx']").attr("content");
            var url = _ctx + "/getKaptchaImage";
            $(".form-login-verifyCode-img").attr("src", url);
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
