require "rubygems"
require 'capybara/cucumber'
require "selenium-webdriver"

Capybara.register_driver :selenium_with_long_timeout do |app|
	client = Selenium::WebDriver::Remote::Http::Default.new
	client.timeout = 1200000000000 # Avoid timeout errors 
	Capybara::Selenium::Driver.new(app, :browser => :firefox, :http_client => client)
end 

Capybara.run_server = false
Capybara.default_driver = :selenium_with_long_timeout