# Change this values to an account that has granted The Huffington Post, right
# to share an article otherwise it will not work 
email = "email"
password = "password"


Given(/^I am on the Huffing Post homepage$/) do
    visit 'http://www.huffingtonpost.com/'
end
 
Then(/^I click the login link$/) do
    click_link('hp_login_bt')
end

Then(/^I click the google plus link$/) do
	# sleep(15) # Uncomment if we have a slow internet connection and increase as necessary
	find(".google-plus").click
end

Then(/^I sign in to google plus$/) do
	main = page.driver.browser.window_handles.first
  popup = page.driver.browser.window_handles.last
  page.driver.browser.switch_to.window(popup)
  
  fill_in 'Email', :with => email
  fill_in 'Passwd', :with => password
 	click_on('signIn')

  page.driver.browser.switch_to.window(main)
end

Then(/^I close the modal$/) do
	find('#connect_modal_closer').click
end

Then(/^I am on an article page$/) do
	visit('http://www.huffingtonpost.com/2015/01/04/wenjian-lius-funeral_n_6412894.html')
end

Then(/^I open google plus$/) do
	# sleep(15) # Uncomment if we have a slow internet connection and increase as necessary
  find('ul .googleplus a').click
end

# Assumes that huffington has permission on the google account which
# would be the case if we doing acceptance tests
Then(/^I make a comment and post it$/) do
	main = page.driver.browser.window_handles.first
  popup = page.driver.browser.window_handles.last
  page.driver.browser.switch_to.window(popup)
  
  within(".cc") do
 	  find('div', :text => "Share").click
  end


  page.driver.browser.switch_to.window(main)
end





