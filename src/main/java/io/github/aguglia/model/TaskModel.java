package io.github.aguglia.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TaskModel {
	private String ID;
	private String taskname;
	private String coment;
	private String content;
	private String priority;
	private LocalDate startdate;
	private LocalDate deadlinedate;
	private Integer Requiredtime;
	private String UserID;
	private String[] ShareID;
}
