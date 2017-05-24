package cn.tedu.ems.service;
/**
 * 应用异常类
 * @author soft01
 *
 */
public class ApplicationException extends RuntimeException {

	public ApplicationException() {
		super();
		
	}

	public ApplicationException(String message) {
		super(message);
		
	}

}
