import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class User_Test {
	
	WebDriver driver;
	String url="http://localhost/employeeuser/admin/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User_Test obj = new User_Test();
		obj.callBrowser();
		obj.passingUsernameAndPassword("admin@gmail.com", "admin@12345");
		obj.adminLogin();
		obj.clickUserAndURLValidation();
		//obj.entriesDropdown();
		//obj.searchUser("Ad");
		//obj.addNewUser("ram@gmail.com", "ram@12345", "Ram", "Gopal");
		//obj.updateUser("User");
		//obj.deleteUser();
		obj.logoutUser();

	}
	
	public void callBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void passingUsernameAndPassword(String username, String userPassword) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		
	}
	
	public void adminLogin() {
		WebElement uname =  driver.findElement(By.name("username"));
		WebElement upass =  driver.findElement(By.name("password"));
		
		if(uname.getAttribute("value").isEmpty()){
			System.out.println("user name is empty");
			
		}
		else if(upass.getAttribute("value").isEmpty()){
			System.out.println("password is empty");
		}
		else{
			driver.findElement(By.xpath("//*[@id=\"login-frm\"]/button[1]")).click();
		}
	}
	
	public void clickUserAndURLValidation() {
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/a")).click();
		
		String url = driver.getCurrentUrl();
		String expectedUrl="http://localhost/employeeuser/admin/users.php";
		if(url.equals(expectedUrl)) {
			System.out.println("URL Validation Success!");
		}
		else {
			System.out.println("URL Validation Failed!");
		}
		
	}
	
	public void entriesDropdown() {
		driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/label/select")).click();
		
		WebElement option =  driver.findElement(By.name("table_length"));
		
		
			System.out.println("The selected option is: "+ option.getAttribute("value"));
		
	}
	
	public void searchUser(String name) {
		driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[2]/label/input")).sendKeys(name);
		
	}
	
	public void addNewUser(String username, String password, String firstname, String lastname) {
		driver.findElement(By.xpath("/html/body/div[2]/div[4]/button")).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[1]/input")).sendKeys(username);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[2]/input")).sendKeys(password);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[3]/input")).sendKeys(firstname);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[4]/input")).sendKeys(lastname);
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[2]/button")).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		String s= driver.switchTo().alert().getText();
		System.out.println("User Updating Alert Message is : "+s);
		driver.switchTo().alert().accept();
		
	}
	
	public void updateUser(String lastname) {
		searchUser("Ram");
		driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/table/tbody/tr/td[4]/center/button[1]/i")).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[4]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div[4]/input")).sendKeys(lastname);
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[2]/button")).click();
		
		String s= driver.switchTo().alert().getText();
		System.out.println("User Updating Alert Message is : "+s);
		driver.switchTo().alert().accept();
		
	}
	
	public void deleteUser() {
		searchUser("Ram");
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/table/tbody/tr/td[4]/center/button[2]/i")).click();
		
		String s= driver.switchTo().alert().getText();
		System.out.println("User Updating Alert Message is : "+s);
		driver.switchTo().alert().dismiss();
		
	}
	
	public void logoutUser() {
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/a")).click();
	}
}