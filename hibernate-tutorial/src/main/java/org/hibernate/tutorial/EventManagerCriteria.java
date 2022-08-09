package org.hibernate.tutorial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tutorial.domain.Cat;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManagerCriteria {
	public static void main(String[] args) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Cat gato = new Cat("Firulais", "Persa", 2);
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Cat.class)
			.add(Restrictions.eq("idCat",3l));
			gato.setBreed("Persa-2");
			gato.setIsAlive(1);
			gato.setLitterId(1);
			gato.setCanBark(0);
			gato.setDateOfBirth(format.parse(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
			session.save(gato);
//			session.close();
			List<Cat> gatos = criteria.list();
			
			for (int i = 0;i<gatos.size();i++){
				System.out.println(gatos.get(i).getName());
				System.out.println(gatos.get(i).getAge());
			}			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}		
	}
}
