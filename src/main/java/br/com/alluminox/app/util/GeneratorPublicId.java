package br.com.alluminox.app.util;

import java.io.Serializable;
import java.util.UUID;

public class GeneratorPublicId implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String random() {
		return UUID.randomUUID().toString();
	}
}
