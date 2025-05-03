package io.github.aguglia.model;

import lombok.Data;

@Data
public class TaskSmallModel {
	private String SmallID;
	private String TaskID;
	private String content;
	private Boolean compleate;
}
