package com.quiz.portal.online.idgenerators;

import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentIdGenerator implements IdentifierGenerator{

	private static final Logger log = LoggerFactory.getLogger(FacultyIdGenerator.class);
	final static String prefix = "STU";

	String formattedCount;
	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		try {
			log.info("session==>" + session);
			log.info("object==>" + object);
			long count = 1;

			ResultSet rs=session.getJdbcConnectionAccess()
			.obtainConnection()
			.createStatement()
			.executeQuery("select student_id from student_info order by student_id desc limit 1");
			if(rs.next()) {
				count=Integer.parseInt(rs.getString(1).substring(3));
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
			log.error("Not able to generate the student id-->" + err.getMessage());

		}

		return prefix + formattedCount;
	}

}
