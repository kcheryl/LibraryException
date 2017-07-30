package library.exception;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import library.exception.service.IService;
import library.exception.service.ServiceImpl;

public class ExcepServiceTest {
	static IService serv;
	static final String ACTION_SUCCESS = "Action successfully executed";
	static final String PROJECT_BANK = "bank";
	static final String PROJECT_SCHEDULER = "scheduler";

	@BeforeClass
	public static void setUp() {
		serv = new ServiceImpl();
		serv.init();
	}

	@Test
	public void testMap() {
		ServiceImpl servMap = new ServiceImpl();
		servMap.init();
		String result = servMap.getInfoMap().toString();
		String expected = "{ProjectInfo [bank, deposit, java.bank.InsufficientBalanceException]=[ActionAttribute [SMS, {to=9654321}], ActionAttribute [Email, {to=b@n.com}]], "
				+ "ProjectInfo [bank, deposit, java.bank.InvalidAccountException]=[ActionAttribute [SMS, {to=911111111}], ActionAttribute [SMS, {to=9666666}]], "
				+ "ProjectInfo [bank, withdraw, java.bank.InsufficientBalanceException]=[ActionAttribute [Email, {to=a@b.com}], ActionAttribute [Email, {to=c@d.com}]], "
				+ "ProjectInfo [bank, withdraw, java.bank.InvalidAccountException]=[ActionAttribute [Email, {to=w@e.com}], ActionAttribute [SMS, {to=9123456}]], "
				+ "ProjectInfo [scheduler, add, java.scheduler.InvalidDateException]=[ActionAttribute [Log, {file=log2.txt}], ActionAttribute [Log, {file=log3.txt}]], "
				+ "ProjectInfo [scheduler, add, java.scheduler.InvalidNameException]=[ActionAttribute [Email, {to=e@f.com}], ActionAttribute [Log, {file=log.txt}]], "
				+ "ProjectInfo [scheduler, remove, java.scheduler.InvalidDateException]=[ActionAttribute [Email, {to=i@j.com}], ActionAttribute [Log, {file=log4.txt}]], "
				+ "ProjectInfo [scheduler, remove, java.scheduler.InvalidNameException]=[ActionAttribute [SMS, {to=944444}], ActionAttribute [Log, {file=log6.txt}]]}";
		assertEquals(expected, result);
	}

	@Test
	public void testInvalidAction() {
		Exception excep = new Exception("xxx");
		String result = serv.handleAction("temp", "temp", excep);
		String expected = "Error: Invalid exception details";
		assertEquals(expected, result);
	}

	@Test
	public void testEmailAction() {
		Exception excep = new Exception("java.bank.InsufficientBalanceException");
		String result = serv.handleAction(PROJECT_BANK, "withdraw", excep);
		String expected = ACTION_SUCCESS;
		assertEquals(expected, result);
		// console - Sending email to=a@b.com... \n Sending email to=c@d.com...
	}

	@Test
	public void testSMSAction() {
		Exception excep = new Exception("java.bank.InvalidAccountException");
		String result = serv.handleAction(PROJECT_BANK, "deposit", excep);
		String expected = ACTION_SUCCESS;
		assertEquals(expected, result);
		// console - Sending SMS to=911111111... \n Sending SMS to=9666666...
	}

	@Test
	public void testLogAction() {
		Exception excep = new Exception("java.scheduler.InvalidDateException");
		String result = serv.handleAction(PROJECT_SCHEDULER, "add", excep);
		String expected = ACTION_SUCCESS;
		assertEquals(expected, result);
		// console - Logging file=log2.txt... \n Logging file=log3.txt...
	}

	@Test
	public void testSMSLogAction() {
		Exception excep = new Exception("java.scheduler.InvalidNameException");
		String result = serv.handleAction(PROJECT_SCHEDULER, "remove", excep);
		String expected = ACTION_SUCCESS;
		assertEquals(expected, result);
		// console - Sending SMS to=944444... \n Logging file=log6.txt...
	}

	@Test
	public void testEmailLogAction() {
		Exception excep = new Exception("java.scheduler.InvalidDateException");
		String result = serv.handleAction(PROJECT_SCHEDULER, "remove", excep);
		String expected = ACTION_SUCCESS;
		assertEquals(expected, result);
		// console - Sending email to=i@j.com... \n Logging file=log4.txt...
	}

	@Test
	public void testEmailSMSAction() {
		Exception excep = new Exception("java.bank.InvalidAccountException");
		String result = serv.handleAction(PROJECT_BANK, "withdraw", excep);
		String expected = ACTION_SUCCESS;
		assertEquals(expected, result);
		// console - Sending email to=w@e.com... \n Sending SMS to=9123456...
	}

}
