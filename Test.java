package com.koreait.crawling;

import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

   public static void main(String[] args) {
      
      String DRIVER_ID = "webdriver.chrome.driver";
      String DRIVER_PATH = "C:/lyju777/JSP/chromedriver.exe";
      
      System.setProperty(DRIVER_ID, DRIVER_PATH);
      WebDriver driver = new ChromeDriver();
      
      String base_url = "https://www.banapresso.com/store";
      Document doc = null; 
      
      try {
         driver.get(base_url);
         Thread.sleep(1000);

         int i = 1;
         while(true) {
            try {
               
               try {
                  doc = Jsoup.connect(base_url).get();
               } catch (Exception e) {
                  e.printStackTrace();
               }
                
               System.out.println("-------"+ i + "페이지--------");
            
               List<WebElement> element =  driver.findElements(By.cssSelector("div.store_shop_list > ul > li > a > span.store_name_map"));

               for(WebElement el : element) {
                  String elb = el.findElement(By.tagName("b")).getText();
                  String elspan = el.findElement(By.tagName("span")).getText();
                  if(elb != "") {
                     System.out.println("가게이름 : " + elb + "\n"+ "주소 : "+elspan);   
                  }
                 
               }
               WebElement Nextpage = driver.findElement(
                  By.cssSelector("div.pagination > ul > li:nth-child("+i+")")
               );
               Nextpage.click();
               System.out.println("페이징 " + i);
               Thread.sleep(1000);
               if(i<=4) {
                  i++;
                  
               }else {
                  WebElement NextBtn = driver.findElement(By.cssSelector("div.pagination > span.btn_page_next > a"));
                  NextBtn.click();
                  Thread.sleep(1000);
                  i=1;
                  }
                  
               
            }catch(Exception e) {
               e.printStackTrace();
               System.out.println("프로그램을 종료합니다.");
               break;
            }
         }   
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   
   }
}