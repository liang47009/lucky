package com.yunfeng.lucky.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yunfeng.lucky.dto.Duration;
import com.yunfeng.lucky.dto.PropsDto;
import com.yunfeng.lucky.entity.CollectRecords;
import com.yunfeng.lucky.entity.Props;
import com.yunfeng.lucky.service.CollectRecordsService;
import com.yunfeng.lucky.service.PropsService;
import com.yunfeng.util.DateUtil;

@Component
public class DtoManager {

	@Resource
	private PropsService propsService;
	@Resource
	private CollectRecordsService collectRecordsService;

	/**
	 * 火热进行中的
	 * 
	 * @param list
	 * @return
	 */
	public List<PropsDto> toHotProps(List<Props> list) {
		List<PropsDto> props = new ArrayList<>();
		for (Props p : list) {
			if (p.getStart() < System.currentTimeMillis()) {// 已经开始的
				if (DateUtil.expired(p.getEnd())) {
					propsService.reValid(p);
				}
				PropsDto propsDto = createPropsDto(p, p.getEnd());
				props.add(propsDto);
			}
		}
		return props;
	}

	/**
	 * 即将开始的
	 * 
	 * @param list
	 * @return
	 */
	public List<PropsDto> toStartProps(List<Props> list) {
		List<PropsDto> props = new ArrayList<>();
		for (Props p : list) {
			if (p.getStart() > System.currentTimeMillis()) {// 即将开始的
				if (DateUtil.in24Hour(p.getStart())) {
					PropsDto propsDto = createPropsDto(p, p.getStart());
					props.add(propsDto);
				}
			}
		}
		return props;
	}

	/**
	 * 创建传输对象
	 * 
	 * @param p
	 * @param duration
	 * @return
	 */
	public PropsDto createPropsDto(Props p, long time) {
		Duration duration = DateUtil.format(time);
		PropsDto propsDto = new PropsDto();
		propsDto.setId(p.getId());
		short count = collectRecordsService.findTotalCountByPid(p.getId());
		propsDto.setCount(count + "/" + p.getTotalCount());
		propsDto.setDuration(duration.toString());
		propsDto.setPrice(String.valueOf(p.getPrice()));
		propsDto.setCast(p.getCast());
		propsDto.setName(p.getName());
		propsDto.setUrl(p.getImgUrl());
		return propsDto;
	}

	/**
	 * 创建传输对象
	 * 
	 * @param p
	 * @param cr
	 * @param duration
	 * @return
	 */
	public PropsDto createSusPropsDto(Props p, CollectRecords cr) {
		PropsDto propsDto = new PropsDto();
		propsDto.setId(p.getId());
		propsDto.setName(p.getName());
		propsDto.setUrl(p.getImgUrl());
		propsDto.setPrice(String.valueOf(p.getPrice()));
		propsDto.setUsername(cr.getName());
		return propsDto;
	}

}
