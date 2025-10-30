package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.entity.IdTypeEntity;

public final class IdTypeEntityAssembler implements EntityAssembler<IdTypeEntity, IdTypeDomain> {

	private static final EntityAssembler<IdTypeEntity, IdTypeDomain> instance = new IdTypeEntityAssembler();

	private IdTypeEntityAssembler() {

	}

	public static EntityAssembler<IdTypeEntity, IdTypeDomain> getIdTypeEntityAssembler() {
		return instance;
	}

	@Override
	public IdTypeEntity toEntity(IdTypeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdTypeDomain toDomain(IdTypeEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
