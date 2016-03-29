// Simple function to set a cookie by the name, value, and desired time in seconds

function setCookie(cname, cvalue, exsecs) {
    var d = new Date();
    d.setTime(d.getTime() + (exsecs*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
