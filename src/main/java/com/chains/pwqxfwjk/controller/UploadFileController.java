package com.chains.pwqxfwjk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chains.core.util.ControllerSupport;

@Controller
@RequestMapping("/uploadFile")
public class UploadFileController extends ControllerSupport {

/*	@RequestMapping(value = "/execel")
	@ResponseBody
	public Map<String, Object> uploadExecel(
			@RequestParam(value = "file", required = false) final MultipartFile multifile,
			final HttpServletRequest request) {
		System.out.println(multifile.getName());
		System.out.println(multifile.getOriginalFilename());
		System.out.println(multifile.getContentType());
		System.out.println(multifile.getSize());
		
		String dir = request.getServletContext().getRealPath("/upload");
		String filePath = dir + "/" + multifile.getOriginalFilename();
		File file = new File(filePath);
		System.out.println(file);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FileUtils.copyInputStreamToFile(multifile.getInputStream(), file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return map;
	}*/
}
