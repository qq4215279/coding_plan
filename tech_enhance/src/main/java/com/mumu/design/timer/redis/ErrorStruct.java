package com.mumu.design.timer.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cyril_cz
 * @version 2016年5月24日 上午11:57:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorStruct {
	private int id;
	private String text;
	private String comment;
}
