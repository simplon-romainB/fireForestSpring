package fr.rb.fireforest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.business.Materiau;
import fr.rb.fireforest.dto.ConfigurationDto;
import fr.rb.fireforest.dto.MateriauDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface FireForestMapper {
	ConfigurationDto toDto(Configuration configuration);

	Configuration toEntity(ConfigurationDto configurationDto);

	MateriauDto toDto(Materiau materiau);

	Materiau toEntity(MateriauDto materiauDto);

}
