import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class Main {

    static WebDriver webDriver;

    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String username = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.aac.ac.il/");
        List <WebElement> elementList = webDriver.findElements(By.className("top-header-menu"));
        WebElement menu = elementList.get(0);
        List <WebElement> menuItems = menu.findElements(By.tagName("li"));
        WebElement personalInfo = menuItems.get(19);
        personalInfo.click();
        Thread.sleep(1000);
        login(username,password);
        List<WebElement> webElementList = webDriver.findElements(By.className("row"));
        WebElement moodleButton  = webElementList.get(5);
        moodleButton.findElement(By.tagName("a")).click();
        Thread.sleep(4000);
        List<WebElement> courseList = webDriver.findElements(By.xpath("//div[@class='card dashboard-card']"));
        printCoursesList(courseList);
        System.out.println("Which course would you like to choose");
        int courseOption =scanner.nextInt();
        courseList.get(courseOption-1).click();
        Thread.sleep(2000);
        logout();


    }

    private static void login(String username, String password){
        WebElement userInput = webDriver.findElement(By.id("Ecom_User_ID"));
        userInput.sendKeys(username);
        WebElement passInfo = webDriver.findElement(By.id("Ecom_Password"));
        passInfo.sendKeys(password);
        WebElement submitButton = webDriver.findElement(By.id("wp-submit"));
        submitButton.click();
    }

    private static void printCoursesList(List<WebElement> coursesList){
        System.out.println("your courses list:");
        for (int i = 0; i < coursesList.size(); i++) {
            String courseName = null;
            try {
                courseName = coursesList.get(i).findElement(By.className("multiline")).getText();
            } catch (Exception e) {

            }
            if (courseName!=null)
            System.out.println(i+1 +"." +(courseName));
        }
    }
    private static void logout() throws Exception{
        WebElement accountBar = webDriver.findElements(By.id("action-menu-toggle-1")).get(0);
        accountBar.click();
        webDriver.findElement(By.xpath("//a[@data-title='logout,moodle']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//a[@href='https://portal.aac.ac.il/AGLogout']")).click();
        System.out.println("Good bye");
    }
}
