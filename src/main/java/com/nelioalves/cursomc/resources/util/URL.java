package com.nelioalves.cursomc.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String s) {
		String[] split = s.split(",");
		
		List<Integer> categorias = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++) {
			int categoria = Integer.parseInt(split[i]);
			categorias.add(categoria);
		}
		return categorias;
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
}
