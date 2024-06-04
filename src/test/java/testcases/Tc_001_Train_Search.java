package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.Train_Page;
import testbase.TestBase;
import utility.ExcelUtility;

public class Tc_001_Train_Search extends TestBase
{
	
	@Test
	public void verify_Train_Search() throws IOException, InterruptedException
	{
		ExcelUtility excel_util=new ExcelUtility("./testdata/train.xlsx");
		String fromstation=excel_util.getCellData("Sheet 1", 1, 0);
		String endstation=excel_util.getCellData("Sheet 1", 1, 1);
		
		Train_Page trainpage=new Train_Page(driver);
		trainpage.enterSourceStation(fromstation);
		trainpage.selectFromStation();
		Thread.sleep(1000);
		trainpage.enterDestinationStation(endstation);
		trainpage.selectTOStation();
		trainpage.clickDateBox();
		Assert.assertEquals(trainpage.checkCurrentDate(), true,"systemdate and current date are not equal");
		Assert.assertEquals(trainpage.checkdatehighlight(), true,"date is not highlighted");
		trainpage.selectDate(85);
		Thread.sleep(1000);
		trainpage.clickGetTrainsButton();
		//Thread.sleep(1000);
		//trainpage.clickTravelTime();
		//Thread.sleep(2000);
		//trainpage.chooseTrain();
		trainpage.clickFastestTrain();
		Thread.sleep(2000);
		
	}
	
	
	
	
	
	
	
	
	

}
