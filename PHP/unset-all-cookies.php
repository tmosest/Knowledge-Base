/*
  Simple function to check if cookies are set in php and then delete them all for the site.
*/
if (isset($_SERVER['HTTP_COOKIE'])) { // if cookies are set for server
    $cookies = explode(';', $_SERVER['HTTP_COOKIE']); // seperate the cookies
    foreach($cookies as $cookie) { // loop through each cookie
        $parts = explode('=', $cookie); // break the cookie into its parts
        $name = trim($parts[0]); // from the parts get the name
        setcookie($name, '', time()-1000); // set the cookie in the past
        setcookie($name, '', time()-1000, '/'); // ? extra line
    } // end for loop
} // end if cookies are set
