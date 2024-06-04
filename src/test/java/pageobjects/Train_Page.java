package pageobjects;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Train_Page
{
	WebDriver driver;
	
	public Train_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	@FindBy(name="station1") public WebElement Frombox;
    @FindBy(xpath="//a[contains(text(),'CHENNAI EGMORE (')]") WebElement Chennaiegmore;
    @FindBy(name="station2") public WebElement Tobox;
	@FindBy(xpath="//a[contains( text(),'BANGALORE CY JN (SBC)')]") WebElement bangalore ;
	@FindBy(xpath="//input[@name='date']") WebElement Datebox;
	@FindBy(xpath="//button[@value='Today']") WebElement Todaybutton;
	@FindBy(xpath="//input[@class='calBtn today']") WebElement Todaydate;
	@FindBy(xpath="//input[@class='monthDsp']") WebElement Monthyear;
	@FindBy(xpath="//td//input[@value='>']") WebElement Nextbutton;
	@FindBy(xpath="//table[@class='dptbl noinnerborder']//td//input[contains(@class,'calBtn')]") List<WebElement> Table;
	@FindBy(id="tbssbmtbtn") WebElement Gettrainsbtn;
	@FindBy(xpath="//a[@title='Click Here to Sort Train List by Travel Time']") WebElement Traveltimebutton;
	//@FindBy(xpath="//tbody//i[@etitle='Pantry Available']/ancestor::tr") List <WebElement> Trainrows;
    @FindBy(xpath="//table[@class='myTable data nocps nolrborder bx1_brm']//i[@etitle='Pantry Available']/ancestor::tr") List <WebElement> Trainrows;
    public void enterSourceStation(String fromstation)
	{
       Frombox.sendKeys(fromstation);		
	}
	
	public void selectFromStation()
	{
		 Chennaiegmore.click();
		
	}
	
	public void enterDestinationStation(String endstation)
	{
       Tobox.sendKeys(endstation);		
	}
	
	public void selectTOStation()
	{
		bangalore.click();
		
	}
	public void clickDateBox()
	{
		Datebox.click();
		
	}
	
	public boolean checkCurrentDate()
	{  
		String currentdate=Todaydate.getAttribute("value")+" "+Monthyear.getAttribute("value");
	    System.out.println(currentdate);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");  
		return LocalDate.parse(currentdate, formatter).isEqual(LocalDate.now());
	}
	
	public boolean checkdatehighlight()
	{
		return (Todaydate.getCssValue("border-color").equals("rgb(0, 200, 0)"));
	}
	
	public void selectDate(int daystoadd)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		String futuredate=LocalDate.now().plusDays(daystoadd).format(formatter);//dd/mm/yyy
		System.out.println(futuredate);
		String fut_date=futuredate.substring(0,2);//dd
		String fut_monyear=futuredate.substring(3);//mmm/yyyy
	    while (!fut_monyear.equalsIgnoreCase(Monthyear.getAttribute("value")))
        {
	    	Nextbutton.click();
        }
		for(int i=0;i<=Table.size();i++)
		{
			if(Table.get(i).getAttribute("value").equals(fut_date) )
			{
				Table.get(i).click();
				break;
			}
		 }
	 }

	public void clickGetTrainsButton()
	{
		
		Gettrainsbtn.click();
	}
	
	public void clickTravelTime()
	{
		
		Traveltimebutton.click();
	}
	public void chooseTrain()
	{
		Trainrows.get(0).click();
		
	}

	
	@FindBy(xpath="//table[@class='myTable data nocps nolrborder bx1_brm']//i[@etitle='Pantry Available']/following::td[2]") List <WebElement> Departuretime;
	@FindBy(xpath="//table[@class='myTable data nocps nolrborder bx1_brm']//i[@etitle='Pantry Available']/following::td[4]") List <WebElement> Arrivaltime;
	
	
    public void clickFastestTrain()
	 {
       List<Integer> min=new<Integer>ArrayList();
       HashMap<Integer,WebElement> hg= new<Integer,WebElement>HashMap();
	     for(int i=0;i<Departuretime.size();i++)
	       {
		    if(Departuretime.get(i).isDisplayed()&&Arrivaltime.get(i).isDisplayed())
		     {
	           LocalTime dtime = LocalTime.parse(Departuretime.get(i).getText());
	           LocalTime atime = LocalTime.parse(Arrivaltime.get(i).getText());
	           int a =dtime.getHour()*60+dtime.getMinute();
	           int	b=atime.getHour()*60+atime.getMinute();
	           if(a>b)
		    	{
		  			b=b+1440;
		   		}
	   		   min.add(Math.abs(a-b));
	 		   hg.put(Math.abs(a-b) ,Departuretime.get(i));
		      }
	
   	        }
	    Collections.sort(min);
	    hg.get(min.get(0)).click();
	  }

    }
