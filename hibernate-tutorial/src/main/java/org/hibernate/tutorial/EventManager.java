package org.hibernate.tutorial;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;


public class EventManager {
	public static void main(String[] args) {
		EventManager mgr = new EventManager();
		mgr.createAndStoreEvent("My Event", new Date()); //inserta dato
		
		List events = mgr.listEvents();
		for (int i = 0; i < events.size(); i++) {
			Event theEvent = (Event) events.get(i);
			System.out.println(
			"Event: " + theEvent.getTitle() + " Time: " + theEvent.getDate()
			);
		}
		
		Long eventId = mgr.createAndStoreEvent("My Event", new Date());
		Long personId = mgr.createAndStorePerson("Foo", "Bar");
		
		mgr.addPersonToEvent(personId, eventId);
		System.out.println("Added person " + personId + " to event " + eventId);
		
		HibernateUtil.getSessionFactory().close();
		}
	
	private Long createAndStorePerson(String string, String string2) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person aPerson = new Person();
		aPerson.setFirstname(string);
		aPerson.setLastname(string2);
		session.save(aPerson);
		session.getTransaction().commit();
		return aPerson.getId();
	}

	private List listEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Event").list();
		session.getTransaction().commit();
		return result;
	}

	private Long createAndStoreEvent(String title, Date theDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		session.save(theEvent);
		session.getTransaction().commit();
		return theEvent.getId();
		}
	
	private void addPersonToEvent(Long personId, Long eventId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person aPerson = (Person) session.load(Person.class, personId);
		Event anEvent = (Event) session.load(Event.class, eventId);
		aPerson.getEvents().add(anEvent);
		session.getTransaction().commit();
		
		// End of first unit of work
		aPerson.getEvents().add(anEvent); // aPerson (and its collection) is detached
		// Begin second unit of work
		Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		session2.beginTransaction();
		session2.update(aPerson); // Reattachment of aPerson
		session2.getTransaction().commit();		
		
		}
}
