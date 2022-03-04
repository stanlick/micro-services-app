package com.microservices.microservices.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservices.models.DateHelp;
import com.microservices.microservices.models.DatesUtils;

@RestController
@RequestMapping("/api/calendar")
public class CalendarUtils {


	@GetMapping("help")
	public ResponseEntity<List<DateHelp>> get() {
		return ResponseEntity.ok()
				.body(Arrays.asList(new DateHelp("now", "/now returns current date"),
						new DateHelp("yesterday", "/yesterday returns yesterday's date"),
						new DateHelp("tomorrow", "/tomorrow returns tomorrow's date"),
						new DateHelp("next/{number}/dow", "/next/3/FRIDAY returns next three Fridays"),
						new DateHelp("previous/{number}/dow",
								"/previous/7/SATURDAY returns previous seven Saturdays")));
	}

	@GetMapping("{command}")
	public ResponseEntity<List<LocalDate>> get(@PathVariable String command) {
		switch (command) {
			case "now":
				return ResponseEntity.ok().body(Arrays.asList(LocalDate.now()));
			case "yesterday":
				return ResponseEntity.ok().body(Arrays.asList(LocalDate.now().minusDays(1)));
			case "tomorrow":
				return ResponseEntity.ok().body(Arrays.asList(LocalDate.now().plusDays(1)));
			default:
				throw new IllegalArgumentException(command + " is invalid.  Try /dates/help for valid commands");
		}

	}

	@GetMapping("{command}/{number}/{dayOfWeek}")
	public ResponseEntity<List<LocalDate>> get(@PathVariable String command, @PathVariable int number,
											   @PathVariable DayOfWeek dayOfWeek) {
		switch (command) {
			case "next":
				return ResponseEntity.ok().body(getDates(command, number, dayOfWeek));
			case "previous":
				return ResponseEntity.ok().body(getDates(command, number, dayOfWeek));
			default:
				throw new IllegalArgumentException(command + " is invalid.  Try /dates/help for valid commands");
		}

	}

	private List<LocalDate> getDates(String command, int number, DayOfWeek dayOfWeek) {
		if (command.equals("next"))
			return nextDates(number, dayOfWeek, new ArrayList<>());
		else
			return previousDates(number, dayOfWeek, new ArrayList<>());

	}

	private List<LocalDate> nextDates(int number, DayOfWeek dayOfWeek, List<LocalDate> dates) {
		int daysToAdd = 0;
		for (int i = 0; i < number; i++) {
			dates.add(DatesUtils.getSomeNextOrSameDayOfWeek(dayOfWeek).plusDays(daysToAdd));
			daysToAdd += 7;
		}
		return dates;
	}

	private List<LocalDate> previousDates(int number, DayOfWeek dayOfWeek, List<LocalDate> dates) {
		int daysToSubtract = 0;
		for (int i = 0; i < number; i++) {
			dates.add(DatesUtils.getSomePreviousOrSameDayOfWeek(dayOfWeek).minusDays(daysToSubtract));
			daysToSubtract += 7;
		}
		return dates;
	}


}
