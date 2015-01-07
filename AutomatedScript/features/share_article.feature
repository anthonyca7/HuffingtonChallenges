Feature:  Login to huffingtonpost.com and open any article and share that article on google plus
 
Scenario: Login to the website        
	Given I am on the Huffing Post homepage
	Then I click the login link
	Then I click the google plus link
	Then I sign in to google plus
	Then I close the modal

	Then I am on an article page
	Then I open google plus
	Then I make a comment and post it
