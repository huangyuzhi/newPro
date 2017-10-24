package com.chains.pwqxfwjk.util.Exception;

/**
 * 类名称:ExistsSameNameException<br>
 * 功能描述: 已存在同名数据的异常                     <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月20日 下午11:09:19<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月20日 下午11:09:19<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class ExistsSameNameException extends RuntimeException {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	public ExistsSameNameException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExistsSameNameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ExistsSameNameException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ExistsSameNameException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
