package com.chains.pwqxfwjk.other;

import java.util.ArrayList;
import java.util.List;

import com.chains.pwqxfwjk.model.GPSCoords;
import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.service.LineInfoService;

public class TransformerInfoConversionStrategy implements
		BdConvesionStrategy<TransformerInfo> {
	private LineInfoService lineInfoService; 
	
	public List<TransformerInfo> bdConvesion(List<? extends GPSCoords> list,
			TransServiceModel model) {
		try {
			lineInfoService.insertTempTable(list, model);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ArrayList<TransformerInfo>();
	}
	 
	public TransformerInfoConversionStrategy(LineInfoService lineInfoService) {
		this.lineInfoService = lineInfoService;
	}
	
	/**
	 * @since lineInfoService
	 * @param lineInfoService
	 * @return void
	 */
	public void setLineInfoService(LineInfoService lineInfoService) {
		this.lineInfoService = lineInfoService;
	}
	
}
