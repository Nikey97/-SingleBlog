package cn.supspider.Utils;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;





public class QueryPagingPage extends HibernateDaoSupport{
		/**
		 * �����ҳ����
		 * 
		 * */
//		public static List<?> getPagingList(Session session,String hql,int offset,int length){
//			Query q=session.createQuery(hql);
//			q.setFirstResult(offset);//���� ��ʼ λ��
//			q.setMaxResults(length);//���ö�ȡ������
//			List<?> list=q.list();
//			return list;
//		}
	
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List getListForpage(final String hql, final int offset,final int length) {
			List list = (List) getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					Query query = session.createQuery(hql);
					query.setFirstResult(offset);
					query.setMaxResults(length);
					List list = query.list();
					return list;
				}
				
			});
			return list;
		}  
		
//		public static void main(String[] args) {
//			QueryPagingPage q=new QueryPagingPage();
//			List<String> list = q.getListForpage("from ResourceAll where R_name like '%�й�%'", 0, 8);
//			if(!list.isEmpty()) {
//				System.out.println("���ǿյ�");
//			}
//		} 
		
}
