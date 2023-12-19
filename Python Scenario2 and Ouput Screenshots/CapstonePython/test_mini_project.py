# test_mini_project.py

import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException

@pytest.fixture
def setup():
    driver = webdriver.Chrome(executable_path="chromedriver.exe")
    driver.get("http://the-internet.herokuapp.com/")
    yield driver
    driver.quit()

def test_verify_title(setup):
    assert "The Internet" in setup.title

def test_checkboxes_page(setup):
    # Click on Checkboxes link
    checkboxes_link = setup.find_element(By.LINK_TEXT, "Checkboxes")
    checkboxes_link.click()

    # Verify the text on the page
    assert "Checkboxes" in setup.find_element(By.TAG_NAME, "h3").text

    # Verify checkbox 1 is not checked and checkbox 2 is checked
    assert not is_checkbox_checked(setup, 1)
    assert is_checkbox_checked(setup, 2)


def test_file_upload(setup):
    # Navigate back to the Home page
    #setup.back()

    # Explicitly wait for the File Upload link using the specified XPath expression
    try:
        file_upload_link = WebDriverWait(setup, 10).until(
            EC.element_to_be_clickable((By.XPATH, "//a[normalize-space()='File Upload']"))
        )
        file_upload_link.click()
    except TimeoutException as e:
        # If the element is not found, try switching to an iframe if it exists
        print("TimeoutException: Trying to switch to iframe and locate the element.")
        setup.switch_to.default_content()  # Switch back to the default content

        # Retry locating the File Upload link within the iframe
        try:
            file_upload_link = WebDriverWait(setup, 10).until(
                EC.element_to_be_clickable((By.XPATH, "//a[normalize-space()='File Upload']"))
            )
            file_upload_link.click()
        except TimeoutException:
            # Print page source for debugging
            print("Page source:", setup.page_source)
            # Re-raise the original exception for test failure
            raise e

    # Verify the text on the page
    assert "File Uploader" in setup.find_element(By.TAG_NAME, "h3").text

    # Upload a file
    choose_file_button = setup.find_element(By.ID, "file-upload")
    choose_file_button.send_keys("D:\CapstoneAssignment_Advanced\Python Scenario2 and Ouput Screenshots\CapstonePython\TestDataFile\SivaExample.txt")

    # Click on Upload button
    upload_button = setup.find_element(By.ID, "file-submit")
    upload_button.click()

def is_checkbox_checked(driver, checkbox_index):
    checkbox_xpath = f"//input[@type='checkbox'][{checkbox_index}]"
    checkbox = driver.find_element(By.XPATH,checkbox_xpath)
    return checkbox.is_selected()
