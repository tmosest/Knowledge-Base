// Simple function to set a cookie by the name, value, and desired time in seconds
// here is the code to set path document.cookie = cname + "=" + cvalue + ";path=/; " + expires;
function setCookie(cname, cvalue, exsecs) {
    var d = new Date();
    d.setTime(d.getTime() + (exsecs*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function setCookie(cname, cvalue, exsecs, path) {
    var d = new Date();
    d.setTime(d.getTime() + (exsecs*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";path=" + path + "; " + expires;
}
