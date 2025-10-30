package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CityDomain extends Domain {

	private StateDomain state;
	private String name;

	CityDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setState(new StateDomain());
		setName(TextHelper.getDefault());
	}

	public CityDomain(final UUID id) {
		super(id);
		setState(new StateDomain());
		setName(TextHelper.getDefault());
	}

	public CityDomain(final UUID id, final StateDomain state, final String name) {
		super(id);
		setState(state);
		setName(name);
	}

	public StateDomain getState() {
		return state;
	}

	public void setState(final StateDomain state) {
		this.state = ObjectHelper.getDefault(state, new StateDomain());
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
}