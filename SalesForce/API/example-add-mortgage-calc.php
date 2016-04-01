//Store needed data in SalesForce database instead
define("USERNAME", "");
define("PASSWORD", "");
define("SECURITY_TOKEN", "");
require_once ('sfapi/soapclient/SforceEnterpriseClient.php');
require_once ('sfapi/soapclient/SforceHeaderOptions.php');
$mySforceConnection = new SforceEnterpriseClient();
ini_set("soap.wsdl_cache_enabled", "0"); //Amazing hack to not cache enterprise file
$mySforceConnection->createConnection("sfapi/soapclient/enterprise.wsdl.xml");
$login = $mySforceConnection->login(USERNAME, PASSWORD.SECURITY_TOKEN);
if($login) {

$sObject = new stdclass();
$sObject->Name = $post['id'];
$sObject->Form_ID__c = $post['id'];
$sObject->Form_URL__c = $post['url'];
$sObject->Purchase_Price__c = $post['purchase_price'];
$sObject->Down_Payment__c = $post['down_payment'];
$sObject->Interest_Rate__c = $post['interest'];
$sObject->Loan_Term__c = $post['term'];
$sObject->Property_Taxes__c = $post['taxes'];
$sObject->Home_Insurance__c = $post['ins'];
$sObject->HOA_Condo_Fees__c = $post['hoa'];
$sObject->Start_Date__c = $post['date'];
$sObject->User_Role__c = $post['user'];
$sObject->Credit_Score__c = $post['credit'];

$createResponse = $mySforceConnection->create(array($sObject), 'Mortgage_Calculation__c');
