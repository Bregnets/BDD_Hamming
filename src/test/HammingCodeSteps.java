package test;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;



import junit.framework.Assert;

public class HammingCodeSteps {
	
	private String msg;
	private String error;
	
	@Given("I Recive a Message")
	public void ReciveMsg(String msg) {
		this.msg = msg;
		 System.out.println("Message Recived ===> "+ msg);	
	}
	
	@When("a error occurs while transmitting")
	  public void errorOccurs() {
	    System.out.println(">>>> Error -- ");
	    error = App.flip(msg);
	  }

	  @Then("The algorithm correct that error")
	  public void CatchError() {
	    System.out.println(">>>> Expected Msg - "+ msg);	    
	    String corrected = HammingCode.decode(error);
	    System.out.println(">>>> Actual Msg - "+ corrected);
	  }

}

