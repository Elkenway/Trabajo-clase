package co.edu.uco.nose.business.domaine;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

class Domain {
	
	private UUID id;
	
	protected Domain(final UUID id) {
		
		setId(id);
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);

	}
}