package org.hibernate.tutorial;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tutorial.domain.Cat;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManagerCriteria {
	public static void main(String[] args) {
		Cat gato = new Cat("Firulais", "Persa", 2);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Cat.class)
		.add(Restrictions.eq("idCat",3l))
		.add(); //el parametro es el campo de la clase
		List<Cat> gatos = criteria.list();
		for (int i = 0;i<gatos.size();i++){
			System.out.println(gatos.get(i).getName());
		}
	}
}
