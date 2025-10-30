package co.edu.uco.nose.business.assembler.dto.impl;

import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.dto.IdTypeDTO;

public final class IdTypeDTOAssembler implements DTOAssembler<IdTypeDTO, IdTypeDomain> {

	private static final DTOAssembler<IdTypeDTO, IdTypeDomain> instance = new IdTypeDTOAssembler();

	private IdTypeDTOAssembler() {

	}

	public static DTOAssembler<IdTypeDTO, IdTypeDomain> getIdTypeDTOAssembler() {
		return instance;
	}

	@Override
	public IdTypeDTO toDTO(IdTypeDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdTypeDomain toDomain(IdTypeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdTypeDTO> toDTO(List<IdTypeDomain> domainList) {
		// TODO Auto-generated method stub
		return null;
	}

}
