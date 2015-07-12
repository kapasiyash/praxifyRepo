package com.chiron.vd.tester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.chiron.vd.dao.Medication;
import com.chiron.vd.dao.Relation;
import com.chiron.vd.dao.Symptom;
import com.chiron.vd.persistence.HibernateUtil;

public class TestUnit {

	static List<Symptom> lstSymptom;
	static List<Symptom> lstSymptom2 = new ArrayList<Symptom>();
	static List<Medication> lstMedication;
	static List<Medication> lstMedication2 = new ArrayList<Medication>();
	static List<Relation> lstRelation;
	static List<Relation> lstRelation2 = new ArrayList<Relation>();;
	
	public static void main(String[] args) {
						
		String str =" headache cold fever ";
		str = str.trim();
		search(str);
		
	}
	
	public static void search(String symptom){
		
		String[] splited = symptom.split("\\s+");
		String key="";
		Set set1 = new HashSet();
		Set set2 = new HashSet();
		for(int i = 0;i<splited.length;i++)
		{
			key = splited[i];
			
			if(key.equalsIgnoreCase("high") )
			{
				if(i+1<splited.length)
				{
					set2.add(splited[i+1]);
				}
			}
			else
			{
				set1.add(key);
			}
		}
		System.out.println(set1.toString());
		System.out.println(set2.toString());
		
		getData(set1,set2);
	}
	
	public static void getData(Set set, Set set1) {
		Query query = null;
		Symptom symptom = null;
		Set set2 = new HashSet();
		Set set3 = new HashSet();
		Set set4 = new HashSet();
		Relation r = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Iterator itr1 = set.iterator();
		Iterator itr2 = null;
		
		while (itr1.hasNext()) {
			query = session
					.createQuery("select a FROM Symptom a where symptom='"
							+ (String) itr1.next() + "'");
			lstSymptom = query.list();
			if (lstSymptom != null && !lstSymptom.isEmpty()) {
				query = session
						.createQuery("select a FROM Relation a where symptomId='"
								+ (String) lstSymptom.get(0).getSymptomId()
								+ "'");
				lstRelation = query.list();
				if (lstRelation != null && !lstRelation.isEmpty()) {
					itr2 = lstRelation.iterator();
					while (itr2.hasNext()) {
						r = (Relation) itr2.next();
						if (set2.add(r.getMedicationId())) {
							query = session
									.createQuery("select a FROM Medication a where medicationId='"
											+ r.getMedicationId() + "'");
							lstMedication = query.list();
							lstMedication2.add(lstMedication.get(0));
							set3.add(lstMedication.get(0).getMedication());
						}
						lstRelation2.add(r);
					}

				}
			}
			lstSymptom2.add(lstSymptom.get(0));
		}
		//System.out.println(lstMedication2.toString());
		//System.out.println(lstRelation2.toString());
		//System.out.println(lstSymptom2.toString());
		session.getTransaction().commit();

		if (set1.size() != 0) {			
			int count = 0;
			Iterator itr9 = set3.iterator();
			Medication m1 = null;
			Medication m2 = null;
			Iterator itr10 = lstMedication2.iterator();
			int days = 0;
			while (itr10.hasNext()) {
				m2 = (Medication) itr10.next();

				if (days < Integer.parseInt(m2.getDays())) {
					days = Integer.parseInt(m2.getDays());
					//System.out.println("days---" + days);
				}
			}
			while (itr9.hasNext()) {
				String str = (String) itr9.next();
				 itr10 = lstMedication2.iterator();
				while (itr10.hasNext()) {
					m1 = (Medication) itr10.next();
					if (str.equalsIgnoreCase(m1.getMedication())) {
						count++;
					}
				}
				//System.out.println("count---"+count);
				if (count > 1) {
					count = 0;
					Iterator itr11 = lstMedication2.iterator();
					while (itr11.hasNext()) {
						m2 = (Medication) itr11.next();
						if (m2.getPower().equalsIgnoreCase("high")) {
							if (m2.getDepends().equalsIgnoreCase("Y")) {
								int d = Integer.parseInt(m2.getDays());
								Integer d1 = d + days;
								m2.setDays(d1.toString());
								
							}
							set4.add(m2);
						}
						else
						{
							if (m2.getDepends().equalsIgnoreCase("Y")) {
								int d = Integer.parseInt(m2.getDays());
								Integer d1 = d + days;
								m2.setDays(d1.toString());
								set4.add(m2);
							}
						}
					}

				} else {
					set4.add(m1);
				}

			}

			System.out.println(set4.toString());
		}

		else {
			Medication m2 = null;
			int days = 0;
			Iterator itr10 = lstMedication2.iterator();
			while (itr10.hasNext()) {
				m2 = (Medication) itr10.next();

				if (days < Integer.parseInt(m2.getDays())) {
					days = Integer.parseInt(m2.getDays());
					//System.out.println("days---" + days);
				}
			}
				itr10 = lstMedication2.iterator();
				while (itr10.hasNext()) {
					m2 = (Medication) itr10.next();

					if ((m2.getPower().equalsIgnoreCase("normal"))) {
						if (m2.getDepends().equalsIgnoreCase("Y")) {
							int d = Integer.parseInt(m2.getDays());
							Integer d1 = d + days;
							m2.setDays(d1.toString());
							set4.add(m2);
						}
						set4.add(m2);
					}
				}
			//System.out.println(set4.toString());
		}
	
		Iterator itr12= set4.iterator();
		while(itr12.hasNext())
		{
			Medication m1 = (Medication)itr12.next();
			String str ="";
			System.out.println("Take "+m1.getMedication()+" "+m1.getMg()+" "+m1.getTimes()+" times for " +m1.getDays()+ " day" );
		}
	}
	
}
