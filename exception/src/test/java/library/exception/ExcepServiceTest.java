package library.exception;

import static org.junit.Assert.*;
import org.junit.Test;
import library.exception.service.ServiceImpl;

public class ExcepServiceTest {

	@Test
	public void testMap() {
		ServiceImpl serv = new ServiceImpl();
		serv.init();
		String result = serv.getInfoMap().toString();
		String expected = "{ProjectInfo [bank, deposit, java.bank.InsufficientBalanceException]=[ActionAttribute [sms, {to=9654321}], ActionAttribute [email, {to=b@n.com}]], "
				+ "ProjectInfo [bank, deposit, java.bank.InvalidAccountException]=[ActionAttribute [sms, {to=911111111}], ActionAttribute [log, {file=log2.txt}]], "
				+ "ProjectInfo [bank, withdraw, java.bank.InsufficientBalanceException]=[ActionAttribute [email, {to=a@b.com}], ActionAttribute [sms, {to=985027676}], ActionAttribute [log, {file=log.txt}]], "
				+ "ProjectInfo [bank, withdraw, java.bank.InvalidAccountException]=[ActionAttribute [email, {to=c@d.com}], ActionAttribute [sms, {to=9123456}]], "
				+ "ProjectInfo [scheduler, add, java.scheduler.InvalidDateException]=[ActionAttribute [sms, {to=9333333}], ActionAttribute [log, {file=log3.txt}]], "
				+ "ProjectInfo [scheduler, add, java.scheduler.InvalidNameException]=[ActionAttribute [email, {to=e@f.com}], ActionAttribute [sms, {to=92222222}]], "
				+ "ProjectInfo [scheduler, remove, java.scheduler.InvalidDateException]=[ActionAttribute [email, {to=i@j.com}], ActionAttribute [log, {file=log4.txt}]], "
				+ "ProjectInfo [scheduler, remove, java.scheduler.InvalidNameException]=[ActionAttribute [sms, {to=944444}], ActionAttribute [email, {to=g@h.com}]]}";
		assertEquals(expected, result);
	}
}
