package cn.supspider.bean;


public class userFeedback {
		/*
		 * �û�������(����):
		 * 			�����û�id/�û���
		 * 			��������
		 * 			��������
		 * 			�ύʱ��
		 * 			�Ƿ����
		 * */
		private int id;
		private String userName;
		private String title;
		private String context;
		private String submitTime;
		private int look;
		
		//ά�����
		private userinfo userinfo;
		public userinfo getUserinfo() {
			return userinfo;
		}
		public void setUserinfo(userinfo userinfo) {
			this.userinfo = userinfo;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContext() {
			return context;
		}
		public void setContext(String context) {
			this.context = context;
		}
		public String getSubmitTime() {
			return submitTime;
		}
		public void setSubmitTime(String submitTime) {
			this.submitTime = submitTime;
		}
		public int getLook() {
			return look;
		}
		public void setLook(int look) {
			this.look = look;
		}
}
