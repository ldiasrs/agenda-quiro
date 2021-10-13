package br.com.agendaquiro.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageHttpResponse {
	private String  message;
	private Map<String, String> values;
	public MessageHttpResponse addValue(String key, String value) {
		if (values == null) {
			values = new HashMap<>();
		}
		values.put(key, value);
		return this;
	}
	public String getValue(String key) {
		if (values == null) {
			values = new HashMap<>();
		}
		return values.get(key);
	}
}
