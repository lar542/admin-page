package com.example.study.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.PartnerRepository;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {
	
	@Autowired
	private PartnerRepository partnerRepository;
	
	@Override
	public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
		return Optional.ofNullable(request.getData())
				.map(body -> {
					Item item = Item.builder()
							.status(body.getStatus())
							.name(body.getName())
							.title(body.getTitle())
							.content(body.getContent())
							.price(body.getPrice())
							.brandName(body.getBrandName())
							.registeredAt(LocalDateTime.now())
							.partner(partnerRepository.getOne(body.getPartnerId()))
							.build();
					return item;
				})
				.map(newItem -> baseRepository.save(newItem))
				.map(newItem -> response(newItem))
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<ItemApiResponse> read(Long id) {
		return baseRepository.findById(id)
			.map(item -> response(item))
			.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
		ItemApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
				.map(entityItem -> {
					entityItem
						.setStatus(body.getStatus())
						.setName(body.getName())
						.setTitle(body.getTitle())
						.setContent(body.getContent())
						.setPrice(body.getPrice())
						.setBrandName(body.getBrandName())
						.setRegisteredAt(body.getRegisteredAt())
						.setUnregisteredAt(body.getUnregisteredAt())
						.setPartner(partnerRepository.getOne(body.getPartnerId()));
					return entityItem;
				})
				.map(newEntityItem -> baseRepository.save(newEntityItem))
				.map(item -> response(item))
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(item -> {
					baseRepository.delete(item);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	private Header<ItemApiResponse> response(Item item){
		//API에서 상태 값을 title이나 description으로 변경해서 가져올 수 있다.
		String statusTitle = item.getStatus().getTitle();
		
		ItemApiResponse itemApiResponse = ItemApiResponse.builder()
				.id(item.getId())
				.status(item.getStatus())
				.name(item.getName())
				.title(item.getTitle())
				.content(item.getContent())
				.price(item.getPrice())
				.brandName(item.getBrandName())
				.registeredAt(item.getRegisteredAt())
				.unregisteredAt(item.getUnregisteredAt())
				.partnerId(item.getPartner().getId())
				.build();
		return Header.OK(itemApiResponse);
	}					

}
