package com.quiz.portal.online.idgenerators;

import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuizIdGenerators implements IdentifierGenerator {
	private static final Logger log = LoggerFactory.getLogger(QuizIdGenerators.class);
	private final static String prefix = "QUIZ";
	private String formattedCount;
	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
	

			
			try {
				log.info("session==>" + session);
				log.info("object==>" + object);
				long count = 0;

				ResultSet rs=session.getJdbcConnectionAccess()
				.obtainConnection()
				.createStatement()
				.executeQuery("select quiz_id from quiz_info order by quiz_id desc limit 1");
				if(rs.next()) {
					count=Integer.parseInt(rs.getString(1).substring(4));
					count++;
				}
				if (count < 10) {
					formattedCount = "0000" + count;
				} else if (count > 10 && count < 100) {
					formattedCount = "000" + count;
				} else if (count > 100 && count < 1000) {
					formattedCount = "00" + count;
				} else if (count > 1000 && count < 10000) {
					formattedCount = "0" + count;
				} else {
					formattedCount = String.valueOf(count);
				}
			} catch (Exception err) {
				err.printStackTrace();
				log.error("Not able to generate the Faculty id-->" + err.getMessage());

			}

			return prefix + formattedCount;
		}
	

}
