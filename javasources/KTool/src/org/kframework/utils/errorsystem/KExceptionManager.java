package org.kframework.utils.errorsystem;

import java.util.ArrayList;
import java.util.List;

import org.kframework.utils.errorsystem.KException.ExceptionType;
import org.kframework.utils.errorsystem.KException.KExceptionGroup;
import org.kframework.utils.general.GlobalSettings;


public class KExceptionManager {
	private List<KException> exceptions = new ArrayList<KException>();

	public void register(KException exception) {
		exceptions.add(exception);
		if (exception.exceptionGroup.equals(KExceptionGroup.CRITICAL)) {
			print();
		}
	}

	public void print() {
		boolean errors = false;
		for (KException e : exceptions) {
			if (e.type == ExceptionType.WARNING && e.level <= GlobalSettings.warningslevel)
				System.err.println(e);
			if (e.type == ExceptionType.ERROR) {
				System.err.println(e);
				errors = true;
			}
		}
		if (errors)
			System.exit(1);
	}

	public void print(KExceptionGroup keg) {
		boolean errors = false;
		for (KException e : exceptions)
			if (e.exceptionGroup == keg) {
				if (e.type == ExceptionType.WARNING && e.level <= GlobalSettings.warningslevel)
					System.err.println(e);
				if (e.type == ExceptionType.ERROR) {
					System.err.println(e);
					errors = true;
				}
			}
		if (errors)
			System.exit(1);
	}
}