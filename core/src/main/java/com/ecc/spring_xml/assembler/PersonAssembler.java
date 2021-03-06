package com.ecc.spring_xml.assembler;

import com.ecc.spring_xml.model.Person;
import com.ecc.spring_xml.model.Name;
import com.ecc.spring_xml.model.Address;
import com.ecc.spring_xml.dto.PersonDTO;
import com.ecc.spring_xml.dto.NameDTO;
import com.ecc.spring_xml.dto.AddressDTO;
import com.ecc.spring_xml.util.AssemblerUtils;

public class PersonAssembler implements Assembler<Person, PersonDTO> {
	private RoleAssembler roleAssembler;
	private ContactAssembler contactAssembler;

	public void setRoleAssembler(RoleAssembler roleAssembler) {
		this.roleAssembler = roleAssembler;
	}

	public void setContactAssembler(ContactAssembler contactAssembler) {
		this.contactAssembler = contactAssembler;
	}

	@Override
	public PersonDTO createDTO(Person model) {
		PersonDTO dto = createBasicDTO(model);
		if (dto == null) {
			return null;
		}
		dto.setAddress(createAddressDTO(model.getAddress()));
		dto.setBirthday(model.getBirthday());
		dto.setGWA(model.getGWA());
		dto.setCurrentlyEmployed(model.getCurrentlyEmployed());
		dto.setDateHired(model.getDateHired());
		dto.setGWA(model.getGWA());
		dto.setContacts(AssemblerUtils.asList(model.getContacts(), contactAssembler::createDTO));
		dto.setRoles(AssemblerUtils.asList(model.getRoles(), roleAssembler::createDTO));
		return dto;
	}

	@Override 
	public Person createModel(PersonDTO dto) {
		Person model = createBasicModel(dto);
		if (model == null) {
			return null;
		}
		model.setAddress(createAddressModel(dto.getAddress()));
		model.setBirthday(dto.getBirthday());
		model.setGWA(dto.getGWA());
		model.setCurrentlyEmployed(dto.getCurrentlyEmployed());
		model.setDateHired(dto.getDateHired());
		model.setGWA(dto.getGWA());
		model.setContacts(AssemblerUtils.asSet(dto.getContacts(), contactAssembler::createModel));
		model.setRoles(AssemblerUtils.asSet(dto.getRoles(), roleAssembler::createModel));
		return model;
	}

	public PersonDTO createBasicDTO(Person model) {
		return model == null? null: new PersonDTO(model.getId(), createNameDTO(model.getName()));
	}

	public Person createBasicModel(PersonDTO dto) {
		return dto == null? null: new Person(dto.getId(), createNameModel(dto.getName()));
	}

	private AddressDTO createAddressDTO(Address model) {
		if (model == null) {
			return null;
		}
		AddressDTO dto = new AddressDTO();
		dto.setStreetNumber(model.getStreetNumber());
		dto.setBarangay(model.getBarangay());
		dto.setMunicipality(model.getMunicipality());
		dto.setZipCode(model.getZipCode());
		return dto;	
	}

	private Address createAddressModel(AddressDTO dto) {
		if (dto == null) {
			return null;
		}
		Address model = new Address();
		model.setStreetNumber(dto.getStreetNumber());
		model.setBarangay(dto.getBarangay());
		model.setMunicipality(dto.getMunicipality());
		model.setZipCode(dto.getZipCode());
		return model;	
	}

	private NameDTO createNameDTO(Name model) {
		if (model == null) {
			return null;
		}
		NameDTO dto = new NameDTO();
		dto.setTitle(model.getTitle());
		dto.setLastName(model.getLastName());
		dto.setFirstName(model.getFirstName());
		dto.setMiddleName(model.getMiddleName());
		dto.setSuffix(model.getSuffix());
		return dto;
	}
 
	private Name createNameModel(NameDTO dto) {
		if (dto == null) {
			return null;
		}
		Name model = new Name();
		model.setTitle(dto.getTitle());
		model.setLastName(dto.getLastName());
		model.setFirstName(dto.getFirstName());
		model.setMiddleName(dto.getMiddleName());
		model.setSuffix(dto.getSuffix());
		return model;
	}
}