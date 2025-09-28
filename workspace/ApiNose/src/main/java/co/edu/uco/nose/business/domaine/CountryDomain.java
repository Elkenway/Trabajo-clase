package co.edu.uco.nose.business.domaine;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CountryDomain extends Domain {
	
	private String name;

	public CountryDomain(final UUID id, final String name) {
		super(UUIDHelper.getUUIDHelper().getDefault(id));
		setName(TextHelper.getDefault());
	}
	
	public CountryDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
}
