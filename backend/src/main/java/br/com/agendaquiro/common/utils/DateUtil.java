package br.com.agendaquiro.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
@Transactional(readOnly=true)
public class DateUtil {

	private final SimpleDateFormat formatMMddyyyy = new SimpleDateFormat("MM/dd/yyyy");
	private final SimpleDateFormat formatddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	private final SimpleDateFormat formatddMMyyyy_as_HHmm = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm");
	private final SimpleDateFormat formatHHmm = new SimpleDateFormat("HH:mm");


	public String format_ddMMyyyy(final Date date) {
		if (date != null) {
			return String.valueOf(formatddMMyyyy.format(date));
		}
		return "";
	}
	
	public String format_MMddyyy(final Long time){
		if (time != null) {
			return String.valueOf(formatMMddyyyy.format(time));
		}
		return "";
	}

	public Date format_MMddyyy(final String date) throws ParseException {
		return new Date(formatMMddyyyy.parse(date).getTime());
	}

	public String format_ddMMyyyy(final Long time) {
		if (time != null) {
			return String.valueOf(formatddMMyyyy.format(time));
		}
		return "";
	}

	public String format_ddMMyyyy_as_HHmm(final Long time) {
		if (time != null) {
			return String.valueOf(formatddMMyyyy_as_HHmm.format(time));
		}
		return "";
	}

	public String format_HHmm(final Long time) {
		if (time != null) {
			return String.valueOf(formatHHmm.format(time));
		}
		return "";
	}

	public Date format_ddMMyyyy(String date) throws ParseException {
		return new Date(formatddMMyyyy.parse(date).getTime());
	}

}
