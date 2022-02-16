package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureDataJpa
class DemoApplicationTests {

	@Autowired
	DateEntityRepo dateEntityRepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void testDate() {
		TimeZone timeZone = TimeZone.getTimeZone("Europe/Sofia");
		TimeZone.setDefault(timeZone);
		Instant now = Instant.now().truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime lNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		DateEntity dateEntity = new DateEntity();
		dateEntity.setInstantDate(now);
		dateEntity.setLocalDateTime(lNow);
		dateEntity = dateEntityRepo.save(dateEntity);
		assertEquals(now, dateEntity.getInstantDate());
		assertNotEquals(LocalDateTime.now(), Instant.now());
		assertEquals(lNow, dateEntity.getLocalDateTime());

		Map<String, Object> results = jdbcTemplate.queryForMap("SELECT * FROM date_entity");
		Timestamp instantTimestamp = (Timestamp)results.get("INSTANT_DATE");
		Timestamp localDateTimeTimestamp = (Timestamp)results.get("LOCAL_DATE_TIME");
		assertEquals(now, instantTimestamp.toInstant());
		assertEquals(lNow, localDateTimeTimestamp.toLocalDateTime());
		assertEquals(now, localDateTimeTimestamp.toInstant());
	}

}
