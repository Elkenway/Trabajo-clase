package co.edu.uco.nose.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.dto.UserDTO;

public final class UserDTOAssembler implements DTOAssembler<UserDTO, UserDomain> {

	private static final DTOAssembler<UserDTO, UserDomain> instance = new UserDTOAssembler();

	private UserDTOAssembler() {

	}

	public static DTOAssembler<UserDTO, UserDomain> getUserDTOAssembler() {
		return instance;
	}

	@Override
	public UserDTO toDTO(UserDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDomain toDomain(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> toDTO(final List<UserDomain> domainList) {
		var userDtoList = new ArrayList<UserDTO>();

		for (var userDomain : domainList) {
			userDtoList.add(toDTO(userDomain));
		}

		return userDtoList;
	}

}
