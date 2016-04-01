function query_my_sql_users($query) {
			//MySQL stuff
			$username="";
			$password="";
			$database="";
			// connect
			$m = mysql_connect('localhost',$username,$password);
			if($m) {
				mysql_select_db($database, $m);
				$results = mysql_query($query, $m);		
			} else {
				$results = false;	
			}
			mysql_close($m);
			return $results;
		}//end query_my_sql_users
