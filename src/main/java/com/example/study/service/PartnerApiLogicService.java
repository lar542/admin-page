package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.Partner;
import com.example.study.model.enumclass.PartnerStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.repository.CategoryRepository;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
		return Optional.ofNullable(request.getData())
				.map(body -> {
					Partner partner = Partner.builder()
							.name(body.getName())
							.status(body.getStatus())
							.address(body.getAddress())
							.callCenter(body.getCallCenter())
							.partnerNumber(body.getPartnerNumber())
							.businessNumber(body.getBusinessNumber())
							.ceoName(body.getCeoName())
							.registeredAt(LocalDateTime.now())
							.category(categoryRepository.getOne(body.getCategoryId()))
							.build();
					return partner;
				})
				.map(newPartner -> baseRepository.save(newPartner))
				.map(newPartner -> response(newPartner))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<PartnerApiResponse> read(Long id) {
		return baseRepository.findById(id)
				.map(partner -> response(partner))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
		PartnerApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
				.map(partner -> {
					partner
						.setName(body.getName())
						.setStatus(body.getStatus())
						.setAddress(body.getAddress())
						.setCallCenter(body.getCallCenter())
						.setPartnerNumber(body.getPartnerNumber())
						.setBusinessNumber(body.getBusinessNumber())
						.setCeoName(body.getCeoName())
						.setUnregisteredAt(body.getStatus().equals(PartnerStatus.UNREGISTERED) ? LocalDateTime.now() : null)
						.setCategory(categoryRepository.getOne(body.getCategoryId()));
					
					return partner;
				})
				.map(undatePartner -> baseRepository.save(undatePartner))
				.map(newPartner -> response(newPartner))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(partner -> {
					baseRepository.delete(partner);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	@Override
	public Header<List<PartnerApiResponse>> search(Pageable pageable) {
		Page<Partner> partners = baseRepository.findAll(pageable);
		
		List<PartnerApiResponse> partnerApiResponseList = partners.stream()
				.map(partner -> response(partner))
				.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
				.totalPages(partners.getTotalPages())
				.totalElements(partners.getTotalElements())
				.currentPage(partners.getNumber())
				.currentElements(partners.getNumberOfElements())
				.build();
		
		return Header.OK(partnerApiResponseList, pagination);
	}
	
	private PartnerApiResponse response(Partner partner) {
		PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
				.id(partner.getId())
				.name(partner.getName())
				.statusKey(partner.getStatus().getKey())
				.statusTitle(partner.getStatus().getTitle())
				.address(partner.getAddress())
				.callCenter(partner.getCallCenter())
				.partnerNumber(partner.getPartnerNumber())
				.businessNumber(partner.getBusinessNumber())
				.ceoName(partner.getCeoName())
				.registeredAt(partner.getRegisteredAt())
				.unregisteredAt(partner.getUnregisteredAt())
				.categoryId(partner.getCategory().getId())
				.categoryTitle(partner.getCategory().getTitle())
				.build();
		return partnerApiResponse;
	}
}
