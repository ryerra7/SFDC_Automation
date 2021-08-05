package main.java.com.rp.automation.customisedexception;


public class FrameworkException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameworkException()
	{
		super();
	}
	
	public FrameworkException(String exceptionMessage)
	{
		super(exceptionMessage);
	}
	
	

	
	public FrameworkException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public FrameworkException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public FrameworkException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
