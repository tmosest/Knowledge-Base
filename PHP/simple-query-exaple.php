// Function: get the users fullname and lo id to build out their information
		function get_user_name_and_lo_id($id) {
			// Query to get the name and lo id of the user based on id
			$mySQL = 'SELECT `Name`, `Lo_Id` FROM `users` WHERE `Id` = "'.$id.'"';
			//echo $mySQL;
			$results = query_my_sql_users($mySQL); // run the sql query
			$user_info = array(); // create an array to store the variables
			while($myresults =  mysql_fetch_assoc($results)  ) { // While have results
				$user_info['name'] = $myresults['Name'];
				$user_info['lo'] = $myresults['Lo_Id'];
			}// end while results
			return $user_info; // return the user information
		}//end get_user_name_and_lo_id
