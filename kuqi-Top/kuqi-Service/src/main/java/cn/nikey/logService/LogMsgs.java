package cn.nikey.logService;

public interface LogMsgs {
	/**  
	  * @Title: enclosing_method  
	  * @Description: ��־��ӡ���쳣��
	  * @user: Nikey 
	  * @param tags
	  * @date: 2018��11��28�� ����10:41:01    
	  */
	void logError(String msg);
	
	/**  
	  * @Title: logDug  
	  * @Description: ��־��ӡ����Ϣ�� 
	  * @user: Nikey 
	  * @param tags
	  * @date: 2018��11��28�� ����10:41:36    
	  */
	void logInfo(String msg);
	
	/**  
	  * @Title: enclosing_method  
	  * @Description: ��־��ӡ�����ԣ�
	  * @user: Nikey 
	  * @param tags
	  * @date: 2018��11��28�� ����10:42:02    
	  */
	void logDug(String msg);
}
