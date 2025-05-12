package io.github.aguglia.utils;

import java.util.ArrayList;
import java.util.List;

public class DateUtil {
	public static List<Integer> minuteToHour(Integer minite) {
		int m = (minite == null) ? 0 : minite;
		int min, hour;
		min = m % 60;
		hour = m / 60;
		List<Integer> ans = new ArrayList<Integer>();
		ans.add(hour);
		ans.add(min);

		return ans;
	}

	public static Integer hourToMinite(Integer hour, Integer minite) {
		int h = (hour == null) ? 0 : hour;
		int m = (minite == null) ? 0 : minite;

		int ans;
		ans = (h * 60) + m;
		return ans;
	}
}
