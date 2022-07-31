package version_pack;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Loan extends invoke{
WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		Loan l = new Loan();
		confirmbox cb = new confirmbox();
		int res = cb.showbox("Do you want to add new loans");
		if(res==0)
				{
			l.addRefLoan();
			l.addNonRefLoan();
				}
		else if(res==1) {
			l.loanApplication("easy refundable loan","105");
			l.loanDisburse("105");
		}
		//l.addRefLoan();
		//l.addNonRefLoan();
		l.setEligibility("refundable 50000 loan");
		//l.loanApplication("easy refundable loan","105");
		//l.loanDisburse("105");
	}
	
	public Loan() throws InterruptedException, IOException
	{
		invoke i = new invoke();
		driver = i.login();
	}
	public Loan(WebDriver wd) throws InterruptedException, IOException
	{
		driver = wd;
	}
	

	public void addRefLoan() throws InterruptedException
	{
		System.out.println("calling from addloan method");
		driver.navigate().to(sitepath+"/LoanTypeMst.aspx?title=Loan%20Type");
		driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanCode")).sendKeys("ref1001");
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanName")).sendKeys("refundable 50000 loan");
		Select recover = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlRecoverable")));
		recover.selectByValue("Y");
		driver.findElement(By.id("ctl00_DefaultContent_txtNoOfAvailability")).sendKeys("1");
		driver.findElement(By.id("ctl00_DefaultContent_dcMaxEligibleAmount")).sendKeys("50000");
		driver.findElement(By.id("ctl00_DefaultContent_txtNoOfInstallment")).sendKeys("5");
		driver.findElement(By.id("ctl00_DefaultContent_txtNoOfIntInstallment")).sendKeys("5");
		
		Select relation = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeRelation")));
		relation.selectByValue("6");
		
		driver.findElement(By.id("ctl00_DefaultContent_btnAddRelation")).click();
		Select aadhar = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocument")));
		aadhar.selectByValue("8");
		Select doctype = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocumentStatus")));
		doctype.selectByValue("MANDATORY");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddDocument")).click();
		
		driver.findElement(By.id("ctl00_DefaultContent_btnAddRelation")).click();
		Select document = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocument")));
		document.selectByValue("4");
		Select optional = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocumentStatus")));
		optional.selectByValue("OPTIONAL");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddDocument")).click();
		
		Select fact1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionFactor")));
		fact1.selectByValue("3");
		Select op1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionOperator")));
		op1.selectByValue(">");
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanTypeSanctionValue")).sendKeys("25000");
		Select mo1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionStatus")));
		mo1.selectByValue("MANDATORY");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddSanctionFactor")).click();
		
		Select fact2 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionFactor")));
		fact2.selectByValue("1");
		Select op2 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionOperator")));
		op2.selectByValue(">");
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanTypeSanctionValue")).sendKeys("1");
		Select mo2 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionStatus")));
		mo2.selectByValue("OPTIONAL");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddSanctionFactor")).click();
		driver.findElement(By.id("ctl00_DefaultContent_btnSave")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(1500);
		driver.findElement(By.id("btnCloseMessage")).click();
		System.out.println("refundable loan added");	
		setEligibility("refundable 50000 loan");
		
	}
	
	public void addNonRefLoan() throws InterruptedException
	{
		System.out.println("calling from addloan method");
		driver.navigate().to(sitepath+"/LoanTypeMst.aspx?title=Loan%20Type");
		driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanCode")).sendKeys("nonref1001");
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanName")).sendKeys("non refundable 50000 loan");
		Select recover = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlRecoverable")));
		recover.selectByValue("N");
		driver.findElement(By.id("ctl00_DefaultContent_txtNoOfAvailability")).sendKeys("1");
		driver.findElement(By.id("ctl00_DefaultContent_dcMaxEligibleAmount")).sendKeys("50000");
				
		Select relation = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeRelation")));
		relation.selectByValue("6");
		
		driver.findElement(By.id("ctl00_DefaultContent_chkEqualDistribution")).click();
		
		driver.findElement(By.id("ctl00_DefaultContent_btnAddRelation")).click();
		Select aadhar = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocument")));
		aadhar.selectByValue("8");
		Select doctype = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocumentStatus")));
		doctype.selectByValue("MANDATORY");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddDocument")).click();
		
		driver.findElement(By.id("ctl00_DefaultContent_btnAddRelation")).click();
		Select document = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocument")));
		document.selectByValue("4");
		Select optional = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeDocumentStatus")));
		optional.selectByValue("OPTIONAL");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddDocument")).click();
		
		Select fact1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionFactor")));
		fact1.selectByValue("3");
		Select op1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionOperator")));
		op1.selectByValue(">");
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanTypeSanctionValue")).sendKeys("25000");
		Select mo1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionStatus")));
		mo1.selectByValue("MANDATORY");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddSanctionFactor")).click();
		
		Select fact2 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionFactor")));
		fact2.selectByValue("1");
		Select op2 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionOperator")));
		op2.selectByValue(">");
		driver.findElement(By.id("ctl00_DefaultContent_txtLoanTypeSanctionValue")).sendKeys("1");
		Select mo2 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanTypeSanctionStatus")));
		mo2.selectByValue("OPTIONAL");
		driver.findElement(By.id("ctl00_DefaultContent_btnAddSanctionFactor")).click();
		driver.findElement(By.id("ctl00_DefaultContent_btnSave")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(1500);
		driver.findElement(By.id("btnCloseMessage")).click();
		System.out.println("non refundable loan added");		
		setEligibility("non refundable 50000 loan");
	}
	
	public void setEligibility(String loan) throws InterruptedException
	{
		driver.navigate().to(sitepath+"/LoanTypeEligbCriteriaMst.aspx?title=Eligibility+Criteria");
		Select loanlist = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanType")));
		loanlist.selectByVisibleText(loan);
		driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("ctl00_DefaultContent_txtCriteriaName")).sendKeys("crite1");
		
		Select factor1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlConditionFactor")));
		factor1.selectByValue("1");
		Select op1= new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlConditionOperator")));
		op1.selectByValue(">");
		driver.findElement(By.id("ctl00_DefaultContent_txtConditionValue")).sendKeys("1");
		driver.findElement(By.id("ctl00_DefaultContent_btnEligibilityConditionFactorAdd")).click();
		
		Select factor2 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlConditionFactor")));
		factor2.selectByValue("3");
		Select op2= new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlConditionOperator")));
		op2.selectByValue(">");
		driver.findElement(By.id("ctl00_DefaultContent_txtConditionValue")).sendKeys("25000");
		driver.findElement(By.id("ctl00_DefaultContent_btnEligibilityConditionFactorAdd")).click();
		
		Select formula = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlFormulaFactor")));
		formula.selectByValue("2");
		Select opr = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlFormulaOperator")));
		opr.selectByValue("<");
		driver.findElement(By.id("ctl00_DefaultContent_txtFormulaValue")).sendKeys("1");
		driver.findElement(By.id("ctl00_DefaultContent_btnEligibilityFormulaFactorAdd")).click();
		driver.findElement(By.id("ctl00_DefaultContent_btnSave")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(1500);
		
		driver.findElement(By.id("btnCloseMessage")).click();
		System.out.println("eligibility added");		
		
	}
	
	public void loanApplication(String loan,String empid) throws InterruptedException
	{
	driver.navigate().to(sitepath+"/LoanApplicationMst.aspx?title=Application(T)");
	Thread.sleep(4000);
	try {
		WebDriverWait waita = new WebDriverWait(driver,10);
		WebElement elementa = waita.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ctl00_DefaultContent_btnNewUp"))));
		elementa.click();	
		System.out.println("new button is clicked");
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	
	//	driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
	Thread.sleep(5000);
	Select loanlist = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLoanType")));
	loanlist.selectByVisibleText(loan);
	Thread.sleep(3000);
	driver.findElement(By.id("ctl00_DefaultContent_txtAppliedAmt")).sendKeys("50000");
	driver.findElement(By.id("ctl00_DefaultContent_txtActualCost")).sendKeys("70000");
	
	driver.findElement(By.id("ctl00_DefaultContent_txtEmployeeCode")).sendKeys(empid+"\n");
	Thread.sleep(3000);
	
	
	if(driver.findElement(By.id("btnCloseMessage")).isDisplayed())
		driver.findElement(By.id("btnCloseMessage")).click();
	
	try
	{
		WebElement wb = driver.findElement(By.name("ctl00$DefaultContent$btnCalculate"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();",wb);
		Thread.sleep(20000); 
		System.out.println("try block executed");
	}
	catch(Exception e)
	{
		System.out.println("**********************************************");
		System.out.println(e.getMessage());
		System.out.println("**********************************************");
	}
		
	Select status = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlStatus")));
	status.selectByVisibleText("SANCTIONED");
	Thread.sleep(4000);
	
	try
	{
		driver.findElement(By.xpath("//*[@id=\"ctl00_DefaultContent_txtSanctionedOnDate\"]")).sendKeys(AutoLog.addtodate(0,java.time.LocalDate.now().toString()));
		String loanval = driver.findElement(By.id("ctl00_DefaultContent_txtEntitlement")).getText();
		System.out.println("the value of loanval is :"+loanval);
		driver.findElement(By.id("ctl00_DefaultContent_txtSanctioned")).sendKeys(loanval);
	}
	catch(Exception e)
	{
		System.out.println("**********************************************");
		System.out.println(e.getMessage());
		System.out.println("**********************************************");
	}
	
	
	//driver.findElement(By.id("ctl00_DefaultContent_txtSanctionedOnDate")).sendKeys(AutoLog.addtodate(0,java.time.LocalDate.now().toString() ));
	
	//WebElement wb = driver.findElement(By.id("ctl00_DefaultContent_btnCalculate"));
	Thread.sleep(3000);
	Select relative = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlBeneficiary")));
	relative.selectByIndex(1);
	
	//List <WebElement> cols = driver.findElements(By.xpath("//*[@id=\"ctl00_DefaultContent_tblLoanDocument\"]/tbody/tr[2]/td"));
	List <WebElement> cols = driver.findElements(By.xpath("//*[@id=\"ctl00_DefaultContent_tblLoanDocument\"]/tbody/tr[2]/td/div/table/tbody/tr"));
	System.out.println(cols.size());
	String optionval="";
	int inc=1;
	int adds=0;
	//String flag = "MANDATORY";
	
	System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div/form/div[4]/div/table/tbody/tr/td/div/table[2]/tbody/tr[8]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td[3]/span")).getText());
	
	for(inc=1;inc<cols.size();inc++)
		{
		adds=inc+1;
		//optionval = driver.findElement(By.xpath("//*[@id='ctl00_DefaultContent_tblLoanDocument']/tbody/tr[2]/td/div/table/tbody/tr['"+inc+"']/td['"+(inc+1)+"']/span")).getText();
		optionval = driver.findElement(By.id("ctl00_DefaultContent_gvLoanTypeDocument_ctl0"+adds+"_lblLoanTypeDocumentStatus")).getText().trim();
		System.out.println("the value of optionval is :"+optionval);
		try 
			{
				driver.findElement(By.id("ctl00_DefaultContent_gvLoanTypeDocument_ctl0"+adds+"_chkDocumentGrid")).click();
				System.out.println("check box is clicked");
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
				
		}
	driver.findElement(By.id("ctl00_DefaultContent_btnSave")).click();
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	
	driver.switchTo().activeElement();
	
	WebDriverWait wa= new WebDriverWait(driver, 10);
	WebElement ea= wa.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("btnCloseMessage"))));
	ea.click();
	driver.findElement(By.id("btnCloseMessage")).click();
	
	//driver.close();
	}

		
	public void loanDisburse(String empid) throws InterruptedException
	{
	System.out.println("the value of empid is : "+empid);
	driver.navigate().to(sitepath+"/LoanDisbursement.aspx?title=Disbursement(T)");
	Thread.sleep(3000);
	WebDriverWait wa = new WebDriverWait(driver,10); 
	WebElement ea =	wa.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("ctl00$DefaultContent$btnNewUp")))); 
	ea.click();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("#ctl00_DefaultContent_txtEmployeeCode")).sendKeys(empid);
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@class='ac_results']/ul/li")).click();
	
	Thread.sleep(2000);
			Select ledger = new Select(driver.findElement(By.cssSelector("#ctl00_DefaultContent_ddlLedgerName")));
			ledger.selectByVisibleText("HDFC BANK");
			
			Select ptype = new Select(driver.findElement(By.cssSelector("#ctl00_DefaultContent_ddlPaymentType")));
			ptype.selectByValue("C");
			Random r = new Random();
			String[] chdetails = getcheque();
			driver.findElement(By.cssSelector("#ctl00_DefaultContent_txtChequeNo")).sendKeys(chdetails[0]);
			int number = r.nextInt(999999);
			String s = String.format("%6d", number);
			driver.findElement(By.cssSelector("#ctl00_DefaultContent_txtAccNo")).sendKeys(s);
			driver.findElement(By.cssSelector("#ctl00_DefaultContent_txtPayeeName")).sendKeys("new payee");
			driver.findElement(By.cssSelector("#ctl00_DefaultContent_txtBankName")).sendKeys("sbi");
			Thread.sleep(3000);
			
			driver.findElement(By.cssSelector("#ctl00_DefaultContent_btnAddAccount")).click();
			Thread.sleep(6000);
			
			driver.findElement(By.cssSelector("#ctl00_DefaultContent_btnSave")).click();
			driver.switchTo().alert().accept();
			System.out.println("execution done");
			
	}
	
	
}

